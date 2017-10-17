package com.bs.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bs.model.ParkingTicketVO;
import com.bs.util.DBUtil;

public class ParkingCar_In_Dao extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 입고 차량 등록

		req.setCharacterEncoding("utf-8");

		int parkno = Integer.parseInt(req.getParameter("pno"));
		String carno = req.getParameter("carno");
		String grade = req.getParameter("grade");
		String tstat = "I";
		String date = getCurrentDate();

		String sql = "insert into park_info_tbl(parkno, carno, grade,tstat, indate) values (?,?,?,?,?)";
		try {
			Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, parkno);
			pstmt.setString(2, carno);
			pstmt.setString(3, grade);
			pstmt.setString(4, tstat);
			pstmt.setString(5, date);

			int res = pstmt.executeUpdate();

			if (res > 0) {
				resp.sendRedirect("mainPage.jsp?contentPage=/view/parkingCar_List.jsp?update='i'");
			}

			conn.close();
			pstmt.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	// 차량 번호로 정기권인지 일일권인지 검색
	public ParkingTicketVO getSearch(String carNo) {
		String sql = "select * from ticket_tbl_01 where carno='" + carNo + "'";
		ParkingTicketVO ptVO = new ParkingTicketVO();
		try {
			Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			int tno;
			String carno;
			String phone;
			String grade;
			String tstat;
			String startdate;
			String enddate;

			if (!rs.next()) {
				tno = 0;
				carno = carNo;
				phone = "-";
				grade = "D";
				tstat = "-";
				startdate ="-";
				enddate = "-";

			} else {
				tno = rs.getInt("tno");
				carno = rs.getString("carno");
				phone = rs.getString("phone");
				grade = rs.getString("grade");
				tstat = rs.getString("tstat");
				startdate = rs.getString("startdate");
				enddate = rs.getString("enddate");

			}
			ptVO.setTno(tno);
			ptVO.setCarno(carno);
			ptVO.setPhone(phone);
			ptVO.setGrade(grade);
			ptVO.setTstat(tstat);
			ptVO.setStartdate(startdate);
			ptVO.setEnddate(enddate);

			if (grade.equalsIgnoreCase("M"))
				ptVO.setNote("월회원 입니다.");
			if (grade.equalsIgnoreCase("Y"))
				ptVO.setNote("연회원 입니다.");
			if(grade.equalsIgnoreCase("D"))
				ptVO.setNote("일일권 입니다.");

			conn.close();
			pstmt.close();
			rs.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ptVO;
	}

	// 입고차량 중복 검색
	public boolean getCheckInCar(String carNo) {
		boolean dup = false;
		String sql = "select tstat from park_info_tbl where carno='" + carNo
				+ "'";
		try {
			Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()){
				String tstat = rs.getString("tstat");
				if(tstat.equalsIgnoreCase("i")){
					dup = true;
				}
			}
			conn.close();
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}

		return dup;
	}

	// 주자번호 1부터 자동생성, 빈곳이있으면 숫자를 채워서 생성
	public String getPakingNum() {

		int count = 1;

		try {
			String sql = "select parkno from park_info_tbl";
			Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			ArrayList<Integer> parkNums = new ArrayList<Integer>();

			while (rs.next()) {
				int parkNo = rs.getInt("parkno");
				parkNums.add(parkNo);
			}

			// 등록된 주자번호가 없으면 1부터 시작
			if (parkNums.isEmpty()) {
				return "1";
			} else {

				// 있으면 숫자가 빈 번호 찾고 없으면 다음번호로 시작
				for (int i = 0; i < parkNums.size(); i++) {
					if ((i + 1) != parkNums.get(i)) {
						break;
					}
					count++;
				}
			}

			conn.close();
			pstmt.close();
			rs.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "" + count;
	}

	// 현재 년 월 일 시 분 초 생성
	public String getCurrentDate() {

		SimpleDateFormat fm = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = fm.format(new Date());
		System.out.println("현재시간 시분초 = " + date);

		return date;
	}

	public boolean existCarNo(String carNo) {
		boolean exist = false;

		return exist;
	}

}
