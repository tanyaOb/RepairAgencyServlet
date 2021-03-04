<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"
           prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager cabinet</title>
</head>
<body>
<h1>List of orders</h1>
<c:choose>
    <c:when test="${fn:length(orders) == 0}">No orders yet</c:when>

    <c:otherwise>
        <table class="table table-striped" border=1 bordercolor="#000000" cellpadding=10>
            <tr>
                <th>Client name</th>
                <th>Category</th>
                <th>Description</th>
                <th>Model</th>
                <th>State manager</th>
                <th>State master</th>
                <th>Price</th>
                <th>Master name</th>
                <th>Change status</th>
                <th>Choose master</th>
            </tr>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.userName}</td>
                    <td>${order.category}</td>
                    <td>${order.description}</td>
                    <td>${order.modelOrder}</td>
                    <td>${order.stateManager}</td>
                    <td>${order.stateMaster}</td>
                    <td>${order.orderPrice}</td>
                    <td>${order.masterId}</td>
                    <td><a href="app?command=changepaymentstatus&orderId=${order.id}&orderPrice=${order.orderPrice}">Change</a>
                    </td>
                    <td><a href="app?command=choosemaster&orderId=${order.id}&orderPrice=${order.orderPrice}">Choose</a>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
<br>
<a href="app?command=manageuseraccounts">Manage user accounts</a>
<br>
<p></p>
<a href="app?command=logout">Sign out</a>
</body>
</html>
