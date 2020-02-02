<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${not empty sessionScope.successMessage}">
    <div class="alert alert-success" role="alert">
        <fmt:message bundle="${locale}" key="${sessionScope.successMessage}"/>
        <c:remove var="successMessage" scope="session"/>
    </div>
</c:if>
<c:if test="${not empty sessionScope.errorMessage}">
    <div class="alert alert-danger" role="alert">
        <fmt:message bundle="${locale}" key="${sessionScope.errorMessage}"/>
        <c:remove var="errorMessage" scope="session"/>
    </div>
</c:if>