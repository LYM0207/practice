package com.bs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.bs.model.ParkingTicketVO;
import com.bs.util.DBUtil;

public class ParkingTicket_List_Dao {
	
	//정기권 정보 조회 출력
	
	public ArrayList<ParkingTicketVO> getList() {

		ArrayList<ParkingTicketVO> ptList = new ArrayList<ParkingTicketVO>();
		String sql = "select * from ticket_tbl_01";
		try {
			Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ParkingTicketVO ptVO = new ParkingTicketVO();

				int tno = rs.getInt("tno");
				String carno = rs.getString("carno");
				String phone = rs.getString("phone");
				String grade = rs.getString("grade");
				String tstat = rs.getString("tstat");
				String startdate = rs.getString("startdate");
				String enddate = rs.getString("enddate");

				ptVO.setTno(tno);
				ptVO.setCarno(carno);
				ptVO.setPhone(phone);
				ptVO.setGrade(grade);
				ptVO.setTstat(tstat);
				ptVO.setStartdate(startdate);
				ptVO.setEnddate(enddate);

				ptList.add(ptVO);

			}
			conn.close();
			pstmt.close();
			rs.close();
		} catch (Exception e) {
		}
		return ptList;
	}

}
