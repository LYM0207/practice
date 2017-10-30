<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function q() {
		if (confirm("[차량입고] 하시겠습니까?") == true) {
			return true;
		} else {
			return false;
		}
	}
	
</script>
<c:if test="${carInfo.car.tstat=='I'}">
	<script type="text/javascript">
		location.href = "carInOut.do"
		alert("입고된 차량입니다. ");
	</script>
</c:if>
<body>
	<form method="post" action="carInOut.do?table=in" onsubmit="return q()">
		<table border="1">
			
<!-- 			<input type="hidden" name="table" value="in"> -->
			<tr>
				<th>정기권번호</th>
				<td><input type="text" name="tno"
					value="${carInfo.ticketInfo.tno}"></td>
			</tr>
			<tr>
				<th>차량번호</th>
				<td><input type="text" name="carno"
					value="${carInfo.ticketInfo.carno}"></td>

			</tr>
			<tr>
				<th>차주전화</th>
				<td><input type="text" name="phone"
					value="${carInfo.ticketInfo.phone}"></td>
			</tr>
			<tr>
				<th>등급</th>
				<td><input type="text" name="grade"
					value="${carInfo.ticketInfo.grade}"></td>
			</tr>
			<tr>
				<th>시작일</th>
				<td><input type="text" name="startdate"
					value="${carInfo.ticketInfo.startdate}"></td>
			</tr>
			<tr>
				<th>종료일</th>
				<td><input type="text" name="enddate"
					value="${carInfo.ticketInfo.enddate}"></td>
			</tr>
			<tr>
				<th>비고</th>
				<td><input type="text" name="note"
					value="${carInfo.ticketInfo.note }"></td>
			</tr>
			<tr>
				<th>주차번호</th>
				<td><input type="text" name="pno" value="${carInfo.parkNo}"></td>
			</tr>
		</table>
		<input type="submit" value="입고확인">
	</form>

</body>
</html>