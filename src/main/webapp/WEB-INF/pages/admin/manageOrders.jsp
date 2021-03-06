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
            <c:import url="/WEB-INF/pages/fragments/messages.jsp"/>
            <div class="card-body">
                <table class="table table-striped">
                    <tbody>
                    <tr>
                        <th><fmt:message bundle="${locale}" key="text.order.id"/></th>
                        <th><fmt:message bundle="${locale}" key="text.user.id"/></th>
                        <th><fmt:message bundle="${locale}" key="text.order.date"/></th>
                        <th><fmt:message bundle="${locale}" key="text.price"/></th>
                        <th><fmt:message bundle="${locale}" key="text.status"/></th>
                        <th><fmt:message bundle="${locale}" key="text.action"/></th>
                    </tr>
                    <c:forEach items="${orders}" var="order">
                        <tr>
                            <form method="POST" action="controller">
                            <td>#${order.id}</td>
                            <td>${order.userId}</td>
                            <td><input id="date" type="text" class="form-control date" name ="date" value="<fmt:formatDate pattern = "MM/dd/yyyy hh:mm" value = "${order.date}" />"></td>
                            <td><input type="text" class="form-control" name="total" value="${order.total}" required></td>
                            <td>
                                <select class="custom-select" name="status_id">
                                    <c:forEach items="${orderStatuses}" var="status">
                                <option value="${status.id}" ${status.id == order.orderStatus.id ? 'selected' : ''}>
                                        <fmt:message bundle="${locale}" key="${status.name}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <input type="hidden" name="command" value="update_order">
                                <input type="hidden" name="order_id" value="${order.id}">
                                <button type="submit" class="btn btn-outline-success"><fmt:message bundle="${locale}" key="text.update"/></button>
                            </td>
                            </form>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
 $(function () {
$('.date').datetimepicker();});
</script>
</body>
</html>