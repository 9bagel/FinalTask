<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale" var="locale" scope="application"/>
<html xmlns:c="http://java.sun.com/JSP/Page">
<body>
<c:import url="/WEB-INF/pages/fragments/header.jsp"/>
<div class="container">
    <h2><fmt:message bundle="${locale}" key="text.registration.form" /></h2>
    <form name="loginForm" action="controller" method="post">
        <input type="hidden" name="command" value="registration"/>
        <div class="row">
            <div class="col-md-12 form-group">
                <label class="block"><fmt:message bundle="${locale}" key="text.registration.login" /></label>
                <input type="text" pattern="^[\w-]{3,10}$" name="login" class="form-control" placeholder="<fmt:message bundle="${locale}" key="text.login" />" required>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 form-group">
                <label class="block"><fmt:message bundle="${locale}" key="text.registration.email" /></label>
                <input type="email" name="email" class="form-control" placeholder="<fmt:message bundle="${locale}" key="text.email" />" required>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 form-group">
                <label class="block"><fmt:message bundle="${locale}" key="text.registration.password" /></label>
                <input type="password" pattern="^[A-Za-z0-9]{8,15}$" name="password" class="form-control"
                       placeholder="<fmt:message bundle="${locale}" key="text.password" />" required>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 form-group">
                <label class="block"><fmt:message bundle="${locale}" key="text.registration.repeat" /></label>
                <input type="password" pattern="^[A-Za-z0-9]{8,15}$" name="passwordRepeat" class="form-control"
                       placeholder="<fmt:message bundle="${locale}" key="text.registration.repeat" />" required>
            </div>
        </div>
        <br/>
        <c:if test="${not empty errorLoginPassMessage}">
            <div class="alert alert-danger" role="alert">
                ${errorLoginPassMessage}
            </div>
        </c:if>
        <div class="row">
            <div class="col-md-12 form-group">
                <button type="button" class="btn btn-success"><fmt:message bundle="${locale}" key="button.send" /></button>
            </div>
        </div>
    </form>
</div>

</body>
</html>