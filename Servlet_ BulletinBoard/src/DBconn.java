import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class DBconn {
	
	public Connection dbconn(){
		
		
		Connection conn = null;
		
		try {
			Context context = new InitialContext();
			DataSource source = (DataSource)context.lookup("java:comp/env/jdbc/myconn");
			conn=source.getConnection();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("DB connection Fail !!!");
			e.printStackTrace();
			
		}
		
		return conn;
		
	}

}
