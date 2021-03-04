<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Registration form</title>
</head>
<body>
<h1>
    Please register to the system! <br/>
</h1>

<form method="post" action="${pageContext.request.contextPath}/app">
    <input type="hidden" name="command" value="registration"/>
    <select name="role">
        <option>Client</option>
        <option>Manager</option>
        <option>Master</option>
    </select><br>
    <p></p>
    <label>Your email</label>
    <input type="text" name="name"><br/>
    <p></p>
    <label>Your password</label>
    <input type="password" name="pass"><br/>
    <p></p>
    <input class="button" type="submit" value="Register">
</form>
<br/>
<a href="${pageContext.request.contextPath}/app/login?command=login">Sign in</a>

</body>
</html>