<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale" var="locale" scope="application"/>
<html>
<c:import url="/WEB-INF/pages/fragments/header.jsp"/>
<script src="${pageContext.request.contextPath}/resources/js/validator.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/login.js"></script>
<body>
<c:import url="/WEB-INF/pages/fragments/navigation.jsp"/>
<div class="container">
    <div class="row">
        <c:import url="/WEB-INF/pages/fragments/menu.jsp"/>
        <div class="col-lg-9">
    <h2><fmt:message bundle="${locale}" key="text.login.form" /></h2>
    <form name="loginForm" id="loginForm" action="controller" method="post">
        <input type="hidden" name="command" value="login_action"/>
        <div class="row">
            <div class="col-md-12 form-group">
                <input type="text" name="login" id="login" class="form-control" placeholder="<fmt:message bundle="${locale}" key="text.login" />" required>
                <div id="loginHintInvalid" class="hint-invalid invisible">
                    <fmt:message bundle="${locale}" key="text.login.hint" />
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 form-group">
                <input type="password" name="password" id="password" class="form-control" placeholder="<fmt:message bundle="${locale}" key="text.password" />" required>
                <div id="passwordHintInvalid" class="hint-invalid invisible">
                    <fmt:message bundle="${locale}" key="text.password.hint" />
                </div>
            </div>
        </div>
        <c:import url="/WEB-INF/pages/fragments/messages.jsp"/>
        <div class="row">
            <div class="col-md-12 form-group">
                <button type="submit" id="buttonSubmit" class="btn btn-success"><fmt:message bundle="${locale}" key="text.send" /></button>
            </div>
        </div>
    </form>
</div>
</div>
</div>
</body>
</html>