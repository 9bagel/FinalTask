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
                        <input type="hidden" name="command" value="add_service">
                    <input type="text" name="title_en" class="form-control" placeholder="<fmt:message bundle="${locale}" key="text.title.en"/>" style="margin: 10px;">
                    <input type="text" name="title_ru" class="form-control" placeholder="<fmt:message bundle="${locale}" key="text.title.ru"/>" style="margin: 10px;">
                    <input type="text" name="title_by" class="form-control" placeholder="<fmt:message bundle="${locale}" key="text.title.by"/>" style="margin: 10px;">
                    <table class="table table-striped">
                        <tbody>
                        <tr>
                            <th><fmt:message bundle="${locale}" key="text.description.en"/></th>
                            <th><fmt:message bundle="${locale}" key="text.description.ru"/></th>
                            <th><fmt:message bundle="${locale}" key="text.description.by"/></th>
                        </tr>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <textarea class="form-control" name="description_en"></textarea>
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <textarea class="form-control" name="description_ru"></textarea>
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <textarea class="form-control" name="description_by"></textarea>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <input type="text" name="price" class="col-1 form-control" placeholder="Price" style="margin: 10px;">
                    <div class="float-left">
                        <select class="custom-select" name="status_id" title="<fmt:message bundle="${locale}" key="text.service.select_type"/>">
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
                    <input type="submit" class="btn btn-success" value="<fmt:message bundle="${locale}" key="text.add"/>" style="margin-left: 10px;">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>