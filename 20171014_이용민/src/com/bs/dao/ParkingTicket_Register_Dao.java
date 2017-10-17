package com.bs.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bs.model.ParkingTicketVO;
import com.bs.util.DBUtil;

public class ParkingTicket_Register_Dao extends HttpServlet {


	// 정기권 등록 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		req.setCharacterEncoding("utf-8");

		int tno = Integer.parseInt(req.getParameter("tno"));
		String carno = req.getParameter("carno");
		String phone = req.getParameter("phone");
		String grade = req.getParameter("grade");
		String tstat = req.getParameter("tstat");
		String startdate = req.getParameter("startdate");
		String enddate = req.getParameter("enddate");

		String sql = "insert into ticket_tbl_01 values (?,?,?,?,?,?,?)";

		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();

		try {
			Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, tno);
			pstmt.setString(2, carno);
			pstmt.setString(3, phone);
			pstmt.setString(4, grade);
			pstmt.setString(5, tstat);
			pstmt.setString(6, startdate);
			pstmt.setString(7, enddate);

			int res = pstmt.executeUpdate();

			if (res > 0) {
				resp.sendRedirect("mainPage.jsp?contentPage=/view/parkingTicket_List.jsp");
			}

			conn.close();
			pstmt.close();

		} catch (Exception e) {
			out.print("SQL erro :" + e.getMessage());
		}
	}

	//정기권 번호 자동 생성 
	public int getAutoNum() {
		ParkingTicket_List_Dao listDao = new ParkingTicket_List_Dao();
		ArrayList<ParkingTicketVO> ptList = new ArrayList<ParkingTicketVO>();
		ptList = listDao.getList();
		int[] tno = new int[ptList.size()];

		for (int i = 0; i < ptList.size(); i++) {
			tno[i] = ptList.get(i).getTno();
		}
		int count = 1;
		for (int i = 0; i < tno.length; i++) {
			if (tno[i] != count) {
				break;
			}
			count++;
		}
		return count;
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
		if(grade.equalsIgnoreCase("Y"))
			cal.add(Calendar.YEAR, 1);
		if (grade.equalsIgnoreCase("M"))
			cal.add(Calendar.DAY_OF_MONTH, 30);
		
		SimpleDateFormat fm = new SimpleDateFormat(
	            "yyyyMMdd");
	    endDate = fm.format(cal.getTime());

		return endDate;
		
		
	}
}
