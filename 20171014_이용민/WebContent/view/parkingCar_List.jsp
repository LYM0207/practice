<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.bs.dao.ParkingCar_List_Dao"%>
<%@ page import="com.bs.model.ParkingCarVO"%>
<%@ page import="java.util.ArrayList"%>

<%!ParkingCar_List_Dao listDAO = new ParkingCar_List_Dao();
	ParkingCarVO pcVO = new ParkingCarVO();%>
<%
	String update = request.getParameter("update");
	ArrayList<ParkingCarVO> list = new ArrayList<ParkingCarVO>();
	list = listDAO.getList();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	var update = <%=update%>
	if (update=="i")
		alert("[입고완료] 되었습니다.");
	if(update=="o")
		alert("[출고완료] 되었습니다.");
</script>
<body>
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
		<%
			for (int i = 0; i < list.size(); i++) {
				pcVO = list.get(i);
				int parkno = pcVO.getParkno();
				String carno = pcVO.getCarno();
				String grade = pcVO.getGrade();
				String tstat = pcVO.getTstat();
				String indate = pcVO.getIndate();
				String outdate = pcVO.getOutdate();

				if (outdate == null)
					outdate = "";
		%>
		<tr align="center">
			<td><%=parkno%></td>
			<td><%=carno%></td>
			<td><%=grade%></td>
			<td><%=tstat%></td>
			<td><%=indate%></td>
			<td><%=outdate%></td>
		</tr>
		<%
			}
		%>
	</table>

</body>
</html>