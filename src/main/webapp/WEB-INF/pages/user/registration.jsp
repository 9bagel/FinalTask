<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale" var="locale" scope="application"/>
<html>
<c:import url="/WEB-INF/pages/fragments/header.jsp"/>
<script src="${pageContext.request.contextPath}/resources/js/validator.js"></script>
<body>
<c:import url="/WEB-INF/pages/fragments/navigation.jsp"/>
<div class="container">

    <div class="row">
        <c:import url="/WEB-INF/pages/fragments/menu.jsp"/>

        <div class="col-lg-9">
    <h2><fmt:message bundle="${locale}" key="text.registration.form" /></h2>
    <form name="signUpForm" id="signUpForm" action="controller" method="post">
        <input type="hidden" name="command" value="registration_action"/>
        <div class="row">
            <div class="col-md-12 form-group">
                <label class="block"><fmt:message bundle="${locale}" key="text.registration.login" />:</label>
                <input type="text" id="login" name="login" class="form-control" placeholder="<fmt:message bundle="${locale}" key="text.login" />" required>
                <div id="loginHintInvalid" class="hint-invalid invisible">
                    <fmt:message bundle="${locale}" key="text.login.hint" />
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 form-group">
                <label class="block"><fmt:message bundle="${locale}" key="text.registration.email" />:</label>
                <input type="email" name="email" class="form-control" placeholder="<fmt:message bundle="${locale}" key="text.email" />" required>
                <div id="emailHintInvalid" class="hint-invalid invisible">
                    <fmt:message bundle="${locale}" key="text.email.hint" />
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 form-group">
                <label class="block"><fmt:message bundle="${locale}" key="text.registration.password" />:</label>
                <input type="password" pattern="^[A-Za-z0-9]{8,15}$" name="password" class="form-control"
                       placeholder="<fmt:message bundle="${locale}" key="text.password" />" required>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 form-group">
                <label class="block"><fmt:message bundle="${locale}" key="text.registration.label.repeat" />:</label>
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
                <button type="submit" class="btn btn-success"><fmt:message bundle="${locale}" key="text.send" /></button>
            </div>
        </div>
    </form>
</div>
</div>
</div>
<script>
$("#login").keyup(function() {
isLoginValid = validateInput($(this), $("#loginHintInvalid"), "^[A-Za-z0-9]{5,20}$");
allowSubmit();});
$("#password").keyup(function() {
isPasswordValid = validateInput($(this), $("#passwordHintInvalid"), "^.{8,15}$");
allowSubmit();});
</script>
</body>
</html>