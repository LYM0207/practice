<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, base.JangBaguni"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2 align="center">장바구니</h2>

	<%
		ArrayList<JangBaguni> myList = (ArrayList) session
				.getAttribute("jang");
		out.print("<table border='1' align='center'> ");
		for (int i = 0; i < myList.size(); i++) {
			JangBaguni jang = myList.get(i);
			out.print("<tr heigth='150' ><td align='center'>");
			out.print("<image src='./images/" + jang.getName()
					+ ".jpg' width250 height='130'>");
			out.print("</td ><td width='350' align='center'>" + jang.getName() + "</td>");
			out.print("<td width='350' align='center'>" + jang.getPrice() + "</td>");
			out.print("<td width='350' align='center'>" + jang.getCnt() + "</td>");
			out.print("<td ><input type='button' name='delete' value='삭제'></td>");
			out.print("</tr>");
		}
		out.print("</table>");
	%>
</body>
</html>