//20170925   이용민//

package bs.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import bs.com.model.UserVO;
import bs.com.utl.DBUtil;

public class UserDAO {

	Scanner sc = new Scanner(System.in);
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 입력 메서드
	public UserVO insertUser() {
		UserVO uv = new UserVO();
		System.out.println("1. 입력 기능");
		// 이름, 나이 정보 입력
		System.out.print("이름을 입력하세요 >>");
		String name = sc.nextLine().trim();
		System.out.print("나이를 입력하세요 >>");
		int age = Integer.parseInt(sc.next().trim());

		String sql = "insert into userinfo values (?,?)";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, name);
			pstmt.setInt(2, age);

			int res = pstmt.executeUpdate();

			if (res > 0) {
				// 객체에 저장
				uv.setName(name);
				uv.setAge(age);
				System.out.println("Data input succeeded ! ");
				System.out.println();
			}

			conn.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println("SQL Err : " + e.getMessage());
		}

		return uv;
	}

	// 수정 메서드
	public void updateUser() {
		// 이름 입력
		System.out.print("나이를 수정할 이름을 입력하시오 >> ");
		String name = sc.next().trim();

		// 입력받은 name의 age값을 35로 수정
		String sql = "update userinfo set age=35 where name='" + name + "'";

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);

			int res = pstmt.executeUpdate();

			if (res > 0) {
				System.out
						.println("Data update succeeded !");
				System.out.println();
			}

			conn.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println("SQL Err :" + e.getMessage());
		}

	}

	// 검색 메서드
	public ArrayList searchName() {

		// 모든 정보 검색 Qurey
		String sql = "Select * from userinfo";

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// 정보 카운트
			int no = 1;
			System.out
					.println("=================모든 자료 검색 결과==================");
			while (rs.next()) {
				String userinfo_Name = rs.getString("name");
				int userinfo_Age = rs.getInt("age");
				System.out.println("No : " + no + "          Name : "
						+ userinfo_Name + "               Age : "
						+ userinfo_Age);
				no++;
			}
			System.out
					.println("===============================================");
			System.out.println();

			conn.close();
			pstmt.close();
			rs.close();

		} catch (Exception e) {
			System.out.println("SQL Err : " + e.getMessage());
		}

		return null;
	}

	// 삭제 메서드
	public void deleteUser() {
		System.out.print("정보를 삭제할 이름을 입력하시오>>");
		String name = sc.next().trim();

		// name값의 정보를 삭제
		String sql = "delete from userinfo where name='" + name + "'";

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);

			int res = pstmt.executeUpdate();

			if (res > 0) {
				System.out.println(name + "  Removed information,  Success !");
				searchName();
			}

			conn.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println("SQL Err : " + e.getMessage());
		}

	}

}
