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
                        <th><fmt:message bundle="${locale}" key="text.id"/></th>
                        <th><fmt:message bundle="${locale}" key="text.user.login"/></th>
                        <th><fmt:message bundle="${locale}" key="text.email"/></th>
                        <th><fmt:message bundle="${locale}" key="text.role"/></th>
                        <th><fmt:message bundle="${locale}" key="text.balance"/></th>
                        <th><fmt:message bundle="${locale}" key="text.action"/></th>
                    </tr>
                    <c:forEach items="${users}" var="user">
                    <tr>
                        <form method="POST" action="controller">
                        <td>${user.id}</td>
                        <td><input type="text" class="form-control" name="login" value="${user.login}" required></td>
                        <td><input type="text" class="form-control" name="email" value="${user.email}" required></td>
                        <td>
                            <select class="custom-select" name="role_id">
                                <c:forEach items="${userTypes}" var="userType">
                                    <option value="${userType.id}" ${userType.id == user.userRole.id ? 'selected' : ''}>
                                    <fmt:message bundle="${locale}" key="${userType.name}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input type="text"  name="balance" class="form-control" value="${user.balance}" required></td>
                        <td>
                            <input type="hidden" name="command" value="update_user">
                            <input type="hidden" name="user_id" value="${user.id}">
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
</body>
</html>