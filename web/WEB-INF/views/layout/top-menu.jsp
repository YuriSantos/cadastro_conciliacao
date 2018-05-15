<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: estagio.yuris
  Date: 09/11/2017
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="PT-BR" xmlns:th="http://www.thymeleaf.org">
<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>CEJUSC</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" type="text/javascript"></script>


    <link rel="shortcut icon" href="http://localhost:8080/imagens/favicon.ico" type="image/x-icon">

    <!-- Bootstrap 3.3.7 -->
    <link href="<c:url value ="/bower_components/bootstrap/dist/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" />
    <!-- Font Awesome -->
    <link href="<c:url value ="/bower_components/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css" />
    <!-- Ionicons -->
    <link href="<c:url value ="/bower_components/Ionicons/css/ionicons.min.css"/>" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="<c:url value ="/dist/css/AdminLTE.min.css"/>" rel="stylesheet" type="text/css" />
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link href="<c:url value ="/dist/css/skins/cejusc.css"/>" rel="stylesheet" type="text/css" />
    <!-- Morris chart -->
    <link href="<c:url value ="/bower_components/morris.js/morris.css"/>" rel="stylesheet" type="text/css" />
    <!-- jvectormap -->
    <link href="<c:url value ="/bower_components/jvectormap/jquery-jvectormap.css"/>" rel="stylesheet" type="text/css" />
    <!-- Date Picker -->
    <link href="<c:url value ="/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css"/>" rel="stylesheet" type="text/css" />
    <!-- Daterange picker -->
    <link href="<c:url value ="/bower_components/bootstrap-daterangepicker/daterangepicker.css"/>" rel="stylesheet" type="text/css" />
    <!-- bootstrap wysihtml5 - text editor -->
    <link href="<c:url value ="/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css"/>" rel="stylesheet" type="text/css" />

    <link rel="stylesheet" type="text/css" href="<c:url value="/bower_components/DataTables/datatables.css"/>"/>


    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Google Font -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<div class="wrapper">

    <header class="main-header">
        <!-- Logo -->
        <a href="${pageContext.request.contextPath}/inicio" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>CEJUSC</b></span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>CEJUSC</b>JFPB</span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>

            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <!-- Messages: style can be found in dropdown.less-->

                    <!-- Notifications: style can be found in dropdown.less -->

                    <!-- Tasks: style can be found in dropdown.less -->

                    <!-- User Account: style can be found in dropdown.less -->
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                            <span class="hidden-xs">${usuario.nome}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">


                                <p>
                                    <!--Colocar o full name user aqui-->
                                </p>
                            </li>
                            <!-- Menu Body -->
                            <li class="user-body">

                                <!-- /.row -->
                            </li>
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-left">
                                    <a href="#" class="btn btn-default btn-flat">Profile</a>
                                </div>
                                <div class="pull-right">
                                    <a href="${pageContext.request.contextPath}/saindo" class="btn btn-default btn-flat">Sign out</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <!-- Control Sidebar Toggle Button -->
                </ul>
            </div>
        </nav>
    </header>

    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content">

                <c:if test="${mensagem != null}">
                <div class="alert alert-success alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <h4><i class="icon fa fa-check"></i>${mensagem}</h4>
                </div>
                </c:if>

                    <c:if test="${erro != null}">
                    <div class="alert alert-danger alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <h4><i class="icon fa fa-ban"></i>${erro}</h4>
                    </div>
                    </c:if>


