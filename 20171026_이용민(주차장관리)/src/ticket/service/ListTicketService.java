package ticket.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.connection.ConnectionProvider;
import ticket.dao.TicketDao;

public class ListTicketService {

	private TicketDao tDao = new TicketDao();

	// 정기권 목록 리스트 서비스
	public ArrayList<TicketList> getTicketList() {
		// TODO Auto-generated method stub
		ArrayList<TicketList> tList = new ArrayList<>();

		try (Connection conn = ConnectionProvider.getConnection()) {
			tList = tDao.getTicketList(conn);

			System.out.println(tList);
		} catch (SQLException e) {
			// TODO: handle exception
		}

		return tList;
	}

}
