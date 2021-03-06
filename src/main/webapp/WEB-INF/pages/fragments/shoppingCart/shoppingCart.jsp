<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<div class="card mt-4">
    <div class="card-body">
        <h3 class="text-center"><fmt:message bundle="${locale}" key="text.shopping_cart"/></h3>
        <table class="table table-striped">
            <tbody>
            <c:set var="total" value="${0}"/>
            <c:forEach items="${services}" var="service" varStatus="loop">
                <c:set var="total" value="${total + service.price}" />
                <tr>
                    <th scope="row">${loop.count}</th>
                    <td><ctg:serviceTitle service="${service}"/></td>
                    <td>${service.price} &nbsp<fmt:message bundle="${locale}" key="text.ruble"/></td>
                    <form method="POST" action="controller">
                        <input type="hidden" name="command" value="remove_from_shopping_cart">
                        <input type="hidden" name="service_id" value="${service.id}">
                        <td>
                            <button type="submit" class="btn btn-outline-danger">
                                <fmt:message bundle="${locale}" key="text.delete"/>
                            </button>
                        </td>
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<div class="card mt-4">
    <div class="card-body">
        <table class="table table-striped">
            <tbody>
            <tr>
                <th scope="row"></th>
                <td><fmt:message bundle="${locale}" key="text.total"/></td>
                <td>${total}&nbsp<fmt:message bundle="${locale}" key="text.ruble"/></td>
                <form method="POST" action="controller">
                    <input type="hidden" name="command" value="create_order">
                    <input type="hidden" name="total" value="${total}">
                    <td>
                        <input id="date" type="text" class="form-control" name ="date"  placeholder="<fmt:message bundle="${locale}" key="text.select_date_time"/>" required>
                    </td>
                    <td>
                        <button type="submit" class="btn btn-success">
                            <fmt:message bundle="${locale}" key="button.make_receipt"/>
                        </button>
                    </td>
                </form>
            </tr>
            </tbody>
        </table>

    </div>
</div>