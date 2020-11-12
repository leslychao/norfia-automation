<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="signup-form">
    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">
                ${error}
        </div>
    </c:if>
    <a id="add_row" class="float-right" href="/home/dictionaryform">
        <svg width="2em" height="2em" viewBox="0 0 16 16"
             class="bi bi-capslock" fill="currentColor"
             xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd"
                  d="M7.27 1.047a1 1 0 0 1 1.46 0l6.345 6.77c.6.638.146 1.683-.73 1.683H11.5v1a1 1 0 0 1-1 1h-5a1 1 0 0 1-1-1v-1H1.654C.78 9.5.326 8.455.924 7.816L7.27 1.047zM14.346 8.5L8 1.731 1.654 8.5H4.5a1 1 0 0 1 1 1v1h5v-1a1 1 0 0 1 1-1h2.846zm-9.846 5a1 1 0 0 1 1-1h5a1 1 0 0 1 1 1v1a1 1 0 0 1-1 1h-5a1 1 0 0 1-1-1v-1zm6 0h-5v1h5v-1z" />
        </svg>
    </a>
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
