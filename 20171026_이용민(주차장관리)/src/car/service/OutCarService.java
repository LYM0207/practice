package car.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import car.dao.CarDao;
import car.model.Car;

public class OutCarService {
	private CarDao carDao = new CarDao();

	// 주차 출고 서비스
	public boolean outputCar(CarRequest carReq) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();

			Car car = new Car();

			car.setParkno(carReq.getParkno());
			car.setTstat(carReq.getTstat());

			return carDao.updateParkingCar(conn, car);
		} catch (SQLException e) {
			// TODO: handle exception
			JdbcUtil.rollback(conn);
		}finally{
			JdbcUtil.close(conn);
		}


		return false;
	}

}
