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
                <div class="card-body">
                    <h3 class="text-center"><fmt:message bundle="${locale}" key="text.basket"/></h3>
                    <table class="table table-striped">
                        <tbody>
                        <c:set var="total" value="${0}"/>
                        <c:forEach items="${services}" var="service" varStatus="loop">
                            <c:set var="total" value="${total + service.price}" />
                            <tr>
                                <th scope="row">${loop.count}</th>
                                <td>${service.title}</td>
                                <td>${service.price} <fmt:message bundle="${locale}" key="text.ruble"/></td>
                                <form method="POST" action="controller">
                                    <input type="hidden" name="command" value="remove_from_basket">
                                    <input type="hidden" name="service_id" value="${service.id}">
                                    <td>
                                        <button type="submit" class="btn btn-outline-danger">
                                            <fmt:message bundle="${locale}" key="button.remove"/>
                                        </button>
                                    </td>
                                </form>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="card mt-4">
                <div class="card-body">
                    <table class="table table-striped">
                        <tbody>
                        <tr>
                            <th scope="row"></th>
                            <td><fmt:message bundle="${locale}" key="text.total"/></td>
                            <td>${total}<fmt:message bundle="${locale}" key="text.ruble"/></td>
                            <td>
                                <button type="submit" class="btn btn-success"><fmt:message bundle="${locale}" key="button.pay"/></button>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>