package car.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import car.dao.CarDao;
import car.model.Car;

public class ListCarService {

	private CarDao carDao = new CarDao();

	public ArrayList<Car> getCarList() {
		// TODO Auto-generated method stub
		Connection conn = null;
		ArrayList<Car> list = new ArrayList<>();

		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			list = carDao.selectParkedCar(conn);

			conn.commit();
		} catch (SQLException e) {
			// TODO: handle exception
			JdbcUtil.rollback(conn);
		} finally {
			JdbcUtil.close(conn);
		}

		return list;
	}

}
