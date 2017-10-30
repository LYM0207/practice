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

<body>
	<div id="wrapper">
		<div id="header" align="center"><jsp:include page="/WEB-INF/view/header.jsp" /></div>
		<div id="nav"><jsp:include page="/WEB-INF/view/nav.jsp" /></div>
		<div id="section"><jsp:include page="/WEB-INF/view/homeForm.jsp"></jsp:include></div>
		<div id="footer" align="center"><jsp:include page="/WEB-INF/view/footer.jsp" /></div>
	</div>
</body>
</html>