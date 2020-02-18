<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">
            <fmt:message bundle="${locale}" key="title"/>
        </a>
        <a href="${pageContext.request.contextPath}/controller?command=change_locale&lang=ru_RU">RU</a>
        <a href="${pageContext.request.contextPath}/controller?command=change_locale&lang=en_US">ENG</a>
        <a href="${pageContext.request.contextPath}/controller?command=change_locale&lang=be_BY">BY</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <h3 class="welcome">${sessionScope.user.login}</h3>
                        <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=logout">
                            <fmt:message bundle="${locale}" key="text.logout"/>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item active">
                            <a class="nav-link" href="${pageContext.request.contextPath}/controller/login">
                                <fmt:message bundle="${locale}" key="text.signin"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/controller/registration">
                                <fmt:message bundle="${locale}" key="text.signup"/>
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
            <form method="POST" action="controller" class="form-inline my-2 my-lg-0" >
                <input type="hidden" name="command" value="search">
                <input class="form-control mr-sm-2" type="search" name="searchPhrase" placeholder="<fmt:message bundle="${locale}" key="text.search"/>" aria-label="Search">
                <button class="btn btn-outline-primary my-2 my-sm-0" type="submit"><fmt:message bundle="${locale}" key="text.search"/></button>
            </form>
        </div>
    </div>
</nav>