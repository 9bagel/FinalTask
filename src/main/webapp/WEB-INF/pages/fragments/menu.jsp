<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-lg-3">
    <c:if test="${not empty sessionScope.user}">
        <c:import url="/WEB-INF/pages/fragments/panel.jsp"/>
    </c:if>
    <h1 class="my-4"><fmt:message bundle="${locale}" key="text.services"/></h1>
    <div class="list-group">
        <a href="${pageContext.request.contextPath}/controller?command=service_list&service_name=haircut" class="list-group-item active"><fmt:message bundle="${locale}" key="service.haircut"/></a>
        <a href="${pageContext.request.contextPath}/controller?command=service_list&service_name=paw_care" class="list-group-item"><fmt:message bundle="${locale}" key="service.paw_care"/></a>
        <a href="${pageContext.request.contextPath}/controller?command=service_list&service_name=washing_drying" class="list-group-item"><fmt:message bundle="${locale}" key="service.washing_drying"/></a>
        <a href="${pageContext.request.contextPath}/controller?command=service_list&service_name=combing_out" class="list-group-item"><fmt:message bundle="${locale}" key="service.combing_out"/></a>
        <a href="${pageContext.request.contextPath}/controller?command=service_list&service_name=claw_trimming" class="list-group-item"><fmt:message bundle="${locale}" key="service.claw_trimming"/></a>

        <a href="${pageContext.request.contextPath}/controller?command=service_list&service_name=ear_cleaning" class="list-group-item active"><fmt:message bundle="${locale}" key="service.ear_cleaning"/></a>
        <a href="${pageContext.request.contextPath}/controller?command=service_list&service_name=eye_brushing" class="list-group-item"><fmt:message bundle="${locale}" key="service.eye_brushing"/></a>
        <a href="${pageContext.request.contextPath}/controller?command=service_list&service_name=spa" class="list-group-item"><fmt:message bundle="${locale}" key="service.spa"/></a>
        <a href="${pageContext.request.contextPath}/controller?command=service_list&service_name=teeth_cleaning" class="list-group-item"><fmt:message bundle="${locale}" key="service.teeth_cleaning"/></a>
        <a href="${pageContext.request.contextPath}/controller?command=service_list&service_name=taxi" class="list-group-item"><fmt:message bundle="${locale}" key="service.taxi"/></a>
    </div>
</div>