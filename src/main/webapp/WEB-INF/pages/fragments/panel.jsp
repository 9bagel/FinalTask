<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h4 class="my-4">
    <fmt:message bundle="${locale}" key="text.panel"/>
</h4>
<div class="list-group">
    <a href="${pageContext.request.contextPath}/controller?command=basket" class="list-group-item">
        <fmt:message bundle="${locale}" key="text.basket"/>
    </a>
    <a href="${pageContext.request.contextPath}/controller?command=balance" class="list-group-item">
        <fmt:message bundle="${locale}" key="text.balance"/>
    </a>
    <a href="${pageContext.request.contextPath}/controller?command=orders" class="list-group-item">
        <fmt:message bundle="${locale}" key="text.orders"/>
    </a>
</div>