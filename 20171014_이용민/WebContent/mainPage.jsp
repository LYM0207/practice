<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String contentPage = request.getParameter("contentPage");
	if (contentPage == null) {
		contentPage = "view/parkingHome.jsp";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인 화면</title>
</head>
<style>
html, body {
	margin: 0;
	padding: 0;
	height: 100%;
}

#wrapper {
	position: relative;
	min-height: 100%;
}

#header {
	height: 70px;
	background: #1712AB;
	color: white;
}

#nav {
	height: 30px;
	background: gray;
}

#section {
	padding: 20px;
}

#footer {
	position: absolute;
	bottom: 0;
	width: 100%;
	height: 70px;
	background: #1712AB;
	color: white;
}
</style>

<body>
	<div id="wrapper">
		<div id="header" align="center"><jsp:include page="header.jsp" /></div>
		<div id="nav"><jsp:include page="nav.jsp" /></div>
		<div id="section"><jsp:include page="<%=contentPage%>"></jsp:include></div>
		<div id="footer" align="center"><jsp:include page="footer.jsp" /></div>
	</div>

</body>
</html>