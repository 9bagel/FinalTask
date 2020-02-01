<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <c:forEach items="${services}" var="service">
                <div class="card mt-4">
                    <div class="card-body">
                        <h3 class="card-title">${service.title}</h3>
                        <h4><fmt:message bundle="${locale}" key="text.price"/>: ${service.price}&nbsp <fmt:message bundle="${locale}" key="text.ruble"/></h4>
                        <p class="card-text">${service.description}</p>
                        <form method="get" action="controller">
                            <input type="hidden" name="command" value="add_to_cart">
                            <input type="hidden" name="service_id" value="${service.id}">
                            <button type="submit" class="btn btn-primary" ><fmt:message bundle="${locale}" key="button.addToCart"/></button>
                        </form>

                        <span class="text-warning">&#9733; &#9733; &#9733; &#9733; &#9734;</span>
                        4.0 stars
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>