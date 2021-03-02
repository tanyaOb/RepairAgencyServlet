<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change payment status</title>
</head>
<body>
<div>
    <h2>Please change payment status</h2>
    <form action="${pageContext.request.contextPath}/app">
        <input type="hidden" name="command" value="changepaymentstatus"/>
        <input type="hidden" name="orderPrice" value="${orderPrice}"/>
        <input type="hidden" name="orderId" value="${orderId}"/>
        <select name="status">
            <option>Paid</option>
            <option>Canceled</option>
            <option>Waiting_for_payment</option>
        </select>
        <p></p>
        <div><input type="submit" value="Save"/></div>
    </form>
</div>
<br>
<h3>${message}</h3>
<br>
<a href="${pageContext.request.contextPath}/app/manageorders">Back to list of orders</a>
</body>
</html>
