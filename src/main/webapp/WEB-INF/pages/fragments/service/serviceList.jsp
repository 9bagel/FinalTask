<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:forEach items="${services}" var="service">
    <div class="card mt-4">
        <div class="card-body">
            <c:if test="${user.userRole.id == 1}">
                <form method="POST" action="controller" class="float-right">
                    <input type="hidden" name="command" value="delete_service">
                    <input type="hidden" name="service_id" value="${service.id}">
                    <button type="submit" class="btn btn-danger"><fmt:message bundle="${locale}" key="text.delete"/></button>
                </form>
                <form method="POST" action="controller" class="float-right" style="margin-right: 5px;">
                    <input type="hidden" name="command" value="edit_service_page">
                    <input type="hidden" name="service_id" value="${service.id}">
                    <button type="submit" class="btn btn-secondary"><fmt:message bundle="${locale}" key="text.edit"/></button>
                </form>
            </c:if>
            <h3 class="card-title"><ctg:serviceTitle service="${service}"/></h3>
            <h4><fmt:message bundle="${locale}" key="text.price"/>: ${service.price}&nbsp <fmt:message bundle="${locale}" key="text.ruble"/></h4>
            <p class="card-text"><ctg:serviceDescription service="${service}"/></p>
            <c:if test="${not empty user}">
            <form method="POST" action="controller">
                <input type="hidden" name="command" value="add_to_cart">
                <input type="hidden" name="service_id" value="${service.id}">
                <button type="submit" class="btn btn-primary" ><fmt:message bundle="${locale}" key="button.addToCart"/></button>
            </form>
            </c:if>
        </div>
    </div>
</c:forEach>