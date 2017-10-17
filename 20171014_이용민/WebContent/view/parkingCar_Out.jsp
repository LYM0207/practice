<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.bs.model.ParkingCarVO"%>
<%@ page import="com.bs.dao.ParkingCar_Out_Dao"%>

<%!ParkingCarVO carVO = new ParkingCarVO();
	ParkingCar_Out_Dao outDAO = new ParkingCar_Out_Dao();%>
<%
request.setCharacterEncoding("utf-8");
	String carno = request.getParameter("carNo");
	carVO = outDAO.searchCar(carno);
	boolean dup = outDAO.getCheckOutCar(carno);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	if (
<%=dup%>
	) {
		alert("이미출고된 차량입니다.")
		location.href = "mainPage.jsp?contentPage=/view/parkingCar_Serch.jsp";
	} else if (
<%=carVO.getParkno()%>
	== 0) {
		alert("입고된 차량이 아닙니다. 확인 후 다시 입력해주세요")
		location.href = "mainPage.jsp?contentPage=/view/parkingCar_Serch.jsp";
	}

	function q() {
		if (confirm("[차량출고] 하시겠습니까?") == true) {
			return true;
		} else {
			return false;
		}
	}
</script>
<body>
	<form method="post" action="carOut" onsubmit="return q()">
		<table border="1">
			<tr>
				<th>주차번호</th>
				<td><input type="text" name="parkno"
					value="<%=carVO.getParkno()%>"></td>
			</tr>
			<tr>
				<th>차량번호</th>
				<td><input type="text" value="<%=carVO.getCarno()%>"></td>
			</tr>
			<tr>
				<th>등급</th>
				<td><input type="text" value="<%=carVO.getGrade()%>"></td>
			</tr>
			<tr>
				<th>상태</th>
				<td><input type="text" value="<%=carVO.getTstat()%>"></td>
			</tr>
			<tr>
				<th>입고일시</th>
				<td><input type="text" value="<%=carVO.getIndate()%>"></td>
			</tr>
			<tr>
				<th>출고일시</th>
				<td><input type="text" value=""></td>
			</tr>
			<tr>
				<th>비고</th>
				<td><input type="text" value="<%=carVO.getBigo()%>"></td>
			</tr>
		</table>
		<input type="submit" value="출고확인">
	</form>

</body>
</html>