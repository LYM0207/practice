<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
	String table = request.getParameter("table");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>주차차량 입고,출고 관리</h3>
	차량번호
	<input type="text" name="carNo">
	<input type="button" value="주차입고" onclick="tableChange(1)">
	<input type="button" value="주차출고" onclick="tableChange(2)">
	<table border="1" width=100% height=75%>
		<tr height="340px">
			<td align="center">
				<%
					if (table == null) {
				%> 차량번호를 입력하고 입고/출고 버튼을 눌러세요<%
					} else {
				%> <jsp:include page="<%=table%>"></jsp:include> <%
 	}
 %>
			</td>
		</tr>

	</table>

</body>
<script type="text/javascript">
	function tableChange(i) {
		var value = document.getElementsByName("carNo")
		var carNo = value[0].value
		
		if (i == 1) {
			if (carNo == "") {
				alert("차량 번호를 입력해주세요")
			} else {
					location.href = "mainPage.jsp?contentPage=/view/parkingCar_Serch.jsp&table=parkingCar_In.jsp?carNo="
							+carNo;
			}
		}
		if (i == 2) {
			if (carNo == "") {
				alert("차량 번호를 입력해주세요")
			} else {

				location.href = "mainPage.jsp?contentPage=/view/parkingCar_Serch.jsp&table=parkingCar_Out.jsp?carNo="
						+ carNo
			}
		}
	}
</script>
</html>
