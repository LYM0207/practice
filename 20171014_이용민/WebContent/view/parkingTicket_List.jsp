<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.bs.dao.ParkingTicket_List_Dao"%>
<%@ page import="com.bs.model.ParkingTicketVO"%>
<%@ page import="java.util.ArrayList"%>

<%!ParkingTicket_List_Dao listDAO = new ParkingTicket_List_Dao();
	ParkingTicketVO ptVO = new ParkingTicketVO();
	ArrayList<ParkingTicketVO> list = new ArrayList<ParkingTicketVO>();%>
<%
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
	<h3 align="center">정기권 조회</h3>
	<table border="1" align="center">
		<tr>
			<th>정기권번호</th>
			<th>차량번호</th>
			<th>차주전화</th>
			<th>주차등급</th>
			<th>정기권상태</th>
			<th>주차시작일</th>
			<th>주차종료일</th>
		</tr>
		<%
			list = listDAO.getList();
			for (int i = 0; i < list.size(); i++) {
				ptVO = list.get(i);
				int tno = ptVO.getTno();
				String carno = ptVO.getCarno();
				String phone = ptVO.getPhone();
				String grade = ptVO.getGrade();
				String tstat = ptVO.getTstat();
				String startdate = ptVO.getStartdate();
				String enddate = ptVO.getEnddate();

				if (grade.equalsIgnoreCase("M"))
					grade = "월회원";
				if (grade.equalsIgnoreCase("Y"))
					grade = "연회원";
		%>
		<tr align="center">
			<td><%=tno%></td>
			<td><%=carno%></td>
			<td><%=phone%></td>
			<td><%=grade%></td>
			<td><%=tstat%></td>
			<td><%=startdate%></td>
			<td><%=enddate%></td>
		</tr>
		<%
			}
		%>

	</table>
</body>
</html>