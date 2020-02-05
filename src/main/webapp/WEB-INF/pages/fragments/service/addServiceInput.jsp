<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
    <input type="text" name="title_en" class="form-control" placeholder="<fmt:message bundle="${locale}" key="text.title.en"/>" style="margin: 10px;">
    <textarea class="form-control" name="description_en" placeholder="<fmt:message bundle="${locale}" key="text.description.en"/>" style="margin: 10px;"></textarea>
</div>
<hr>
<div>
    <input type="text" name="title_ru" class="form-control" placeholder="<fmt:message bundle="${locale}" key="text.title.ru"/>" style="margin: 10px;">
    <textarea class="form-control" name="description_ru" placeholder="<fmt:message bundle="${locale}" key="text.description.ru"/>"style="margin: 10px; height: 61px;"></textarea>
</div>
<hr>
<div>
    <input type="text" name="title_by" class="form-control" placeholder="<fmt:message bundle="${locale}" key="text.title.by"/>" style="margin: 10px;">
    <textarea class="form-control" name="description_by" placeholder="<fmt:message bundle="${locale}" key="text.description.by"/>"style="margin: 10px; height: 43px;"></textarea>
</div>
<hr>
<input type="text" name="price" class="col-2 form-control" placeholder="<fmt:message bundle="${locale}" key="text.price"/>" style="margin: 10px;">
<div class="float-left" style="margin-left: 10px;">
    <select class="custom-select" name="service_type_id" title="<fmt:message bundle="${locale}" key="text.service.select_type"/>">
        <option value="1"><fmt:message bundle="${locale}" key="service.haircut"/></option>
        <option value="2"><fmt:message bundle="${locale}" key="service.paw_care"/></option>
        <option value="3"><fmt:message bundle="${locale}" key="service.washing_drying"/></option>
        <option value="4"><fmt:message bundle="${locale}" key="service.combing_out"/></option>
        <option value="5"><fmt:message bundle="${locale}" key="service.claw_trimming"/></option>
        <option value="6"><fmt:message bundle="${locale}" key="service.ear_cleaning"/></option>
        <option value="7"><fmt:message bundle="${locale}" key="service.eye_brushing"/></option>
        <option value="8"><fmt:message bundle="${locale}" key="service.spa"/></option>
        <option value="9"><fmt:message bundle="${locale}" key="service.teeth_cleaning"/></option>
        <option value="10"><fmt:message bundle="${locale}" key="service.taxi"/></option>
    </select>
</div>
<input type="submit" class="btn btn-success" value="<fmt:message bundle="${locale}" key="text.send"/>" style="margin-left: 10px;">