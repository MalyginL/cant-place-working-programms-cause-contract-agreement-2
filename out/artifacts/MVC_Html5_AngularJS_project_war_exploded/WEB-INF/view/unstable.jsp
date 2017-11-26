<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: adm
  Date: 16.08.2017
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Date"%>
<page:template>
    <jsp:attribute name="title">Расчет поправки на компенсацию</jsp:attribute>
    <jsp:body>
        <body>
        <div class="templatemo-content-wrapper">
            <div class="templatemo-content">
                <h1>Расчет поправки на компенсацию отклонения </h1>
                <p></p>
                <div class="row">
                    <div class="col-md-12 col-sm-12">
                        <div class="panel panel-primary">
                            <div class="panel-body">
                                <div class="col-md-6 col-sm-12">
                                    <div class="table-responsive">
                                        <h4 class="margin-bottom-15">Рабочие каналы эталона</h4>
                                        <table class="table table-striped table-hover table-bordered">
                                            <tbody>
                                            <tr class="success">
                                                <c:forEach items="${resultObject.workingChannels}" var="item">
                                                    <td>${item}</td>
                                                </c:forEach>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>


                                    <form role="form" class="form-group col-md-12" id="removeOldChannel" method='get'
                                          action="/removeChannelForEtalon">

                                        <div class="col-md-6 col-sm-12">

                                            <select class="form-control margin-bottom-15" id="removeChannel"
                                                    name="removeChannel">
                                                <c:forEach items="${resultObject.workingChannels}" var="item">
                                                    <option value="${item}">${item}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-md-6 col-sm-12">
                                            <button type="submit" class="btn btn-danger btn-block">Удалить канал
                                            </button>
                                        </div>
                                    </form>

                                    <form role="form" class="form-group col-md-12" id="addNewChannel" method='get'
                                          action="/addChannelForEtalon">

                                        <div class="col-md-6 col-sm-12">

                                            <select class="form-control margin-bottom-15" id="addChannel"
                                                    name="addChannel">
                                                <c:forEach var="i" begin="1" end="8">
                                                    <c:if test="${!resultObject.workingChannels.contains(i)}">
                                                        <option value="${i}">${i}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-md-6 col-sm-12">
                                            <button type="submit" class="btn btn-success btn-block">Добавить канал
                                            </button>
                                        </div>
                                    </form>

                                </div>
                                <div class="col-md-6 col-sm-12">


                                    <br>
                                    <form role="form" id="startUnstable" method='get' action="/unstableStart">
                                        <div class="col-md-6 col-sm-12">
                                            <label for="measurement" class="control-label">Время измерения (мин)</label>
                                            <input type="text" class="form-control margin-bottom-15" id="measurement"
                                                   value="1"
                                                   name="measurement">
                                            <label class="control-label">Сигма</label>
                                            <br>
                                            <input type="radio" name="sigma" value="1" checked>СКДО<br>
                                            <input type="radio" name="sigma" value="2">СКО

                                        </div>
                                        <div class="col-md-6 col-sm-12">
                                            <label for="observation" class="control-label">Время наблюдения(мин)</label>
                                            <input type="text" class="form-control" id="observation" value="24"
                                                   name="observation">
                                            <br>
                                            <button type="submit" class="btn btn-primary form-control">Начать
                                                испытания
                                            </button>
                                        </div>
                                        <div class="col-md-12 col-sm-12">
                                                <%--<button type="submit" class="btn btn-primary form-control">Начать--%>
                                                <%--испытания--%>
                                                <%--</button>--%>
                                        </div>


                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">

                    <div class="col-md-6 col-sm-12">
                        <div class="panel panel-primary">
                            <div class="panel-heading">Таблица поправок</div>
                            <div class="panel-body">
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Время</th>
                                        <th>Учавствующие каналы</th>
                                        <th>Поправка на компенсацию</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>#</td>
                                        <td>${resultObject.currTime}</td>
                                        <td>${resultObject.workingChannelsToString}</td>
                                        <td>${resultObject.itogovayaPopravka}</td>
                                            <%--<td>${item.id}</td>--%>
                                            <%--<td>${item.tableName}</td>--%>
                                            <%--<td>${item.channel}</td>--%>
                                            <%--<td>${item.commandName}</td>--%>
                                            <%--</c:forEach>--%>
                                    </tbody>

                                </table>
                            </div>
                        </div>

                    </div>

                    <div class="col-md-12 col-sm-12">
                        <c:forEach items="${resultObject.workingChannels}" var="channel">
                            <div class="panel panel-primary">
                                <div class="panel-heading">Таблицы результатов канала ${channel}</div>
                                <div class="panel-body">
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th>Время</th>
                                            <th>Сигма</th>
                                            <th>A брака</th>
                                            <th>В брака</th>
                                            <th>Поправка брака</th>
                                            <th>A норм</th>
                                            <th>В норм</th>
                                            <th>поправка</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>${resultObject.unstableCurrentTime.get(channel)}</td>
                                            <td>${resultObject.unstableSKOTable.get(channel)}</td>
                                            <td>${resultObject.unstableDefectA.get(channel)}</td>
                                            <td>${resultObject.unstableDefectB.get(channel)}</td>
                                            <td>${resultObject.unstableDefectCorection.get(channel)}</td>
                                            <td>${resultObject.unstableSucessA.get(channel)}</td>
                                            <td>${resultObject.unstableSucessB.get(channel)}</td>
                                            <td>${resultObject.unstableCorrecion.get(channel)}</td>
                                        </tr>
                                        </tbody>

                                    </table>
                                </div>
                            </div>
                        </c:forEach>

                    </div>


                </div>
            </div>
        </div>


        </body>
    </jsp:body>
</page:template>