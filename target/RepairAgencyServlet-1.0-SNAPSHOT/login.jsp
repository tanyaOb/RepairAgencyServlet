<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
</head>
<body>

<h1>Login to the system</h1>
<form method="get" action="${pageContext.request.contextPath}/app/login">
    <input type="text" name="name"><br/><p></p>
    <input type="password" name="pass"><br/><p></p>
    <input class="button" type="submit" value="Sign in">

</form>
<br/>
<a href="${pageContext.request.contextPath}/app/logout">Sign out</a>

</body>
</html>