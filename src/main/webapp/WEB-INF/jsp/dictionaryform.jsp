<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="container">
	<div class="row">
		<div class="mt-5 mx-auto col-md-8 order-md-1">
			<form:form class="needs-validation" method="POST"
				action="/home/addDictionary" modelAttribute="dictionary">
				<form:input type="hidden" path="id" />
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="module" required>Модуль</label>
						<form:input class="form-control" id="module" path="module"
							placeholder="Модуль" required="true" />
						<div class="invalid-feedback">Обязательно к заполнению</div>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="dictionaryType" required>Тип справочника</label>
						<form:input class="form-control" id="dictionaryType"
							path="dictionaryType" placeholder="Тип справочника"
							required="true" />
						<div class="invalid-feedback">Обязательно к заполнению</div>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6 mb-6">
						<label for="dictionaryKey">Название свойства</label>
						<form:input class="form-control" id="dictionaryKey"
							path="dictionaryKey" placeholder="Название свойства"
							required="true" />
						<div class="invalid-feedback">Обязательно к заполнению</div>
					</div>
					<div class="col-md-6 mb-6">
						<label for="dictionaryValue">Значение свойства</label>
						<form:input class="form-control" id="dictionaryValue"
							path="dictionaryValue" placeholder="Значение свойства"
							required="true" />
						<div class="invalid-feedback">Обязательно к заполнению</div>
					</div>
				</div>
				<hr class="mb-4">
				<button class="btn btn-primary btn-lg btn-block" type="submit">Продолжить</button>
			</form:form>
		</div>
	</div>
</div>
