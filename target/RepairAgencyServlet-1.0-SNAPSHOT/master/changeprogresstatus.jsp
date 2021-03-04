<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change progress status</title>
</head>
<body>
<h2>Please change progress status</h2>
<form method="post" action="${pageContext.request.contextPath}/app">
    <input type="hidden" name="command" value="changeprogresstatus"/>
    <input type="hidden" name="orderId" value="${orderId}"/>
    <input type="hidden" name="name" value="${name}"/>
    <select name="status">
        <option>Done</option>
        <option>In_progress</option>
    </select>
    <p></p>
    <div><input type="submit" value="Save"/></div>
</form>
<h3>${message}</h3>
<br>
<a href="app?command=checkorders&name=${name}">Back to list of orders</a>
</body>
</html>
