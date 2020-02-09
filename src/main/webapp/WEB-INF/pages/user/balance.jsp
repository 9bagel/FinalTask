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
                    <h3 class="text-center">
                        <fmt:message bundle="${locale}" key="text.balance"/>
                    </h3>
                    <table class="table table-striped">
                        <tbody>
                        <tr>
                            <td>
                                <fmt:message bundle="${locale}" key="text.your_balance_is"/>
                            </td>
                            <td>${sessionScope.user.balance}&nbsp
                                <fmt:message bundle="${locale}" key="text.ruble"/>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <br>
            <h5 class="text-center">
                <fmt:message bundle="${locale}" key="text.deposit"/>
            </h5>
            <div class="card mt-4">
                <div class="card-body">
                    <table class="table table-striped">
                        <tbody>
                        <tr>
                            <td>
                                <fmt:message bundle="${locale}" key="text.enter_deposit_amount"/>
                            </td>
                            <form method="POST" action="controller">
                                <input type="hidden" name="command" value="make_deposit">
                                <td>
                                    <input type="text" name="amount" class="form-control" aria-label="Small"
                                           aria-describedby="inputGroup-sizing-sm" pattern="[0-9]{,3}">
                                </td>
                                <td>
                                    <button type="submit" class="btn btn-outline-success">
                                        <fmt:message bundle="${locale}" key="button.deposit"/>
                                    </button>
                                </td>
                            </form>
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