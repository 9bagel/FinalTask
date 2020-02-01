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
                <div class="card mt-4">
                    <div class="card-body">
                        <h3 class="text-center">Заказ #322</h3>
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
                                    <td>${service.title}</td>
                                    <td>${service.price}&nbsp<fmt:message bundle="${locale}" key="text.ruble"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <p class="float-right">Итого: 112 руб</p>
                        <h5 class="col">Время записи: 26.01.2020 11:44</h5>
                        <div class="float-left">
                            <form class="float-left" action="controller" method="post">
                                <input type="hidden" name="command" value="cancel_order">
                                <input type="hidden" name="order_id" value="1">
                                <input type="submit" class="btn btn-danger" style="margin-right: 10px;" value="<fmt:message bundle="${locale}" key="button.cancel"/>" >
                            </form>
                            <form class="float-left" action="controller" method="post">
                            <input type="hidden" name="command" value="pay_order">
                            <input type="hidden" name="order_id" value="1">
                            <input type="submit" class="btn btn-success" value="<fmt:message bundle="${locale}" key="button.pay"/>" >
                            </form>
                        </div>
                    </div>
                </div>
        </div>
    </div>
</div>
</body>
</html>