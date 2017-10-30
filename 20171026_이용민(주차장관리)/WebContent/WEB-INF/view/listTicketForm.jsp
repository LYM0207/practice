<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	if ("${insertSuccess}") {
		alert("등록이 완료 되었습니다");
	}
</script>
<body>
	<div id="wrapper">
		<div id="header" align="center"><jsp:include
				page="/WEB-INF/view/header.jsp" /></div>
		<div id="nav"><jsp:include page="/WEB-INF/view/nav.jsp" /></div>
		<div id="section">
			<h3 align="center">정기권리스트</h3>
			<table border="1" align="center">
				<tr>
					<th>정기권번호</th>
					<th>자량번호</th>
					<th>차주전화</th>
					<th>주차등급</th>
					<th>정기권상태</th>
					<th>주차시작일</th>
					<th>주차종료일</th>
				</tr>
				<c:forEach var="ticket" items="${ticketList}">
					<tr align="center">
						<td>${ticket.tno}</td>
						<td>${ticket.carno}</td>
						<td>${ticket.phone}</td>
						<td>${ticket.grade}</td>
						<td>${ticket.tstat}</td>
						<td>${ticket.startdate}</td>
						<td>${ticket.enddate}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div id="footer" align="center"><jsp:include
				page="/WEB-INF/view/footer.jsp" /></div>
	</div>

</body>
</html>