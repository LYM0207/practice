package com.bs.dao;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bs.model.ParkingCarVO;
import com.bs.util.DBUtil;

public class ParkingCar_Out_Dao extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 출고 확인 버튼 클릭시 데이터베이스에 저장된 속성 값을 변경  입고(I) ㅡ> 출고(O)
		// 현재시간을 출고 시간에 입력.
		
		req.setCharacterEncoding("utf-8");

		String parkno = req.getParameter("parkno");
		String date = getCurrentDate();
		String sql = "update park_info_tbl set tstat='O', outdate=(?) where parkno='"
				+ parkno + "'";

		try {
			Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, date);
			
			int res = pstmt.executeUpdate();
			
			if(res>0){
				resp.sendRedirect("mainPage.jsp?contentPage=/view/parkingCar_List.jsp?update='o'");
				System.out.println("출고완료");
			}
		} catch (Exception e) {
			// TODO: handle exception
			resp.sendError(500, "DB Err"+e.getMessage());
		}

	}

	// 현재 년 월 일 시 분 초 생성
	public String getCurrentDate() {

		SimpleDateFormat fm = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = fm.format(new Date());
		System.out.println("현재시간 시분초 = " + date);

		return date;
	}

	// 차량번호로 입고차량 정보 검색
	public ParkingCarVO searchCar(String carno) {
		ParkingCarVO carVO = new ParkingCarVO();

		String sql = "select * from park_info_tbl where carno='" + carno + "'";
		try {
			Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int parkNo = rs.getInt("parkno");
				String carNo = rs.getString("carno");
				String grade = rs.getString("grade");
				String tstat = rs.getString("tstat");
				String indate = rs.getString("indate");
				String bigo = "";
				if (tstat.equalsIgnoreCase("I"))
					bigo = " 출차 준비중 입니다. ";
				if (tstat.equalsIgnoreCase("O"))
					bigo = " 출차 하였습니다. ";

				carVO.setParkno(parkNo);
				carVO.setCarno(carNo);
				carVO.setGrade(grade);
				carVO.setTstat(tstat);
				carVO.setIndate(indate);
				carVO.setBigo(bigo);

				conn.close();
				pstmt.close();
				rs.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return carVO;
	}
	
	// 출고차량 확인 메소드
		public boolean getCheckOutCar(String carNo) {
			boolean dup = false;
			String sql = "select tstat from park_info_tbl where carno='" + carNo
					+ "'";
			try {
				Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();

				if(rs.next()){
					String tstat = rs.getString("tstat");
					if(tstat.equalsIgnoreCase("O")){
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

}
