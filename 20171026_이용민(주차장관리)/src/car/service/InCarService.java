package car.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import ticket.model.Ticket;
import car.dao.CarDao;
import car.model.Car;

public class InCarService {

	private CarDao carDao = new CarDao();

	// 관련 정보 서비스
	public CarInfo searchCarInfo(CarRequest carReq) {
		// TODO Auto-generated method stub
		Connection conn = null;
		String carNo = carReq.getCarNo();

		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Ticket ticketInfo = new Ticket();
			ticketInfo = carDao.getCarTicketInfo(conn, carNo);

			int parkNo = carDao.getParkNo(conn);

			Car car = new Car();
			car = carDao.getParkigCar(conn, carNo);

			CarInfo carInfo = new CarInfo();
			carInfo.setTicketInfo(ticketInfo);
			carInfo.setParkNo(parkNo);
			carInfo.setCar(car);

			conn.commit();

			return carInfo;

		} catch (SQLException e) {
			// TODO: handle exception
			JdbcUtil.rollback(conn);
		}finally{
			JdbcUtil.close(conn);
		}

		return null;
	}


	// 주차 입고 서비스
	public boolean inputCar(CarRequest carReq) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			Car car = new Car();

			car.setCarno(carReq.getCarNo());
			car.setGrade(carReq.getGrade());
			car.setTstat(carReq.getTstat());

			return carDao.insertPakingCar(conn, car);

			
		} catch (Exception e) {
			// TODO: handle exception
			JdbcUtil.rollback(conn);
		}finally{
			JdbcUtil.close(conn);
		}


		return false;
	}

	

}
