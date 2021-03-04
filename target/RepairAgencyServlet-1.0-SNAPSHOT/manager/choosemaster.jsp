<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Choose master</title>
</head>
<body>
<h2>Please choose master for this order</h2>
<form method="post" action="${pageContext.request.contextPath}/app">
    <input type="hidden" value="choosemaster" name="command">
    <input type="hidden" value="${orderId}" name="orderId">
    <input type="hidden" value="Master successfully chosen" name="message">
    <select name="master">
        <c:forEach items="${masters}" var="master">
            <option value="${master.userName}">
                    ${master.userName}
            </option>
        </c:forEach>
    </select>
    <p></p>
    <div><input type="submit" class="custom_button" value="Save"/></div>
</form>
<h3>${message}</h3>
<br>
<a href="app?command=manageorders">Back to list of orders</a>
</body>
</html>
