<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<div
		class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
		<h5 class="my-0 mr-md-auto font-weight-normal">Норфия Индстрис</h5>
		<nav class="my-2 my-md-0 mr-md-3">
			<a class="p-2 text-dark" href="/home/dictionary">Справочник</a>
		</nav>
		<c:choose>
			<c:when test="${sessionScope.app_config.lastUrl ne '/init'}">
				<nav class="my-2 my-md-0 mr-md-3">
					<a class="p-2 text-dark" href="/home/clearskyengcookie">Очистить
						куки skyeng</a>
				</nav>
			</c:when>
		</c:choose>
		<nav class="my-2 my-md-0 mr-md-3">
			<a class="p-2 text-dark" href="/init">Другой документ</a>
		</nav>
		<nav class="my-2 my-md-0 mr-md-3">
			<a class="p-2 text-dark" href="#">Support</a>
		</nav>
		<a class="btn btn-outline-primary" href="#">Sign up</a>
	</div>
</header>
