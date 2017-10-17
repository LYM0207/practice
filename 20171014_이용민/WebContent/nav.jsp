<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function pageMove(pageNum) {
		if (pageNum == 0) {
			location.href = "mainPage.jsp?contentPage=/view/parkingHome.jsp";
		}
		if (pageNum == 1) {
			location.href = "mainPage.jsp?contentPage=/view/parkingTicket_Register.jsp";
		}
		if (pageNum == 2) {
			location.href = "mainPage.jsp?contentPage=/view/parkingTicket_List.jsp";
		}
		if (pageNum == 3) {
			location.href = "mainPage.jsp?contentPage=/view/parkingCar_Serch.jsp";
		}	
		if (pageNum == 4) {
			location.href = "mainPage.jsp?contentPage=/view/parkingCar_List.jsp";
		}
	}
</script>
<body>
	<input type="button" value="Home" onclick="pageMove(0)">
	<input type="button" value="정기권등록" onclick="pageMove(1)">
	<input type="button" value="정기권 조회" onclick="pageMove(2)">
	<input type="button" value="주차차량입출고" onclick="pageMove(3)">
	<input type="button" value="주차현황조회" onclick="pageMove(4)">
</body>
</html>