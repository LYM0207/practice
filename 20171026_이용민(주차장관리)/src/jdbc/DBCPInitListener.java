package jdbc;

import java.io.IOException;
import java.io.StringReader;
import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub

		String poolConfig = sce.getServletContext().getInitParameter(
				"poolConfig");
		Properties prop = new Properties();

		try {
			prop.load(new StringReader(poolConfig));
		} catch (IOException e) {
			// TODO: handle exception
			throw new RuntimeException("poolConfig oad fail", e);
		}
		loadJDBCDriver(prop);
		initConnectionPool(prop);

	}

	private void loadJDBCDriver(Properties prop) {
		// TODO Auto-generated method stub
		String driverClass = prop.getProperty("jdbcDriver");
		try {
			// 컨넥션 풀에서 사용할 jdbc 드라이버를 로딩
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			throw new RuntimeException("fail to load JDBC Driver", e);
		}
	}

	private void initConnectionPool(Properties prop) {
		// TODO Auto-generated method stub
		try {
			String jdbcUrl = prop.getProperty("jdbcUrl");
			String dbUser = prop.getProperty("dbUser");
			String dbPass = prop.getProperty("dbPass");

			// 컨넥션팩토리 생성. 컨넥션 팩토리는 새로운 컨넥션을 생성할때 사용한다.
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(
					jdbcUrl, dbUser, dbPass);

			// DBCP가 컨넥션 풀에 컨넥션을 보관할때 사용하는 PoolableConnectionFactory 생성
			// 실제로 내부적으로 컨넥션을 담고 있고 컨넥션을 관리하는데 기능을 제공한다. ex)커넥션을 close하면 종료하지 않고
			// 커넥션 풀에 반환
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(
					connFactory, null);
			String validationQuery = prop.getProperty("validationQuery");
			if (validationQuery != null && !validationQuery.isEmpty()) {
				// 커넥션이 유효한지 확일할때 사용하는 쿼리를 설정.
				poolableConnFactory.setValidationQuery(validationQuery);
			}

			// 커넥션 풀의 설정 정보를 생성
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			// 유휴 커넥션 검사 주기
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			// 풀에 있는 커넥션이 유효한지 검사 유무 설정
			poolConfig.setTestWhileIdle(true);
			// 커넥션 최소 갯수 설정
			int minIdle = getIntProperty(prop, "minIdle", 5);
			poolConfig.setMinIdle(minIdle);
			// 커넥션 최대 갯수 설정
			int maxTotal = getIntProperty(prop, "maxTotal", 50);
			poolConfig.setMaxTotal(maxTotal);

			// 커넥션 풀 생성. 인자로는 위에서 생성한 PoolableConnectionFactory와
			// GenericObjectPoolConfig를 사용
			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(
					poolableConnFactory, poolConfig);
			// PoolableConnectionFactory에도 커넥션 풀을 연결
			poolableConnFactory.setPool(connectionPool);

			// 커넥션 풀을 제공하는 jdbc드라이버를 등록
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver) DriverManager
					.getDriver("jdbc:apache:commons:dbcp:");
			String poolName = prop.getProperty("poolName");
			// 위에서 커넥션 풀 드라이버에 생성한 커넥션 풀을 등록한다.
			driver.registerPool(poolName, connectionPool);

		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	private int getIntProperty(Properties prop, String propName,
			int defaultValue) {
		// TODO Auto-generated method stub
		String value = prop.getProperty(propName);
		if (value == null)
			return defaultValue;
		return Integer.parseInt(value);
	}
}
