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
            <div class="card mt-4">
                <div class="card-body">
                    <form method="POST" action="controller">
                        <input type="hidden" name="command" value="update_service">
                        <input type="hidden" name="service_id" value="${service.id}">
                        <c:import url="/WEB-INF/pages/fragments/service/updateServiceInput.jsp"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>