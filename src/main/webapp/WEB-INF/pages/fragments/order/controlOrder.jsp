<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="float-left">
    <form class="float-left" action="controller" method="POST">
        <input type="hidden" name="command" value="cancel_order">
        <input type="hidden" name="order_id" value="${order.id}">
        <input type="submit" class="btn btn-danger" style="margin-right: 10px;" value="<fmt:message bundle="${locale}" key="button.cancel"/>" >
    </form>
    <form class="float-left" action="controller" method="post">
        <input type="hidden" name="command" value="pay_order">
        <input type="hidden" name="order_id" value="${order.id}">
        <input type="submit" class="btn btn-success" value="<fmt:message bundle="${locale}" key="button.pay"/>" >
    </form>
</div>