<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add money to user account</title>
</head>
<body>
<h2>Please add money to user account</h2>
<br>
<form method="post" action="${pageContext.request.contextPath}/app">
    <input type="text" name="ammount"><br>
    <input type="hidden" name="command" value="addmoney"/>
    <input type="hidden" name="name" value="${name}"/><br>
    <input class="button" type="submit" value="Save">
</form>
<h3>${message}</h3>
<br>
<a href="app?command=manageuseraccounts">Back to user's accounts</a>
</body>
</html>
