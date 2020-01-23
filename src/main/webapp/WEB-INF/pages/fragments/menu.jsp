<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-lg-3">
    <h1 class="my-4"><fmt:message bundle="${locale}" key="text.localisedServices"/></h1>
    <div class="list-group">
        <a href="${pageContext.request.contextPath}/controller?command=service_list&type_id=1" class="list-group-item active"><fmt:message bundle="${locale}" key="localisedService.haircut"/></a>
        <a href="${pageContext.request.contextPath}/controller?command=service_list&type_id=2" class="list-group-item"><fmt:message bundle="${locale}" key="localisedService.paw_care"/></a>
        <a href="${pageContext.request.contextPath}/controller?command=service_list&type_id=3" class="list-group-item"><fmt:message bundle="${locale}" key="localisedService.washing_drying"/></a>
        <a href="${pageContext.request.contextPath}/controller?command=service_list&type_id=4" class="list-group-item"><fmt:message bundle="${locale}" key="localisedService.combing_out"/></a>
        <a href="${pageContext.request.contextPath}/controller?command=service_list&type_id=5" class="list-group-item"><fmt:message bundle="${locale}" key="localisedService.claw_trimming"/></a>

        <a href="${pageContext.request.contextPath}/controller?command=service_list&type_id=6" class="list-group-item active"><fmt:message bundle="${locale}" key="localisedService.ear_cleaning"/></a>
        <a href="${pageContext.request.contextPath}/controller?command=service_list&type_id=7" class="list-group-item"><fmt:message bundle="${locale}" key="localisedService.eye_brushing"/></a>
        <a href="${pageContext.request.contextPath}/controller?command=service_list&type_id=8" class="list-group-item"><fmt:message bundle="${locale}" key="localisedService.spa"/></a>
        <a href="${pageContext.request.contextPath}/controller?command=service_list&type_id=9" class="list-group-item"><fmt:message bundle="${locale}" key="localisedService.teeth_cleaning"/></a>
        <a href="${pageContext.request.contextPath}/controller?command=service_list&type_id=10" class="list-group-item"><fmt:message bundle="${locale}" key="localisedService.taxi"/></a>
    </div>
</div>