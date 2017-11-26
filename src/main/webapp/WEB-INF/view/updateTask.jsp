<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<page:template>
    <jsp:attribute name="title">Comparator</jsp:attribute>

    <jsp:body>
        <body>
        <div class="templatemo-content-wrapper">
            <div class="templatemo-content">
                <h1>Страница настройки задания</h1>
                <p class="margin-bottom-15">Внимание! Для работающих каналов задание <b>обновляется!</b> Будьте
                    внимательны! </p>
                <div class="row">
                    <div class="col-md-12">
                        <form role="form" id="templatemo-preferences-form" method='get' action="/addNewTask">
                            <div class="row">
                                <div class="col-md-6 margin-bottom-15">
                                    <label for="tableName">Рабочее место</label>
                                    <select class="form-control margin-bottom-15" id="tableName" name="tableName">
                                        <option value="Etalon">Эталон</option>
                                        <option value="2">5-72</option>
                                        <option value="test3">ТЕСТ</option>
                                        <option value="test4">ТЕСТ</option>
                                        <option value="test5">ТЕСТ</option>
                                    </select>
                                </div>
                                <div class="col-md-6 margin-bottom-15">
                                    <label for="channel">Канал</label>
                                    <select class="form-control margin-bottom-15" id="channel" name="channel">
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                        <option value="6">6</option>
                                        <option value="7">7</option>
                                        <option value="8">8</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 margin-bottom-15">
                                    <label for="cmd">Задание</label>
                                    <select class="form-control margin-bottom-15" id="cmd" name="cmd">
                                        <option value="разность фаз">разность фаз</option>
                                        <option value="относительная разность частот">относительная разность частот</option>
                                        <option value="текущие вариации относительной разности частот">текущие вариации относительной разности частот</option>
                                        <option value="СКДО">СКДО</option>

                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 margin-bottom-15">
                                    <label for="startTime" class="control-label">Время начала испытаний</label>
                                    <input type="text" class="form-control" id="startTime" value="0" name="startTime">
                                </div>
                                <div class="col-md-6 margin-bottom-15">
                                    <label for="endTime" class="control-label">Точка остановки</label>
                                    <input type="text" class="form-control" id="endTime" value="0" name="endTime">
                                </div>
                            </div>
                            <div class="row templatemo-form-buttons">
                                <div class="col-md-12">
                                    <button type="submit" class="btn btn-primary">Добавить</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        </body>


    </jsp:body>
</page:template>