package com.bs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.bs.model.ParkingCarVO;
import com.bs.util.DBUtil;

public class ParkingCar_List_Dao {

	
	//주차 조회 정보 출력
	public ArrayList<ParkingCarVO> getList() {

		ArrayList<ParkingCarVO> pcList = new ArrayList<ParkingCarVO>();
		String sql = "select * from park_info_tbl";
		try {
			Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ParkingCarVO pcVO = new ParkingCarVO();

				int parkno = rs.getInt("parkno");
				String carno = rs.getString("carno");
				String grade = rs.getString("grade");
				String tstat = rs.getString("tstat");
				String indate = rs.getString("indate");
				String outdate = rs.getString("outdate");
				
				if(grade.equalsIgnoreCase("M"))
					grade="월회원";
				if(grade.equalsIgnoreCase("Y"))
					grade="연회원";
				if(grade.equalsIgnoreCase("D"))
					grade="일일회원";

				pcVO.setParkno(parkno);
				pcVO.setCarno(carno);
				pcVO.setGrade(grade);
				pcVO.setTstat(tstat);
				pcVO.setIndate(indate);
				pcVO.setOutdate(outdate);

				pcList.add(pcVO);

			}
			conn.close();
			pstmt.close();
			rs.close();
		} catch (Exception e) {
		}
		return pcList;
	}

}
