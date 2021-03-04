<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Assign price</title>
</head>
<body>
<h2>Please assign price</h2>
<br>
<form method="post" action="app">
    <input type="text" name="price"><br/>
    <p></p>
    <input type="hidden" name="command" value="assignprice"/>
    <input type="hidden" name="orderId" value="${orderId}"/>
    <input type="hidden" name="name" value="${name}"/>
    <input class="button" type="submit" value="Save">

</form>
<h3>${message}</h3>
<br>
<a href="app?command=checkorders&name=${name}">Back to list of orders</a>
</body>
</html>
