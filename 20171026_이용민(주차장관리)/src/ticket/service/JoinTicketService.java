package ticket.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import ticket.dao.TicketDao;
import ticket.model.Ticket;

public class JoinTicketService {

	private TicketDao ticketDao = new TicketDao();

	// 정기권 등록 서비스
	public boolean join(JoinRequest joinReq) {
		// TODO Auto-generated method stub
		Connection conn = null;
		boolean success = false;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			success = ticketDao.insert(
					conn,
					new Ticket(joinReq.getCarNo(), joinReq.getPhone(), joinReq
							.getGrade(), joinReq.getTstat(), joinReq
							.getStartDate(), joinReq.getEndDate(), joinReq
							.getGrade()));

			conn.commit();

		} catch (SQLException e) {
			// TODO: handle exception
			JdbcUtil.rollback(conn);
		} finally {
			JdbcUtil.close(conn);
		}
		return success;
	}

	// 정기권 등록 날짜
	public String getStartDate() {
		int year;
		int month;
		int day;
		String startDate;

		Calendar calendar = new GregorianCalendar(Locale.KOREA);
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH) + 1;
		day = calendar.get(Calendar.DAY_OF_MONTH);

		startDate = "" + year + month + day;

		return startDate;
	}

	// 정기권 종료 날짜
	public String getEndDate(String grade) {

		String endDate;

		Calendar cal = new GregorianCalendar(Locale.KOREA);

		cal.setTime(new Date());
		if (grade.equalsIgnoreCase("Y"))
			cal.add(Calendar.YEAR, 1);
		if (grade.equalsIgnoreCase("M"))
			cal.add(Calendar.DAY_OF_MONTH, 30);

		SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");
		endDate = fm.format(cal.getTime());

		return endDate;

	}

}
