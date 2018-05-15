<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: estagio.yuris
  Date: 09/11/2017
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">

                <br>

            </div>
            <div class="pull-left info">
                <p>${usuario.nome}</p>
            </div>
        </div>
        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">MENU</li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-university"></i> <span>Conciliacao</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${pageContext.request.contextPath}/conciliacao/novo"><i class="fa fa-circle-o"></i> Cadastrar</a></li>
                    <li><a href="${pageContext.request.contextPath}/conciliacao/listar"><i class="fa fa-circle-o"></i> Listar</a></li>
                </ul>
            </li>
            <li class="treeview">

                <a href="#">
                    <i class="fa fa-users"></i> <span>Conciliador</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${pageContext.request.contextPath}/conciliador/novo"><i class="fa fa-circle-o"></i> Cadastrar</a></li>
                    <li><a href="${pageContext.request.contextPath}/conciliador/listar"><i class="fa fa-circle-o"></i> Listar</a></li>
                </ul>
            </li>
            <li class=" treeview">
                <a href="#">
                    <i class="fa fa-th"></i> <span>Orgao</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${pageContext.request.contextPath}/orgao/novo"><i class="fa fa-circle-o"></i> Cadastrar</a></li>
                    <li><a href="${pageContext.request.contextPath}/orgao/listar"><i class="fa fa-circle-o"></i> Listar</a></li>
                </ul>
            </li>

            <li class="treeview">

                <a href="#">
                    <i class="fa fa-th"></i> <span>Preposto/Procurador</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${pageContext.request.contextPath}/preposto/novo"><i class="fa fa-circle-o"></i> Cadastrar</a></li>
                    <li><a href="${pageContext.request.contextPath}/preposto/listar"><i class="fa fa-circle-o"></i> Listar</a></li>
                </ul>
            </li>

            <li class="treeview">

                <a href="#">
                    <i class="fa fa-th"></i> <span>Varas</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${pageContext.request.contextPath}/vara/novo"><i class="fa fa-circle-o"></i> Cadastrar</a></li>
                    <li><a href="${pageContext.request.contextPath}/vara/listar"><i class="fa fa-circle-o"></i> Listar</a></li>
                </ul>
            </li>

            <li class="treeview">

                <a href="#">
                    <i class="fa fa-dashboard"></i> <span>Estatística</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${pageContext.request.contextPath}/ver_estatistica/est"><i class="fa fa-circle-o"></i> Filtro</a></li>
                    <!--<li><a href="${pageContext.request.contextPath}/ver_estatistica/mensal"><i class="fa fa-circle-o"></i> Mensal</a></li>
                    <li><a href="${pageContext.request.contextPath}/ver_estatistica/anual"><i class="fa fa-circle-o"></i> Anual</a></li>
                    <li><a href="${pageContext.request.contextPath}/ver_estatistica/total"><i class="fa fa-circle-o"></i> Total</a></li>-->

                </ul>
            </li>

        </ul>
    </section>
    <!-- /.sidebar -->
</aside>

<!-- jQuery 3 -->
<script src="<c:url value ='/bower_components/jquery/dist/jquery.min.js'/>" type="text/javascript"></script>
<!-- jQuery UI 1.11.4 -->
<script src="<c:url value ='/bower_components/jquery-ui/jquery-ui.min.js'/>" type="text/javascript"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.7 -->
<script src="<c:url value ='/bower_components/bootstrap/dist/js/bootstrap.min.js'/>" type="text/javascript"></script>
<!-- Morris.js charts -->
<script src="<c:url value ='/bower_components/raphael/raphael.min.js'/>" type="text/javascript"></script>
<script src="<c:url value ='/bower_components/morris.js/morris.min.js'/>" type="text/javascript"></script>
<!-- Sparkline -->
<script src="<c:url value ='/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js'/>" type="text/javascript"></script>
<!-- jvectormap -->
<script src="<c:url value ='/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js'/>" type="text/javascript"></script>
<script src="<c:url value ='/plugins/jvectormap/jquery-jvectormap-world-mill-en.js'/>" type="text/javascript"></script>
<!-- jQuery Knob Chart -->
<script src="<c:url value ='/bower_components/jquery-knob/dist/jquery.knob.min.js'/>" type="text/javascript"></script>
<!-- daterangepicker -->
<script src="<c:url value ='/bower_components/moment/min/moment.min.js'/>" type="text/javascript"></script>
<script src="<c:url value ='/bower_components/bootstrap-daterangepicker/daterangepicker.js'/>" type="text/javascript"></script>
<!-- datepicker -->
<script src="<c:url value ='/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js'/>" type="text/javascript"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="<c:url value ='/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js'/>" type="text/javascript"></script>
<!-- Slimscroll -->
<script src="<c:url value ='/bower_components/jquery-slimscroll/jquery.slimscroll.min.js'/>" type="text/javascript"></script>
<!-- FastClick -->
<script src="<c:url value ='/bower_components/fastclick/lib/fastclick.js'/>" type="text/javascript"></script>
<!-- AdminLTE App -->
<script src="<c:url value ='/dist/js/adminlte.min.js'/>" type="text/javascript"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="<c:url value ='/dist/js/pages/dashboard.js'/>" type="text/javascript"></script>
<!-- AdminLTE for demo purposes -->
<script src="<c:url value ='/dist/js/demo.js'/>" type="text/javascript"></script>


