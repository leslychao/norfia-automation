<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="container">
	<div class="row">
		<div class="mt-5 mx-auto col-md-8 order-md-1">
			<form:form class="needs-validation" method="POST"
				action="/home/saveStatement" modelAttribute="statement">
				<form:input type="hidden" path="id" />
				<form:input type="hidden" path="credit" />
				<form:input type="hidden" path="inn" />
				<form:input type="hidden" path="packId" />
				<form:input type="hidden" path="syncState" />
				<form:input type="hidden" path="log" />
				<div class="form-group">
					<label for="dictionaryValue" class="col-form-label">Имя
						компании</label>
					<form:textarea class="form-control" id="name" path="name"></form:textarea>
				</div>
				<div class="form-group">
					<label for="dictionaryValue" class="col-form-label">Детали
						платежа</label>
					<form:textarea class="form-control" id="paymentDetails"
						path="paymentDetails"></form:textarea>
				</div>
				<hr class="mb-4">
				<button class="btn btn-primary btn-lg btn-block" type="submit">Продолжить</button>
			</form:form>
		</div>
	</div>
</div>
