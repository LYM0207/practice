<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String[] img = new String[]{"01", "02", "03", "04", "05", "06",
			"07", "08", "09"};

	String[] name = new String[]{"간고등어", "물티슈", "코스트코 머핀", "목쿠션",
			"블루투스 키보드", "케라시스1호", "케라시스 명화I-1호", "메리삭스", "동원참치"};
	String[] price = new String[]{"28,900", "8,500", "9,900", "5,900",
			"31,900", "7,300", "7,300", "8,900", "19,900"};
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script language="javascript">
	function jang(name, price) {
		location.href = "./jang_process.jsp?name=" + name + "&price=" + price;
	}

	function move_jang() {
		location.href = "./jang_baguni.jsp";
	}
</script>

</head>
<body>

	<center>
		<h2>SHOPPING MALL</h2>
		<table width="800" border="0">
			<tr>
				<td align="right" colspan="3"><input type="button"
					value="내 장바구니" onClick="move_jang()" style="border: none" /></td>
			</tr>
			<%
				for (int i = 0; i < img.length; i++) {
					if (i % 3 == 0)
						out.println("<tr>");

					out.println("<td><table border='0'>");
					out.println("<tr height='150'><td align='center'>");

					out.println("<img src='./images/" + img[i]
							+ ".jpg' width='250' height='130'/>");
					out.println("</td></tr>");
					out.println("<tr height='20'><td align='center'>");
					out.println("<b>" + name[i] + "</b></td></tr>");

					out.println("<tr height='20'><td align='center'>");
					out.println("가격 : " + price[i] + "원</td></tr>");
					out.println("<tr height='20'><td align='right'>");

					out.println("<input type='button' value='담기' ");
					out.println("onClick='jang(\"" + name[i] + "\",\"" + price[i]
							+ "\")' style='border:none'/>");
					
					out.println("</td></tr>");
					out.println("</table></td>");

					if (i % 3 == 2)
						out.println("</tr>");
				}
			
			%>
		</table>
	</center>
</body>
</html>