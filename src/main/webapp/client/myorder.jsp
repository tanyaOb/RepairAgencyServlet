<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"
           prefix="fn" %>
<html>
<head>
    <title>Client cabinet</title>
</head>
<body>
<h2>Account balance: ${balance}</h2>
<h1>My orders</h1>
<c:choose>
    <c:when test="${fn:length(orders) == 0}">No orders yet</c:when>

    <c:otherwise>
    <table class="table table-striped" border=1 bordercolor="#000000" cellpadding=10>
        <tr>
            <th>Category</th>
            <th>Description</th>
            <th>Model</th>
            <th>State manager</th>
            <th>State master</th>
            <th>Price</th>
        </tr>
      <c:forEach var = "order" items="${orders}">
          <tr>
            <td>${order.category}</td>
            <td>${order.description}</td>
            <td>${order.modelOrder}</td>
            <td>${order.stateManager}</td>
            <td>${order.stateMaster}</td>
            <td>${order.orderPrice}</td>
        </tr>
        </c:forEach>
    </table>
    </c:otherwise>
</c:choose>
<a href="${pageContext.request.contextPath}/client/neworder.jsp?name=${name}">New order</a>
<br><p></p>
<a href="${pageContext.request.contextPath}/app/logout">Sign out</a>
</body>
</html>
