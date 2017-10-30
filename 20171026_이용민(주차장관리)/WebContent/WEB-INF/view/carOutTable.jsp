<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function q() {
		if (confirm("[차량출고] 하시겠습니까?") == true) {
			return true;
		} else {
			return false;
		}
	}
</script>
<c:if test="${carInfo.car.tstat=='O'}">
	<script type="text/javascript">
		location.href = "carInOut.do"
		alert("출고된 차량입니다.")
	</script>
</c:if>
<c:if test="${carInfo.car==null}">
	<script type="text/javascript">
		location.href = "carInOut.do"
		alert("입고된 차량이 아닙니다.")
	</script>
</c:if>
<body>
	<form method="post" action="carInOut.do?table=out"
		onsubmit="return q()">
		<table border="1">
			<tr>
				<th>주차번호</th>
				<td><input type="text" name="parkno"
					value="${carInfo.car.parkno}"></td>
			</tr>
			<tr>
				<th>차량번호</th>
				<td><input type="text" value="${carInfo.car.carno}"></td>
			</tr>
			<tr>
				<th>등급</th>
				<td><input type="text" value="${carInfo.car.grade}"></td>
			</tr>
			<tr>
				<th>상태</th>
				<td><input type="text" value="${carInfo.car.tstat}"></td>
			</tr>
			<tr>
				<th>입고일시</th>
				<td><input type="text" value="${carInfo.car.indate}"></td>
			</tr>
			<tr>
				<th>출고일시</th>
				<td><input type="text" value=""></td>
			</tr>
			<tr>
				<th>비고</th>
				<c:if test="${carInfo.car.tstat=='I'}"><td><input type="text" value="출고준비중입니다."></td></c:if>
				<c:if test="${carInfo.car.tstat=='O'}"><td><input type="text" value="출고되었습니다."></td></c:if>
			</tr>
		</table>
		<input type="submit" value="출고확인">
	</form>

</body>
</html>