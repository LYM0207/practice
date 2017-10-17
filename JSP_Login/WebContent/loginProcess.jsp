<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.DataSource"%>
<%@ page import="javax.naming.*"%>
<%@ page session="true"%>

<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {
		Context context = new InitialContext();
		DataSource source = (DataSource) context
				.lookup("java:comp/env/jdbc/myconn");
		conn = source.getConnection();
		System.out.println("Connection Succes");
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("connection Erro");
	}

	String query = "select * from login_info";
	boolean id_dup = false;
	try {
		pstmt = conn.prepareStatement(query);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			String member_Id = rs.getString("member_id");

			if (member_Id.equals(id)) {
				System.out.println("같은아이디 존재");
				id_dup = true;
				session.setAttribute("member_Id", member_Id);

				response.sendRedirect("shopPage.jsp");

				break;
			}
		}

		if (id_dup == false) {

			response.sendRedirect("registerPage.jsp");
		}
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Select  Erro");
	} finally {
		conn.close();
		pstmt.close();
		rs.close();
	}
%>