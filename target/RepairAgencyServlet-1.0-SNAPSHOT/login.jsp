<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
</head>
<body>

<h1>Login to the system</h1>
<form method="post" action="app">
    <input type="hidden" name="command" value="login"/>
    <label>Your email</label>
    <input type="text" name="name"><br/>
    <p></p>
    <label>Your password</label>
    <input type="password" name="pass"><br/>
    <p></p>
    <input class="button" type="submit" value="Sign in">
</form>
<h3>${message}</h3>
<br>

</body>
</html>