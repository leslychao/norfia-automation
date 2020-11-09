<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form class="text-center border border-light p-5 col-3" action="/login/process" method="POST">
    <p class="h4 mb-4">Sign in</p>
    <input type="text" id="defaultLoginFormEmail" class="form-control mb-4"
           placeholder="Имя пользователя" name="username">
    <input type="password" id="defaultLoginFormPassword" class="form-control mb-4"
           placeholder="Пароль" name="password">
    <div class="d-flex justify-content-around">
        <div>
            <div class="custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input" id="defaultLoginFormRemember">
                <label class="custom-control-label" for="defaultLoginFormRemember">
                    Remember me
                </label>
            </div>
        </div>
    </div>
    <button class="btn btn-info btn-block my-4" type="submit">Sign in</button>
    <p>Not a member?
        <a href="/login/register">Sign up</a>
    </p>
</form:form>
