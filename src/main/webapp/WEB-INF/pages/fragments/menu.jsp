<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-lg-3">
    <h1 class="my-4"><fmt:message bundle="${locale}" key="text.services"/></h1>
    <div class="list-group">
        <a href="${pageContext.request.contextPath}/controller?command=service_list&service_name=haircut" class="list-group-item active"><fmt:message bundle="${locale}" key="service.haircut"/></a>
        <a href="${pageContext.request.contextPath}/controller?command=service_list&service_name=paw_care" class="list-group-item"><fmt:message bundle="${locale}" key="service.paw_care"/></a>
        <a href="#" class="list-group-item"><fmt:message bundle="${locale}" key="service.washing_drying"/></a>
    </div>
</div>