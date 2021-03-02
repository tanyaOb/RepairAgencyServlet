<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Assign price</title>
</head>
<body>
<h2>Please assign price</h2>
<br>
<form action="${pageContext.request.contextPath}/app">
    <input type="text" name="price"><br/><p></p>
    <input type="hidden" name="command" value="assignprice"/>
    <input type="hidden" name="orderId" value="${orderId}"/>
    <input type="hidden" name="name" value="${name}"/>
    <input class="button" type="submit" value="Save">

</form>
<br/>
<br>
<a href="${pageContext.request.contextPath}/app/checkorders?name=${name}">Back to list of orders</a>
</body>
</html>
