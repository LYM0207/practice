package car.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ticket.model.Ticket;
import car.model.Car;

public class CarDao {
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 차량번호의 정기권정보 가져오기
	public Ticket getCarTicketInfo(Connection conn, String carNo) {

		String sql = "select * from ticket_tbl_01 where carno='" + carNo + "'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// 차량번호 값이 있으면
			if (rs.next()) {
				return new Ticket(rs.getInt("tno"), rs.getString("carno"),
						rs.getString("phone"), rs.getString("grade"),
						rs.getString("tstat"), rs.getString("startdate"),
						rs.getString("enddate"));
			} else
			// 차량번호 값이 없으면
			if (!rs.next()) {
				return new Ticket(carNo, "D");
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}

		return null;
	}

	// 차량번호의 주차정보 가져오기
	public Car getParkigCar(Connection conn, String carNo) throws SQLException {
		// TODO Auto-generated method stub

		String sql = "select * from park_info_tbl where carno='" + carNo + "'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return new Car(rs.getInt("parkno"), rs.getString("carno"),
						rs.getString("grade"), rs.getString("tstat"),
						rs.getString("indate"), rs.getString("outdate"));
			}

			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return null;
	}

	// 다음 주차번호 생성
	public int getParkNo(Connection conn) {
		// TODO Auto-generated method stub

		String sql = "select Max(parkno) from park_info_tbl";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next())
				return rs.getInt(1) + 1;

			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("주차번호 에러 : " + e);
		}
		return 0;
	}

	// 주차 입고 DAO
	public boolean insertPakingCar(Connection conn, Car car) {
		// TODO Auto-generated method stub
		String sql = "insert into park_info_tbl values (null,?,?,?,?,?);";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, car.getCarno());
			pstmt.setString(2, car.getGrade());
			pstmt.setString(3, car.getTstat());
			pstmt.setString(4, car.getIndate());
			System.out.println("차 입고 시간 : " + car.getIndate());
			pstmt.setString(5, null);

			int res = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	// 주차된 차량 정보 리스트 DAO
	public ArrayList<Car> selectParkedCar(Connection conn) {
		// TODO Auto-generated method stub
		String sql = "select * from park_info_tbl";
		ArrayList<Car> parkedCarList = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			String convertGradeText = null;

			while (rs.next()) {
				if (rs.getString("grade").equals("M"))
					convertGradeText = "월회원";
				if (rs.getString("grade").equals("Y"))
					convertGradeText = "년회원";
				if (rs.getString("grade").equals("D"))
					convertGradeText = "일일회원";

				parkedCarList.add(new Car(rs.getInt("parkno"), rs
						.getString("carno"), convertGradeText, rs
						.getString("tstat"), rs.getString("indate"), rs
						.getString("outdate")));

			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return parkedCarList;
	}

	public boolean updateParkingCar(Connection conn, Car car) {
		// TODO Auto-generated method stub
		String sql = "update park_info_tbl set tstat='O', outdate=(?) where parkno='"
				+ car.getParkno() + "'";

		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, car.getOutdate());

			int res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
		}

		return false;
	}

}
