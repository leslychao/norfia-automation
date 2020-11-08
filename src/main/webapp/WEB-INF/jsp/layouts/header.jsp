<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<nav class="navbar navbar-expand-md navbar-dark bg-dark">
		<div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="/home">Norfia
						Industries</a></li>
				<li class="nav-item">
					<a class="nav-link" href="/init">
						Другой документ
					</a>
				</li>
			</ul>
		</div>
		<c:choose>
			<c:when test="${sessionScope.app_config.lastUrl eq '/home'}">
				<div class="mx-auto order-0">
					<a class="navbar-brand mx-auto" href="/home/syncCompanies">Запустить интеграцию</a>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target=".dual-collapse2">
						<span class="navbar-toggler-icon"></span>
					</button>
				</div>
			</c:when>
		</c:choose>
		<div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="https://vk.com/vitalii_kim">Support</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Sign up</a></li>
			</ul>
		</div>
	</nav>
</header>
