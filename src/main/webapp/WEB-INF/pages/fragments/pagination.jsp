<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding: 10px;">

    <form role="form" action="controller" method="get" style="display: inline-block">
        <input type="hidden" name="page" value="1">
        <input class="btn btn-primary btn-sm" name="limit" type="submit" value="5">
        <input class="btn btn-primary btn-sm" name="limit" type="submit" value="10">
        <input class="btn btn-primary btn-sm" name="limit" type="submit" value="20">
    </form>
    <span><fmt:message bundle="${locale}" key="text.page"/></span>
    <form style="display: inline-block" role="form" action="controller" method="get">
        <input type="number" max="${totalPages}" min="1" name="page" placeholder="${page}/${totalPages}" required="">
        <input type="hidden" name="limit" value="${limit}">
        <input class="btn btn-primary btn-sm" type="submit" value="<fmt:message bundle="${locale}" key="text.go"/>" >
    </form>
    <c:choose>
        <c:when test="${page != 1}">
            <form style="display: inline-block" role="form" action="controller" method="get">
                <input type="hidden" name="page" value="${page - 1}">
                <input type="hidden" name="limit" value="${limit}">
                <input class="btn btn-primary btn-sm" type="submit" value="<fmt:message bundle="${locale}" key="text.back"/>">
            </form>
        </c:when>
        <c:otherwise>
            <input class="btn btn-primary btn-sm" type="submit" value="<fmt:message bundle="${locale}" key="text.back"/>" disabled>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${page < totalPages}">
            <form style="display: inline-block" role="form" action="controller" method="get">
                <input type="hidden" name="page" value="${page + 1}">
                <input type="hidden" name="limit" value="${limit}">
                <input class="btn btn-primary btn-sm" type="submit" value="<fmt:message bundle="${locale}" key="text.forward"/>">
            </form>
        </c:when>

        <c:otherwise>
            <input class="btn btn-primary btn-sm" type="submit" value="<fmt:message bundle="${locale}" key="text.forward"/>" disabled>
        </c:otherwise>
    </c:choose>
</div>