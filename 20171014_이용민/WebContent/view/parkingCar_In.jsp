<%@page import="java.io.PrintWriter"%>
<%@page import="javafx.scene.control.Alert"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.bs.model.ParkingTicketVO"%>
<%@ page import="com.bs.dao.ParkingCar_In_Dao"%>

<%!ParkingTicketVO ptVO = new ParkingTicketVO();
	ParkingCar_In_Dao ciDAO = new ParkingCar_In_Dao();%>

<%request.setCharacterEncoding("utf-8");
	String carNo =request.getParameter("carNo");
	ptVO = ciDAO.getSearch(carNo);
	boolean dup = ciDAO.getCheckInCar(carNo);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	var tno =
<%=ptVO.getTno()%>
	if (
<%=dup%>
	) {
		alert("입고된 차량입니다.")
		location.href = "mainPage.jsp?contentPage=/view/parkingCar_Serch.jsp";
	} else {
		if (tno == 0) {
			alert("정기권이아닙니다. 일일권 입니다.");
		}
	}

	function q() {
		if (confirm("[차량입고] 하시겠습니까?") == true) {
			return true;
		} else {
			return false;
		}
	}
</script>
<body>
	<form method="post" action="carIn" onsubmit="return q()">
		<table border="1">
			<tr>
				<th>정기권번호</th>
				<td><input type="text" name="tno" value="<%=ptVO.getTno()%>"></td>
			</tr>
			<tr>
				<th>차량번호</th>
				<td><input type="text" name="carno"
					value="<%=ptVO.getCarno()%>"></td>
			</tr>
			<tr>
				<th>차주전화</th>
				<td><input type="text" name="phone"
					value="<%=ptVO.getPhone()%>"></td>
			</tr>
			<tr>
				<th>등급</th>
				<td><input type="text" name="grade"
					value="<%=ptVO.getGrade()%>"></td>
			</tr>
			<tr>
				<th>시작일</th>
				<td><input type="text" name="startdate"
					value="<%=ptVO.getStartdate()%>"></td>
			</tr>
			<tr>
				<th>종료일</th>
				<td><input type="text" name="enddate"
					value="<%=ptVO.getEnddate()%>"></td>
			</tr>
			<tr>
				<th>비고</th>
				<td><input type="text" name="note" value="<%=ptVO.getNote()%>"></td>
			</tr>
			<tr>
				<th>주차번호</th>
				<td><input type="text" name="pno"
					value="<%=ciDAO.getPakingNum()%>"></td>
			</tr>
		</table>
		<input type="submit" value="입고확인">
	</form>

</body>
</html>