<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="ctg" uri="customtags" %>

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
            <c:import url="/WEB-INF/pages/fragments/messages.jsp"/>
            <c:import url="/WEB-INF/pages/fragments/pagination.jsp"/>
            <c:import url="/WEB-INF/pages/fragments/service/serviceList.jsp"/>
        </div>
    </div>
</div>
</body>
</html>