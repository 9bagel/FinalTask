<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale" var="locale" scope="application"/>
<html>
<body>
<c:import url="/WEB-INF/pages/fragments/header.jsp"/>
<div class="container">
    <h2><fmt:message bundle="${locale}" key="text.login.form" /></h2>
    <form name="loginForm" action="controller" method="post">
        <input type="hidden" name="command" value="login"/>
        <div class="row">
            <div class="col-md-12 form-group">
                <input type="text" name="login" class="form-control" placeholder="<fmt:message bundle="${locale}" key="text.login" />" required>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 form-group">
                <input type="password" name="password" class="form-control" placeholder="<fmt:message bundle="${locale}" key="text.password" />" required>
            </div>
        </div>
        <br/>
        <c:if test="${not empty errorLoginPassMessage}">
            <div class="alert alert-danger" role="alert">
                ${sessionScope.errorLoginPassMessage}
            </div>
        </c:if>
        <c:remove var="errorLoginPassMessage" scope="session"/>
        <div class="row">
            <div class="col-md-12 form-group">
                <button type="submit" class="btn btn-success"><fmt:message bundle="${locale}" key="button.send" /></button>
            </div>
        </div>
    </form>
</div>

</body>
</html>