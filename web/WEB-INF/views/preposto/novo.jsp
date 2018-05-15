<%--
  Created by IntelliJ IDEA.
  User: estagio.yuris
  Date: 01/02/2018
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="${request.contextPath}/left-menu"/>
<c:import url="${request.contextPath}/top-menu"/>



<div class="row">
    <div class="col-xs-12">
        <!-- general form elements -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">Cadastro de Preposto</h3>
            </div>

            <div class="box-body">
                <div class="form-group">
                    <input type="hidden" name="salvo_por" value="${usuario.nome}"/>
                    <form:form action="${pageContext.request.contextPath}/preposto/salvar" method="POST" modelAttribute="preposto">
                    <input type="hidden" name="id" value="${preposto.id}"/>
                    <div class="form-group">
                        <label>Nome</label>
                        <form:input path="nome" class="form-control"/>
                    </div>

                        <div class="form-group">
                            <label>Orgão Pertencente</label>
                            <form:select path="orgao.id" size="1" items="${orgao}" class="form-control">
                                <option>Conciliador</option>
                            </form:select>
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
