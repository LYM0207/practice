<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="javax.naming.*"%>
<%@ page import="javax.sql.DataSource"%>
<%@ page import=" java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%
	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
	String tel = request.getParameter("tel");

	Connection conn = null;
	PreparedStatement pstmt = null;
	
	try {
		Context context = new InitialContext();
		DataSource source = (DataSource) context
				.lookup("java:comp/env/jdbc/myconn");
		conn = source.getConnection();
		System.out.println("Register Connection Succes");
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Register Connection Err");
	}

	String sql = "insert into login_info values(?, ?, ?, ?)";

	try {
		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, id);
		pstmt.setString(2, pw);
		pstmt.setString(3, name);
		pstmt.setString(4, tel);

		int res = pstmt.executeUpdate();

		if (res > 0){
			System.out.println("등록 완료");
			response.sendRedirect("loginPage.jsp");
		}

	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Insert Err");

	} finally {
		conn.close();
		pstmt.close();
	}
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>