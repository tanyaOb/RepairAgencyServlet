<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"
           prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User's accounts</title>
</head>
<body>
<h2>User's accounts</h2>
<c:choose>
    <c:when test="${fn:length(orders) == 0}">No orders yet</c:when>

    <c:otherwise>
        <table class="table table-striped" border=1 bordercolor="#000000" cellpadding=10>
            <tr>
                <th>Client name</th>
                <th>Account balance</th>
                <th>Refill balance</th>
            </tr>
            <c:forEach var = "order" items="${orders}">
                <tr>
                    <td>${order.userName}</td>
                    <td>${order.account}</td>
                    <td><a href="${pageContext.request.contextPath}/app/addmoney?name=${order.userName}">Change</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
<br>
<a href="${pageContext.request.contextPath}/app/manageorders">Back to list of orders</a>
</body>
</html>
