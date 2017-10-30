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
	function tableChange(button) {

		var carNo = document.getElementsByName("carNo");

		if (button == "in") {
			location.href = "carInOut.do?carNo=" + carNo[0].value + "&table=in";
		} else if (button == "out") {
			location.href = "carInOut.do?carNo=" + carNo[0].value
					+ "&table=out";
		}
	}

</script>
<body>
	<div id="wrapper">
		<div id="header" align="center">
			<jsp:include page="/WEB-INF/view/header.jsp" /></div>
		<div id="nav">
			<jsp:include page="/WEB-INF/view/nav.jsp" /></div>
		<div id="section">
			<h3>주차차량 입고, 출고 관리</h3>
			차량번호 <input type="text" name="carNo"> <input type="button"
				value="주차입고" onclick="tableChange('in')"> <input
				type="button" value="주차출고" onclick="tableChange('out')">
			<table border="1" width=90% height=50%>
				<tr height="340px">
					<td align="center"><c:if test="${param.table==null }">차량번호를 입력하고, 버튼을 눌러주세요</c:if>
						<c:if test="${param.table=='in'}">입고<jsp:include
								page="carInTable.jsp"></jsp:include></c:if> <c:if
							test="${param.table=='out'}">출고<jsp:include
								page="carOutTable.jsp"></jsp:include></c:if></td>
				</tr>
			</table>
			<div id="footer" align="center"><jsp:include
					page="/WEB-INF/view/footer.jsp" /></div>
		</div>
	</div>
</body>
</html>