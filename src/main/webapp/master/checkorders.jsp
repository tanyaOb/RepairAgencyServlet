<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"
           prefix="fn" %>
<html>
<head>
    <title>Master cabinet</title>
</head>
<body>
<h1>List of orders</h1>

<c:choose>
    <c:when test="${fn:length(orders) == 0}">No orders yet</c:when>

    <c:otherwise>
        <table class="table table-striped" border=1 bordercolor="#000000" cellpadding=10>
            <tr>
                <th>Category</th>
                <th>Description</th>
                <th>Model</th>
                <th>State master</th>
                <th>Price</th>
                <th>Change status</th>
                <th>Assign price</th>
            </tr>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.category}</td>
                    <td>${order.description}</td>
                    <td>${order.modelOrder}</td>
                    <td>${order.stateMaster}</td>
                    <td>${order.orderPrice}</td>
                    <td><a href="app?command=changeprogresstatus&orderId=${order.id}&name=${name}">Change</a></td>
                    <td><a href="app?command=assignprice&orderId=${order.id}&name=${name}">Assign</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
<br>
<a href="app?command=logout">Sign out</a>
</body>
</html>
