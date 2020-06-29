<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<script language="javascript">
	document.body.onload = function() {
		if ("${sessionScope.app_config.skyengCookie}".length == 0) {
			$('#skyengCookieModal').modal('show');
		}
	}
</script>
</head>
<div class="modal fade" id="skyengCookieModal" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<form method="POST" action="/home/saveskyengcookie">
					<div class="form-group">
						<label for="skyengcookie" class="col-form-label">skyeng
							cookie:</label>
						<textarea class="form-control" id="skyengcookie"
							name="skyengcookie"></textarea>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Закрыть</button>
						<button class="btn btn-primary" type="submit">Сохранить</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="orgNameModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<form method="GET" action="/home/processOrgName">
					<section class="section-preview">
						<div class="my-2"></div>
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input"
								id="excludeIndividual" name="excludeIndividual"> <label
								class="custom-control-label" for="excludeIndividual">Оставить
								только организации</label>
						</div>
						<div class="my-2"></div>
					</section>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Send</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="container">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th scope="col">Кредит</th>
				<th scope="col">
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#orgNameModal" data-whatever="@mdo">Наименование</button>
				</th>
				<th scope="col">ИНН</th>
				<th scope="col">Назначение платежа</th>
				<th scope="col">Последнее обновление</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${statements}" var="statement">
				<tr>
					<td>${statement.credit}</td>
					<td>${statement.name}</td>
					<td>${statement.inn}</td>
					<td>${statement.paymentDetails}</td>
					<td>${localDateTimeFormatter.format(statement.lastUpdated)}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
