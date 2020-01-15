<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale" var="locale" scope="application"/>
<html>
<c:import url="/WEB-INF/pages/fragments/header.jsp"/>
<body>
<c:import url="/WEB-INF/pages/fragments/navigation.jsp"/>
<!-- Modal HTML -->
<c:if test="$(empty sessionScope.userLogin}">
    <div id="login" class="modal fade">
        <div class="modal-dialog modal-login">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Member Login</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <form name="loginForm" action="controller" method="post">
                        <div class="form-group">
                            <i class="fa fa-user"></i>
                            <input type="hidden" name="command" value="login"/>
                            <input type="text" name="login" class="form-control" placeholder="Username"
                                   required="required">
                        </div>
                        <div class="form-group">
                            <i class="fa fa-lock"></i>
                            <input type="password" name="password" class="form-control" placeholder="Password"
                                   required="required">
                        </div>
                        <div class="form-group">
                            <br/> ${errorLoginPassMessage}
                            ${sessionScope.errorMessage}
                            <br/> ${wrongAction}
                            <br/> ${nullPage}
                            <br/>
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary btn-block btn-lg" value="Log in">
                        </div>
                    </form>

                </div>
                <div class="modal-footer">
                    <a href="#">Forgot Password?</a>
                </div>
            </div>
        </div>
    </div>
    <div id="register" class="modal fade">
        <div class="modal-dialog modal-login">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Registration</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <form action="./register" method="post">
                        <div class="form-group">
                            <i class="fa fa-user"></i>
                            <input type="text" name="login" class="form-control" placeholder="Username"
                                   required="required">
                        </div>
                        <div class="form-group">
                            <i class="fa fa-lock"></i>
                            <input type="password" name="password" class="form-control" placeholder="Password"
                                   required="required">
                        </div>
                        <div class="form-group">
                            <i class="fa fa-user"></i>
                            <input type="email" name="email" class="form-control" placeholder="Email"
                                   required="required">
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary btn-block btn-lg" value="Register">
                        </div>
                    </form>

                </div>
                <div class="modal-footer">
                    <a href="#">Forgot Password?</a>
                </div>
            </div>
        </div>
    </div>
</c:if>

<div class="container">

    <div class="row">
        <c:import url="/WEB-INF/pages/fragments/menu.jsp"/>
        <div class="col-lg-9">

            <c:forEach items="${services}" var="service">
                <div class="card mt-4">
                    <div class="card-body">
                        <h3 class="card-title">${service.title}</h3>
                        <h4>${service.price}</h4>
                        <p class="card-text">${service.description}</p>
                        <span class="text-warning">&#9733; &#9733; &#9733; &#9733; &#9734;</span>
                        4.0 stars
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>