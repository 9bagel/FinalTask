<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
    <input type="text" value="${service.titleEn}" name="title_en" class="form-control" placeholder="<fmt:message bundle="${locale}" key="text.title.en"/>" style="margin: 10px;">
    <textarea class="form-control" name="description_en" placeholder="<fmt:message bundle="${locale}" key="text.description.en"/>" style="margin: 10px;">
    ${service.descriptionEn}
    </textarea>
</div>
<hr>
<div>
    <input type="text" value="${service.titleRu}" name="title_ru" class="form-control" placeholder="<fmt:message bundle="${locale}" key="text.title.ru"/>" style="margin: 10px;">
    <textarea class="form-control" name="description_ru" placeholder="<fmt:message bundle="${locale}" key="text.description.ru"/>"style="margin: 10px; height: 61px;">
    ${service.descriptionRu}
    </textarea>
</div>
<hr>
<div>
    <input type="text" value="${service.titleBy}" name="title_by" class="form-control" placeholder="<fmt:message bundle="${locale}" key="text.title.by"/>" style="margin: 10px;">
    <textarea class="form-control" name="description_by" placeholder="<fmt:message bundle="${locale}" key="text.description.by"/>"style="margin: 10px; height: 43px;">
    ${service.descriptionBy}
    </textarea>
</div>
<hr>
<input type="text" value="${service.price}" name="price" class="col-2 form-control" placeholder="<fmt:message bundle="${locale}" key="text.price"/>" style="margin: 10px;">
<div class="float-left" style="margin-left: 10px;">
    <select class="custom-select" name="service_type_id">
        <c:forEach items="${serviceTypes}" var="serviceType">
            <option value="${serviceType.id}" ${serviceType.id == service.serviceType.id ? 'selected' : ''}>
            <fmt:message bundle="${locale}" key="${serviceType.name}"/>
            </option>
        </c:forEach>
    </select>

</div>
<input type="submit" class="btn btn-success" value="<fmt:message bundle="${locale}" key="text.send"/>" style="margin-left: 10px;">