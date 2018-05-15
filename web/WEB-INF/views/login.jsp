<%--
  Created by IntelliJ IDEA.
  User: estagio.yuris
  Date: 07/11/2017
  Time: 08:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Entrar</title>
        <link rel="stylesheet" href="bootstrap/bootstrap.css">
        <link rel="stylesheet" href="bootstrap/AdminLTE.css">
        <link rel="stylesheet" href="vendor/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="vendor/Ionicons/css/ionicons.min.css">
        <link rel="stylesheet" href="vendor/Ionicons/css/ionicons.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

    </head>
    <body class="hold-transition login-page">
        <div class="login-box">
            <div class="login-logo">
                <a href="#"><b>CEJUSC</b> JFPB</a>
            </div>

            <div class="login-box-body">
                    <c:if test="${mensagem != null}">
                        <div class="alert alert-danger alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <p><i class="icon fa fa-ban"></i>${mensagem}</p>
                        </div>
                    </c:if>


                <form:form action="login" method="post" modelAttribute="usuario" class="">

                    <div class="form-group has-feedback">
                        <label>Usuário</label>
                    </div>

                    <div class="form-group has-feedback">
                        <form:input path="nome" placeholder="Usuario" class="form-control"/>
                        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                    </div>

                    <div class="form-group has-feedback">
                        <label>Senha</label>
                    </div>

                    <div class="form-group has-feedback">
                        <form:password path="senha" placeholder="Senha" class="form-control"/>
                        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                    </div>


                    <div class="row">

                        <!-- /.col -->
                        <div class="col-xs-4">
                            <button type="submit" class="btn btn-primary btn-block btn-flat">Entrar</button>
                        </div>
                    </div>

                </form:form>
            </div>
        </div>
    </body>
</html>
