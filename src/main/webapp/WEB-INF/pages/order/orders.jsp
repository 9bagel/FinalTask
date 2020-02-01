<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <div class="card-body">
                    <table class="table table-striped">
                        <tbody>
                        <tr>
                            <th><fmt:message bundle="${locale}" key="text.order.id"/></th>
                            <th><fmt:message bundle="${locale}" key="text.order.date"/></th>
                            <th><fmt:message bundle="${locale}" key="text.price"/></th>
                            <th><fmt:message bundle="${locale}" key="text.status"/></th>
                            <th><fmt:message bundle="${locale}" key="text.details"/></th>
                        </tr>
                        <c:forEach items="${orders}" var="order">
                        <tr>
                            <td>#${order.id}</td>
                            <td><fmt:formatDate type="both" value="${order.date}" /></td>
                            <td>${order.total} &nbsp<fmt:message bundle="${locale}" key="text.ruble"/></td>
                            <td><fmt:message bundle="${locale}" key="${order.orderStatus.name}"/></td>
                            <c:if test="${order.orderStatus.id != 5}">
                            <td>
                            <form method="GET" action="controller">
                            <input type="hidden" name="command" value="order_details">
                            <input type="hidden" name="order_id" value="${order.id}">
                                <button type="submit" class="btn btn-outline-success"><fmt:message bundle="${locale}" key="text.order_details"/></button>
                            </form>
                            </td>
                            </c:if>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
        </div>
    </div>
</div>
</body>
</html>