<%--
  Created by IntelliJ IDEA.
  User: yuris
  Date: 09/11/2017
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="${request.contextPath}/top-menu"/>
<c:import url="${request.contextPath}/left-menu"/>




        <div class="row">
            <div class="col-xs-12">
                <!-- general form elements -->
                <div class="box box-primary">
                    <div class="box-header with-border">
                    <h3 class="box-title">Cadastro de conciliador</h3>
                </div>

                    <div class="box-body">
                        <div class="form-group">
                            <input type="hidden" name="salvo_por" value="${usuario.nome}"/>
                            <form:form action="${pageContext.request.contextPath}/conciliador/salvar" method="POST" modelAttribute="conciliador" >
                                <input type="hidden" name="id" value="${conciliador.id}"/>
                            <div class="form-group">
                                <label>Nome</label>
                                <form:input path="nome" class="form-control"/>
                            </div>


                        </div>
                        <div class="box-footer">
                            <button type="submit" class="btn btn-primary">Salvar</button>
                        </div>
                        </form:form>


                    </div>
                </div>
                <!-- /.box -->

            </div>
        </div>

    </section>
</div>


<c:import url="${request.contextPath}/rodape"/>

<script>
    $('.input-group.date').datepicker({
        format: 'dd/mm/yyyy',
        language: 'pt-BR',
        todayHighlight: true
    });
</script>
