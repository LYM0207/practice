<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
</head>

<body>
<h3>회 원 가 입</h3>
	<form action="registerProcess.jsp" method="post">
		ID: <input type="text" name="id"><br> PW: <input
			type="password" name="pw"><br> NAME: <input type="text"
			name="name"><br> TEL : <input type="text" name="tel"><br>
		<input type="submit" value="회원가입">
	</form>
</body>
</html>