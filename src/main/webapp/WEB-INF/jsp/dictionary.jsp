<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<h4>Таблица справочников</h4>
			<div class="table-responsive">
				<a id="add_row" class="btn btn-primary float-right"
					href="/home/dictionaryform">Add Row</a>
				<table id="mytable" class="table table-bordred table-striped">
					<thead>
						<tr>
							<th>Имя справочника</th>
							<th>Название свойства</th>
							<th>Значение свойства</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${dictionaries}" var="dictionary">
							<tr>
								<td>${dictionary.dictionary}</td>
								<td>${dictionary.key}</td>
								<td>${dictionary.value}</td>
								<td>
									<p data-placement="top" data-toggle="tooltip" title="Edit">
										<a class="btn btn-primary btn-xs"
											href="/home/editDictionary?id=${dictionary.id}"> <span
											class="glyphicon glyphicon-pencil"></span>
										</a>
									</p>
								</td>
								<td>
									<p data-placement="top" data-toggle="tooltip" title="Delete">
										<a class="btn btn-danger btn-xs"
											href="/home/removeDictionary?id=${dictionary.id}"> <span
											class="glyphicon glyphicon-trash"></span>
										</a>
									</p>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>