<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>

    <jsp:attribute name="title">Comparator</jsp:attribute>


    <jsp:body>

        <c:url value="/tasks" var="tables"/>
        <c:url value="/unstable" var="unstable"/>

        <body>
        <!--/.navbar-collapse -->

        <div class="templatemo-content-wrapper">
            <div class="templatemo-content">
                <h1>Домашняя страница управления компараторами</h1>
                <p>##########################################################
                    ########################################################
                    #####################################################
                    ######################################################</p>

                <div class="row">
                    <div class="col-md-6">
                        <div class="templatemo-alerts">
                            <div class="row">
                                <div class="col-md-12">
                                    <c:choose>
                                        <c:when test="${resultObject.dataBaseStatus == true}">

                                            <div class="alert alert-success alert-dismissible" role="alert">
                                                <button type="button" class="close" data-dismiss="alert"><span
                                                        aria-hidden="true"></span><span class="sr-only">Close</span></button>
                                                <strong>Success!</strong> База данных работает
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="alert alert-danger alert-dismissible" role="alert">
                                                <button type="button" class="close" data-dismiss="alert"><span
                                                        aria-hidden="true"></span><span class="sr-only">Close</span></button>
                                                <strong> Warning! </strong> Ошибка доступа к базе
                                            </div>
                                        </c:otherwise>
                                    </c:choose>

                                    <c:choose>
                                        <c:when test="${resultObject.taskLogStatus== -1}">
                                            <div class="alert alert-danger alert-dismissible" role="alert">
                                                <button type="button" class="close" data-dismiss="alert"><span
                                                        aria-hidden="true"></span><span class="sr-only">Close</span></button>
                                                <strong> Warning! </strong> История заданий не прогружается
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="alert alert-success alert-dismissible" role="alert">
                                                <button type="button" class="close" data-dismiss="alert"><span
                                                        aria-hidden="true"></span><span class="sr-only">Close</span></button>
                                                <strong>Success!</strong> Завершенных заданий ${resultObject.taskLogStatus}
                                            </div>
                                        </c:otherwise>
                                    </c:choose>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="templatemo-progress">
                            <div class="list-group">
                                <a href="${unstable}" class="list-group-item">
                                    <h4 class="list-group-item-heading">Накопление данных для рассчета
                                        нестабильности</h4>
                                    <p class="list-group-item-text">С целью улучшения ТТХ ВЭТ 1-13 в части суточной нестабилности необходимо
                                        было осуществлять управление выбранным ВСЧ VCH на основе средневзвешенных оценок относительных отклонений
                                        частот по отношению к каждому ВСЧ из состава ВЭТ 1-13.</p>
                                </a>
                            </div>
                            <div class="progress">
                                <div class="progress-bar progress-bar-striped active" role="progressbar"
                                     aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:40%">
                                    <span class="sr-only">"40" Complete</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="templatemo-panels">
                    <div class="row">
                        <div class="col-md-6 col-sm-6 margin-bottom-30">
                            <div class="panel panel-success">
                                <div class="panel-heading">Data Visualization</div>
                                <canvas id="templatemo-line-chart-first" height="120" width="500"></canvas>
                            </div>
                            <div class="panel panel-success">
                                <div class="panel-heading">Data Visualization</div>
                                <canvas id="templatemo-line-chart-second" height="120" width="500"></canvas>
                            </div>
                            <span class="btn btn-success"><a href="data-visualization.html">More Charts</a></span>
                        </div>
                        <div class="col-md-6 col-sm-6 margin-bottom-30">
                            <div class="panel panel-primary">
                                <div class="panel-heading">Таблица текущих заданий</div>
                                <div class="panel-body">
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Рабочее место</th>
                                            <th>Канал</th>
                                            <th>Задание</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${resultObject.taskList}" var="item">
                                        <tr>
                                            <td>${item.id}</td>
                                            <td>${item.tableName}</td>
                                            <td>${item.channel}</td>
                                            <td>${item.commandName}</td>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <span class="btn btn-primary"><a href="${tables}">Смотреть задания</a></span>
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
              location.reload();
            }
            $(document).ready(function ($) {
                setInterval('show()', 30000);
            });
        </script>

        <!-- Script to Activate the Carousel -->
        <script type="text/javascript">

            // Line chart
            var randomScalingFactor = function () {
                return Math.round(Math.random() * 100)
            };
            var lineChartData = {
                labels: ["January", "February", "March", "April", "May", "June", "July"],
                datasets: [
                    {
                        label: "My First dataset",
                        fillColor: "rgba(220,220,220,0.2)",
                        strokeColor: "rgba(220,220,220,1)",
                        pointColor: "rgba(220,220,220,1)",
                        pointStrokeColor: "#fff",
                        pointHighlightFill: "#fff",
                        pointHighlightStroke: "rgba(220,220,220,1)",
                        data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()]
                    },
                    {
                        label: "My Second dataset",
                        fillColor: "rgba(151,187,205,0.2)",
                        strokeColor: "rgba(151,187,205,1)",
                        pointColor: "rgba(151,187,205,1)",
                        pointStrokeColor: "#fff",
                        pointHighlightFill: "#fff",
                        pointHighlightStroke: "rgba(151,187,205,1)",
                        data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()]
                    }
                ]

            }

            window.onload = function () {
                var ctx_line_first = document.getElementById("templatemo-line-chart-first").getContext("2d");
                var ctx_line_second = document.getElementById("templatemo-line-chart-second").getContext("2d");
                window.myLine = new Chart(ctx_line_first).Line(lineChartData, {
                    responsive: true
                });
                window.myLine = new Chart(ctx_line_second).Line(lineChartData, {
                    responsive: true
                });

            };

            $('#myTab a').click(function (e) {
                e.preventDefault();
                $(this).tab('show');
            });

            $('#loading-example-btn').click(function () {
                var btn = $(this);
                btn.button('loading');


                // $.ajax(...).always(function () {
                //   btn.button('reset');
                // });
            });
        </script>
        </body>
    </jsp:body>

</page:template>
