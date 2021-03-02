<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add money to user account</title>
</head>
<body>
<h2>Please add money to user account</h2>
<br>
<form action="${pageContext.request.contextPath}/app">
    <input type="text" name="ammount"><br>
    <input type="hidden" name="command" value="addmoney"/>
    <input type="hidden" name="name" value="${name}"/>
    <input class="button" type="submit" value="Save">
</form>
<br/>
<br>
<a href="${pageContext.request.contextPath}/app/manageuseraccounts">Back to user's accounts</a>
</body>
</html>
