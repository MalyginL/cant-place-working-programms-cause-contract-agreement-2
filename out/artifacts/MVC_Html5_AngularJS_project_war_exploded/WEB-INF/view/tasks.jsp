<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>

    <jsp:attribute name="title">Comparator</jsp:attribute>

    <jsp:body>
        <c:url value="/addNewTaskButton" var="addNewTaskButton"/>
        <body>
        <!--/.navbar-collapse -->

        <div class="templatemo-content-wrapper">
            <div class="templatemo-content">
                <h1>Управления заданиями</h1>
                <p>Здесь представленны таблицы текущих и завершенных заданий</p>
                <div class="row">
                    <div class="col-md-12">
                        <div class="btn-group pull-right" id="templatemo_sort_btn">
                            <form action="${addNewTaskButton}">
                                <button type="submit" class="btn btn-default"><b>+</b> Добавить задание</button>
                            </form>
                        </div>
                        <div class="table-responsive">
                            <h4 class="margin-bottom-15">Текущие задания</h4>
                            <table class="table table-striped table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th>id</th>
                                    <th>Название рабочего места</th>
                                    <th>Номер канала</th>
                                    <th>Задание</th>
                                    <th>Время запуска задания</th>
                                    <th>Время завершения задания</th>
                                    <th>Удалить</th>

                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${resultObject.taskList}" var="item">
                                    <tr class="success">
                                        <td>${item.id}</td>
                                        <td>${item.tableName}</td>
                                        <td>${item.channel}</td>
                                        <td>${item.commandName}</td>
                                        <td>${item.startTime}</td>
                                        <td>${item.finishTime}</td>
                                        <td>
                                            <a href="/removeTask?id=${item.id}">Удалить</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="table-responsive">
                            <h4 class="margin-bottom-15">Завершенные задания</h4>
                            <table class="table table-striped table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th>Название рабочего места</th>
                                    <th>Номер канала</th>
                                    <th>Задание</th>
                                    <th>Время запуска задания</th>
                                    <th>Время завершения задания</th>
                                    <th>Статус завершения</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${resultObject.taskLogList}" var="item">
                                <c:if test="${item.status!=2}">
                                    <tr>
                                        <td>${item.tableName}</td>
                                        <td>${item.channel}</td>
                                        <td>${item.commandName}</td>
                                        <td>${item.startTime}</td>
                                        <td>${item.finishTime}</td>
                                        <td>${item.status}</td>
                                    </tr>
                                </c:if>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                            <%--<ul class="pagination pull-right">--%>
                            <%--<li class="disabled"><a href="#">&laquo;</a></li>--%>
                            <%--<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>--%>
                            <%--<li><a href="#">2 <span class="sr-only">(current)</span></a></li>--%>
                            <%--<li><a href="#">3 <span class="sr-only">(current)</span></a></li>--%>
                            <%--<li><a href="#">4 <span class="sr-only">(current)</span></a></li>--%>
                            <%--<li><a href="#">5 <span class="sr-only">(current)</span></a></li>--%>
                            <%--<li><a href="#">&raquo;</a></li>--%>
                            <%--</ul>--%>
                    </div>
                </div>
            </div>
        </div>
        </div>

        <script type="text/javascript">
            function show() {
                /* $.ajax({
                 type: "GET",
                 url: "/home",
                 success: function (html) {
                 $("#pisos").html(html);

                 }
                 });*/
                window.location.href="/tasks";

            }
            $(document).ready(function ($) {
                setInterval('show()', 30000);
            });
        </script>

        </body>
    </jsp:body>

</page:template>
