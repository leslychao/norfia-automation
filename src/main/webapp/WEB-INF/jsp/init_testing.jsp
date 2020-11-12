<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="container">
    <div class="row">
        <div class="mt-5 mx-auto col-md-8 order-md-1">
            <h4 class="mb-3">Ленка Пенка введи параметры конфигурации, если
                че спроси у Норфии</h4>
            <form class="needs-validation" method="POST" action="/testing"
                  enctype="multipart/form-data">
                <div class="form-row">
                    <div class="col-md-6 mb-3">
                        <div class="custom-file">
                            <input type="file" class="custom-file-input" id="xlsFile"
                                   name="xlsFile">
                            <label class="custom-file-label" for="xlsFile">Choose XLS file</label>
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-6 mb-3">
                        <div class="custom-file">
                            <input type="file" class="custom-file-input" id="jsonFile"
                                   name="jsonFile">
                            <label class="custom-file-label" for="xlsFile">Choose JSON file</label>
                        </div>
                    </div>
                </div>
                <script>
                  // Add the following code if you want the name of the file appear on select
                  $(".custom-file-input").on(
                      "change",
                      function () {
                        var fileName = $(this).val().split("\\").pop();
                        $(this).siblings(".custom-file-label")
                        .addClass("selected").html(fileName);
                      });
                </script>
                <div class="form-row">
                    <div class="col-md-6 mb-3">
                        <input
                                type="text" class="form-control" id="sheetName" name="sheetName"
                                placeholder="Имя листа в excel окументе" required>
                        <div class="invalid-feedback">Обязательно к заполнению</div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-6 mb-6">
                        <input
                                type="text" class="form-control" id="skipRowNum"
                                name="skipRowNum"
                                placeholder="Количество строк которое нужно пропустить в excel документе"
                                required>
                        <div class="invalid-feedback">Обязательно к заполнению</div>
                    </div>
                    <div class="col-md-6 mb-6">
                        <input
                                type="text" class="form-control" id="headerRowNum"
                                name="headerRowNum" placeholder="Номер строки заголовка" required>
                        <div class="invalid-feedback">Обязательно к заполнению</div>
                    </div>
                </div>
                <hr class="mb-4">
                <button class="btn btn-primary btn-lg btn-block" type="submit">Продолжить</button>
            </form>
        </div>
    </div>
</div>
