<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="signup-form">
    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">
                ${error}
        </div>
    </c:if>
    <form:form action="/login/register" method="POST" modelAttribute="userDto">
        <h2>Sign up</h2>
        <p class="hint-text">Create your account. It's free and only takes a minute.</p>
        <div class="form-group">
            <div class="row">
                <div class="col">
                    <form:input type="text" class="form-control" path="firstName"
                                placeholder="First Name" required="required"/>
                </div>
                <div class="col">
                    <form:input type="text" class="form-control" path="lastName"
                                placeholder="Last Name" required="required"/>
                </div>
            </div>
        </div>
        <div class="form-group">
            <form:input type="text" class="form-control" path="userName" placeholder="User name"
                        required="required"/>
        </div>
        <div class="form-group">
            <form:input type="password" class="form-control" path="userDetails.password"
                        placeholder="Password" required="required"/>
        </div>
        <div class="form-group">
            <input type="password" class="form-control" name="confirmPassword"
                   placeholder="Confirm Password" required="required">
        </div>
        <div class="form-group">
            <label class="form-check-label"><input type="checkbox" required="required"> I accept the
                <a href="#">Terms of Use</a> &amp; <a href="#">Privacy Policy</a></label>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-success btn-lg btn-block">Register Now</button>
        </div>
    </form:form>
    <div class="text-center">Already have an account? <a href="/login">Sign in</a></div>
</div>
