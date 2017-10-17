package com.bs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class JoinProcess extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		req.setCharacterEncoding("utf-8");

		// 사용자로 부터 입력 받은 id , pw
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String dupId = req.getParameter("dupId");

		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// MySql Qurey
		String qurey = "";

		// DB 연결
		try {
			Context context = new InitialContext();
			DataSource source = (DataSource) context
					.lookup("java:comp/env/jdbc/myconn");
			conn = source.getConnection();

		} catch (Exception e) {
			// TODO: handle exception
			resp.sendError(511, "DB가 연결되지 않습니다.");
			e.printStackTrace();
		}

		// 중복버튼이 눌렸을 경우
		if (dupId.equals("dup")) {
			try {
				qurey = "select member_id from login_Info";
				pstmt = conn.prepareStatement(qurey);
				rs = pstmt.executeQuery();

				// 중복체크가 되었는지 안되었는지 확인.
				boolean duplicated = false;
				
				//MySql에 있는 정보 저장 및 중복 비교
				while (rs.next()) {
					String idCheck = rs.getString("member_id");

					// 중복된 아이디가 있으면 중복체크값을 true로
					if (id.equals(idCheck)) {
						duplicated = true;
						resp.sendRedirect("login_Page.html?duplication");
						// 중복아이디가 없으면 false로 초기화
					} else {
						duplicated = false;
					}
				}

				if (duplicated == false)
					resp.sendRedirect("login_Page.html?notDuplication?"+id);
				
			} catch (Exception e) {
				out.println("SQL erro : " + e.getMessage());
				// TODO: handle exception
			}

			// 중복버튼이 안눌렸을경우.
		} else if (!dupId.equals("dup")) {
			// DB에 데이터 Insert
			try {
				qurey = "insert into login_Info values (?, ?) ";
				pstmt = conn.prepareStatement(qurey);
				pstmt.setString(1, id);
				pstmt.setString(2, pw);

				int res = pstmt.executeUpdate();

				if (res > 0) {

					out.print("<h3>Save Success !!</h3 ");
				}

				pstmt.close();
				conn.close();
			} catch (Exception e) {
				out.println("SQL erro : " + e.getMessage());
				// TODO: handle exception
			}
		}

	}
}
