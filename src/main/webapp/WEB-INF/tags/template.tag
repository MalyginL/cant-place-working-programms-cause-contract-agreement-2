<!DOCTYPE html>
<%@tag description="Template Site tag" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<%@attribute name="title" fragment="true" %>
<html>
<head>
    <link href="<s:url value="/resources/images/favicon.ico"/>" rel="icon" type="image/x-icon" />
    <title><jsp:invoke fragment="title"/></title>

    <!-- Bootstrap Core CSS -->
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrap"/>
    <link href="${bootstrap}" rel="stylesheet" />

    <!-- Custom CSS -->
    <spring:url value="/resources/css/font-awesome.min.css" var="fontawesome"/>
    <link href="${fontawesome}" rel="stylesheet" />

    <spring:url value="/resources/css/templatemo_main.css" var="templatemo_main"/>
    <link href="${templatemo_main}" rel="stylesheet" />

    <spring:url value="/resources/js/jquery.min.js" var="jquerymin"/>
    <script src="${jquerymin}"></script>


    <!-- Bootstrap Core JavaScript -->
    <spring:url value="/resources/js/bootstrap.min.js" var="js"/>
    <script src="${js}"></script>

    <spring:url value="/resources/js/Chart.min.js" var="Chartmin"/>
    <script src="${Chartmin}"></script>



    <spring:url value="/resources/js/templatemo_script.js" var="templatemo_script"/>
    <script src="${templatemo_script}"></script>

    <c:url value="/tasks" var="tasks"/>
    <c:url value="/unstable" var="unstable"/>
    <c:url value="/home" var="home"/>



</head>
<body>
<div class="navbar navbar-inverse" role="navigation">
    <div class="navbar-header">
        <%--<div class="logo"><h1>Dashboard - Admin Template</h1></div>--%>
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
    </div>
</div>
<div class="template-page-wrapper">
    <div class="navbar-collapse collapse templatemo-sidebar">
        <ul class="templatemo-sidebar-menu">

            <li class="active"><a href="${home}"><i class="fa fa-home"></i>Домашняя страница</a></li>
            <li class="sub open">
                <a href="javascript:;">
                    <i class="fa fa-cog"></i> Управление <div class="pull-right"><span class="caret"></span></div>
                </a>
                <ul class="templatemo-submenu">
                    <li><a href="${tasks}">Задания</a></li>
                    <li><a href="${unstable}">Нестабильность</a></li>
                </ul>
            </li>
            <li><a href="#"><i class="fa fa-cubes"></i>Визуализация</a></li>
            <%--<li><a href="maps.html"><i class="fa fa-map-marker"></i><span class="badge pull-right">42</span>Maps</a></li>--%>
            <li><a href="#"><i class="fa fa-users"></i>Справка</a></li>
            <li><a href="http://192.168.101.93/phpmyadmin/"><i class="fa fa-database"></i>Доступ к базе</a></li>
            <li><a href=""><i class="fa fa-sign-out"></i>Основной сайт РИРВ</a></li>
        </ul>
    </div>
<jsp:doBody/>
    <footer class="templatemo-footer">
        <div class="templatemo-copyright">
            <p>РИРВ 2017</p>
        </div>
    </footer>
</body>

</html>