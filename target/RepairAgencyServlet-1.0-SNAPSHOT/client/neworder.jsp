<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"
           prefix="fn" %>
<html>
<head>
    <title>New order</title>
</head>
<body>
<div>
    <h2>Create new order</h2>
    <form action="${pageContext.request.contextPath}/app">
        <input type="hidden" name="command" value="neworder"/>
        <input type="hidden" name="name" value="${name}"/>
        <select name="category">
            <option>Laptop</option>
            <option>Smartphone</option>
            <option>PC</option>
            <option>Tablet</option>
            <option>Smart_TV</option>
        </select>
        <p></p>
        <label>Model: <input type="text" name="modelOrder"></label>
        <label>Description: <input type="text" name="description"></label>
        <p></p>
        <div><input type="submit" value="Create order"/></div>
    </form>
</div>
<h3>${message}</h3>
<a href="${pageContext.request.contextPath}/app/myorder?name=${name}">My orders</a>
<br><p></p>
<a href="${pageContext.request.contextPath}/app/logout">Sign out</a>
</body>
</html>
