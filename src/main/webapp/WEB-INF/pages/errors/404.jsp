<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale" var="locale" scope="application"/>
<html>
<c:import url="/WEB-INF/pages/fragments/header.jsp"/>
<body>
<c:import url="/WEB-INF/pages/fragments/navigation.jsp"/>

<div class="container">
    <div class="row">
        <c:import url="/WEB-INF/pages/fragments/menu.jsp"/>

        <div class="col-lg-9">
            <div class="card mt-4">
                <c:if test="${not empty sessionScope.errorMessage}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message bundle="${locale}" key="${sessionScope.errorMessage}"/>
                        <c:remove var="errorMessage" scope="session"/>
                    </div>
                </c:if>
                <div class="d-flex justify-content-center">
                    <img src="${pageContext.request.contextPath}/resources/img/404.jpg" style="width: 50%; height: 50%;"/>
                </div>
                <c:remove var="errorMessage" scope="session"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>