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
						<label for="sheetName" required>Имя справочника</label>
						<form:input class="form-control" id="dictionary" path="dictionary"
							placeholder="Имя справочника" required="true" />
						<div class="invalid-feedback">Обязательно к заполнению</div>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6 mb-6">
						<label for="skipRowNum">Название свойства</label>
						<form:input class="form-control" id="key" path="key"
							placeholder="Название свойства" required="true" />
						<div class="invalid-feedback">Обязательно к заполнению</div>
					</div>
					<div class="col-md-6 mb-6">
						<label for="headerRowNum">Значение свойства</label>
						<form:input class="form-control" id="value" path="value"
							placeholder="Значение свойства" required="true" />
						<div class="invalid-feedback">Обязательно к заполнению</div>
					</div>
				</div>
				<hr class="mb-4">
				<button class="btn btn-primary btn-lg btn-block" type="submit">Продолжить</button>
			</form:form>
		</div>
	</div>
</div>
