<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<div class="row">
		<div class="mt-5 col-md-12">
			<div class="table-responsive">
				<a id="add_row" class="float-right" href="/home/dictionaryform">
					<svg width="2em" height="2em" viewBox="0 0 16 16"
						class="bi bi-capslock" fill="currentColor"
						xmlns="http://www.w3.org/2000/svg">
  						<path fill-rule="evenodd" d="M7.27 1.047a1 1 0 0 1 1.46 0l6.345 6.77c.6.638.146 1.683-.73 1.683H11.5v1a1 1 0 0 1-1 1h-5a1 1 0 0 1-1-1v-1H1.654C.78 9.5.326 8.455.924 7.816L7.27 1.047zM14.346 8.5L8 1.731 1.654 8.5H4.5a1 1 0 0 1 1 1v1h5v-1a1 1 0 0 1 1-1h2.846zm-9.846 5a1 1 0 0 1 1-1h5a1 1 0 0 1 1 1v1a1 1 0 0 1-1 1h-5a1 1 0 0 1-1-1v-1zm6 0h-5v1h5v-1z" />
					</svg>
				</a>
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