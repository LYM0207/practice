package ticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ticket.model.Ticket;
import ticket.service.SearchRequest;
import ticket.service.TicketList;

public class TicketDao {

	// 정기권 등록
	public boolean insert(Connection conn, Ticket ticket) {
		String sql = "insert into ticket_tbl_01 values (null,?,?,?,?,?,?)";

		System.out.println(ticket.getCarno() + ticket.getPhone());
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, ticket.getCarno());
			pstmt.setString(2, ticket.getPhone());
			pstmt.setString(3, ticket.getGrade());
			pstmt.setString(4, ticket.getTstat());
			pstmt.setString(5, ticket.getStartdate());
			pstmt.setString(6, ticket.getEnddate());

			int res = pstmt.executeUpdate();
			System.out.println("성공");

			if (res > 0) {
				return true;

			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("dberro : " + e);
		}

		return true;
	}

	public ArrayList<TicketList> getTicketList(Connection conn) {
		// TODO Auto-generated method stub
		ArrayList<TicketList> tList = new ArrayList<>();
		String sql = "select * from ticket_tbl_01";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int tno = rs.getInt("tno");
				String carno = rs.getString("carno");
				String phone = rs.getString("phone");
				String grade = rs.getString("grade");
				String tstat = rs.getString("tstat");
				String startdate = rs.getString("startdate");
				String enddate = rs.getString("enddate");

				tList.add(new TicketList(tno, carno, phone, grade, tstat,
						startdate, enddate));

			}

		} catch (SQLException e) {
			System.out.println("dbErro: " + e);
		}
		System.out.println(tList);
		return tList;
	}

	// 정기권 번호 자동 생성
	// public int getAutoNum() {
	// ParkingTicket_List_Dao listDao = new ParkingTicket_List_Dao();
	// ArrayList<ParkingTicketVO> ptList = new ArrayList<ParkingTicketVO>();
	// ptList = listDao.getList();
	// int[] tno = new int[ptList.size()];
	//
	// for (int i = 0; i < ptList.size(); i++) {
	// tno[i] = ptList.get(i).getTno();
	// }
	// int count = 1;
	// for (int i = 0; i < tno.length; i++) {
	// if (tno[i] != count) {
	// break;
	// }
	// count++;
	// }
	// return count;
	// }

	public TicketList getTicketInfo(Connection conn, SearchRequest searchReq) {
		// TODO Auto-generated method stub
		String sql = "select * from ticket_tbl_01 where carno= '"
				+ searchReq.getCarNo() + "'";
		TicketList ticket = new TicketList();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("DAO 실행?");
			if (rs.next()) {
				ticket.setTno(rs.getInt("tno"));
				ticket.setCarno(rs.getString("carno"));
				ticket.setPhone(rs.getString("phone"));
				ticket.setGrade(rs.getString("grade"));
				ticket.setTstat(rs.getString("tstat"));
				ticket.setStartdate(rs.getString("startdate"));
				ticket.setEnddate(rs.getString("enddate"));
			}
			System.out.println(ticket.getCarno());

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("SQL Erro : " + e);
		}

		return ticket;
	}
}
