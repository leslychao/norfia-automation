<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
