<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원제 게시판</title>
</head>
<body>

<u:isLogin>
	${authUser.name} 님 안녕하세요.
	<br/><a href="logout.do">[로그아웃하기]</a>
	<br/><a href="changePwd.do">[암호변경하기]</a>
	<br/><a href="article/list.do">[게시판]</a>
</u:isLogin>

<u:notLogin>
	<a href="join.do">[회원가입하기]</a>
	<a href="login.do">[로그인하기]</a>
</u:notLogin>

<br/>
</body>
</html>