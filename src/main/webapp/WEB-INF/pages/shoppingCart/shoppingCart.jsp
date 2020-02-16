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
                <c:choose>
                    <c:when test="${not empty services}">
                <c:import url="/WEB-INF/pages/fragments/shoppingCart/shoppingCart.jsp"/>
                    </c:when>
                    <c:otherwise>
            <div class="card mt-4">
                <div class="card-body">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message bundle="${locale}" key="text.shopping_cart.empty"/>
                    </div>
                </div>
            </div>
                    </c:otherwise>
                </c:choose>
        </div>
    </div>
    <script type="text/javascript">
                                    $(function () {
                                    $('#date').datetimepicker();});
                                 </script>
</div>
</body>
</html>