package ticket.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import ticket.dao.TicketDao;

public class SearchTicketService {

	private TicketDao tDAO = new TicketDao();

	public TicketList search(SearchRequest searchReq) {
		// TODO Auto-generated method stub
		Connection conn = null;
		TicketList ticketList = new TicketList();
		String carNo = searchReq.getCarNo();
		try {
			conn = ConnectionProvider.getConnection();

			ticketList=tDAO.getTicketInfo(conn, searchReq);
			
			System.out.println("ticketList : "+ticketList);
			
			JdbcUtil.close(conn);
		} catch (SQLException e) {
			// TODO: handle exception
			JdbcUtil.rollback(conn);
		}
		return ticketList;
	}
}
