<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <div class="card mt-4">
                    <div class="card-body">
                        <h3 class="text-center"><fmt:message bundle="${locale}" key="text.order"/>&nbsp#${order.id}&nbsp(<fmt:message bundle="${locale}" key="${order.orderStatus.name}"/>)</h3>
                        <table class="table table-striped">
                            <tbody>
                            <tr>
                                <th>№</th>
                                <th><fmt:message bundle="${locale}" key="text.service"/></th>
                                <th><fmt:message bundle="${locale}" key="text.price"/></th>
                            </tr>
                            <c:forEach items="${services}" var="service" varStatus="loop">
                                <tr>
                                    <td>#${loop.count}</td>
                                    <td><ctg:serviceTitle service="${service}"/></td>
                                    <td>${service.price}&nbsp<fmt:message bundle="${locale}" key="text.ruble"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <p class="float-right"><fmt:message bundle="${locale}" key="text.total"/>:&nbsp${order.total}&nbsp<fmt:message bundle="${locale}" key="text.ruble"/></p>
                        <h5 class="col"><fmt:message bundle="${locale}" key="text.order.date"/>:&nbsp<fmt:formatDate type="both" value="${order.date}" /></h5>
                        <c:if test="${order.orderStatus.id == 1}">
                            <c:import url="/WEB-INF/pages/fragments/order/controlOrder.jsp"/>
                        </c:if>
                        <div class="card card-outline-secondary my-4">
                        <div class="card-header">
                            <fmt:message bundle="${locale}" key="text.leave_review"/>
                        </div>
                            <c:choose>
                                <c:when test="${not empty review}">
                                    <div class="card-body">
                                        <p>${review.message}</p>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${order.orderStatus.id == 3}">
                        <form method="POST" action="controller">
                            <input type="hidden" name="command" value="leave_review">
                            <input type="hidden" name="order_id" value="${order.id}">
                        <c:import url="/WEB-INF/pages/fragments/review/leaveReview.jsp"/>
                        </form>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
        </div>
    </div>
</div>

<script type="text/javascript">
$(function () {
$('#date').datetimepicker();});
</script>
</body>
</html>