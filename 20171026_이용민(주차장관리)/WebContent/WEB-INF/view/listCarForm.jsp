<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<c:if test="${success}">
	<script type="text/javascript">
		alert("처리가 완료 되었습니다.");
	</script>
</c:if>
<body>
	<div id="wrapper">
		<div id="header" align="center"><jsp:include
				page="/WEB-INF/view/header.jsp" /></div>
		<div id="nav"><jsp:include page="/WEB-INF/view/nav.jsp" /></div>
		<div id="section">
			<h3 align="center">주차현황조회</h3>
			<table border="1" align="center">
				<tr>
					<th>주차번호</th>
					<th>차량번호</th>
					<th>주차등급</th>
					<th>상태</th>
					<th>입고일시</th>
					<th>출고일시</th>
				</tr>
				<c:forEach var="list" items="${listCar}">
					<tr align="center">
						<td>${list.parkno}</td>
						<td>${list.carno}</td>
						<td>${list.grade}</td>
						<td>${list.tstat}</td>
						<td>${list.indate}</td>
						<td>${list.outdate}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div id="footer" align="center"><jsp:include
				page="/WEB-INF/view/footer.jsp" /></div>
	</div>

</body>
</html>
