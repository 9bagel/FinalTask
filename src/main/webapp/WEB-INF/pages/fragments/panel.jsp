<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${user.userRole.id == 1}">
    <h4 class="my-4">Admin panel</h4>
    <div class="list-group">
        <a href="/FinalTask/controller?command=manage_orders" class="list-group-item">Manage orders</a>
        <a href="/FinalTask/controller?command=manage_users" class="list-group-item">Manage users</a>
        <a href="/FinalTask/controller?command=create_service" class="list-group-item">Create new service</a>

    </div>
</c:if>

<h4 class="my-4">
    <fmt:message bundle="${locale}" key="text.panel"/>
</h4>
<div class="list-group">
    <a href="${pageContext.request.contextPath}/controller?command=shopping_cart" class="list-group-item">
        <fmt:message bundle="${locale}" key="text.shopping_cart"/>
        <span class="badge badge-primary badge-pill">${sessionScope.shopping_cart.count}</span>
    </a>
    <a href="${pageContext.request.contextPath}/controller?command=balance" class="list-group-item">
        <fmt:message bundle="${locale}" key="text.balance"/>
    </a>
    <a href="${pageContext.request.contextPath}/controller?command=show_orders" class="list-group-item">
        <fmt:message bundle="${locale}" key="text.orders"/>
    </a>
</div>