<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.bs.dao.ParkingTicket_Register_Dao"%>

<%!ParkingTicket_Register_Dao ptDao = new ParkingTicket_Register_Dao();%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function endDate(selectList) {
		var endDate = document.getElementsByName("enddate");

		if (selectList == "0") {
			endDate[0].value =
<%=ptDao.getEndDate("M")%>
	}
		if (selectList == "1") {
			endDate[0].value =
<%=ptDao.getEndDate("Y")%>
	}
	}

	function textCheck() {

		var carnoText = document.getElementsByName("carno");
		var phoneText = document.getElementsByName("phone");
		var gradeText = document.getElementsByName("grade");
		var tstatText = document.getElementsByName("tstat");

		if (carnoText[0].value == "" || phoneText[0].value == ""
				|| gradeText[0].checked == false
				&& gradeText[1].checked == false
				|| tstatText[0].checked == false
				&& tstatText[1].checked == false) {
			alert("데이터가 입력되지 않았습니다.")
			return false;
		}else{
			alert("정기주차권 신청이 완료되었습니다.")
			return true;
		}

	}
</script>
<body>
	<h3 align="center">주차 정기권 등록</h3>
	<form method="post" action="register" name="registerFrom" onsubmit="return textCheck()" >
		<table border="1" align="center">
			<tr>
				<th>정기권번호(자동발생)</th>
				<td><input type="text" name="tno"
					value="<%=ptDao.getAutoNum()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<th>차량번호</th>
				<td><input type="text" name="carno"></td>
			</tr>
			<tr>
				<th>차주전화</th>
				<td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<th>주차등급(M/Y)</th>
				<td><input type="radio" name="grade" value="M"
					onclick="endDate('0');">M <input type="radio" name="grade"
					value="Y" onclick="endDate('1');">Y</td>
			</tr>
			<tr>
				<th>정기권상태(Y/N)</th>
				<td><input type="radio" name="tstat" value="Y">Y <input
					type="radio" name="tstat" value="N">N</td>
			</tr>
			<tr>
				<th>시작일</th>
				<td><input type="text" name="startdate"
					value="<%=ptDao.getStartDate()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<th>종료일</th>
				<td><input type="text" name="enddate" readonly="readonly"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="등록"
					> <input type="button" value="조회"></td>
			</tr>

		</table>
	</form>
</body>

</html>