<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: estagio.yuris
  Date: 09/11/2017
  Time: 09:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="${request.contextPath}/top-menu"/>
<c:import url="${request.contextPath}/left-menu"/>


<script src="${pageContext.request.contextPath}/dist/jquery.maskMoney.min.js" type="text/javascript"></script>



    <div class="row">

        <div class="col-xs-12">
            <!-- general form elements -->
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">Cadastro de conciliacao</h3>
                </div>

                <div class="box-body">
                    <div class="form-group">
                        <form:form action="${pageContext.request.contextPath}/conciliacao/salvar" method="POST" modelAttribute="conciliacao">
                            <input type="hidden" name="id" value="${conciliacao.id}"/>
                            <input type="hidden" name="salvo_por" value="${usuario.nome}"/>
                            <div class="form-group">
                                <label>Número do Processo</label>
                                <form:input path="numeroprocesso" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>Conciliador</label>
                                <form:select path="conciliador.id" size="1" items="${conciliador}" class="form-control">
                                    <option>Conciliador</option>
                                </form:select>

                            </div>

                            <div class="form-group">
                                <label>Co Conciliador</label>
                                <form:select path="co_conciliador.id" size="1" items="${conciliador}" class="form-control">
                                    <option>Co Conciliador</option>
                                </form:select>

                            </div>

                            <div class="form-group">
                                <label>Observador</label>
                                <form:select path="observador_1.id" size="1" items="${conciliador}" class="form-control">
                                    <option>Observador</option>
                                </form:select>

                            </div>
                            <div class="form-group">
                                <label>data</label>
                                <div class="input-group date" data-provide="datepicker">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <form:input path="data" required="required" id="calendario" />
                                </div>
                            </div>

                                <div class="form-group">
                                    <label>Orgao</label>
                                    <form:select path="orgao.id" size="1" items="${orgao}" class="form-control">
                                        <option>Orgao</option>
                                    </form:select>

                                </div>

                                <div class="form-group">
                                    <label>Preposto/Procurador</label>
                                    <form:select path="preposto.id" size="1" items="${preposto}" class="form-control">
                                        <option>Preposto/Procurador</option>
                                    </form:select>

                                </div>

                                <div class="form-group">
                                    <label>Vara</label>
                                    <form:select path="vara.id" size="1" items="${vara}" class="form-control">
                                        <option>Vara</option>
                                    </form:select>

                                </div>


                            <div class="form-group">
                                <label>Valor Envolvido em R$</label>

                                    <form:input path="valor_string" class="form-control" id="valor"/>
                            </div>

                                <div class="form-group">
                                    <label>Resultado da Audiência</label>
                                    <br>
                                    <form:radiobutton path="resultado" value="1"/>
                                    <label>Sem Acordo</label>
                                    <form:radiobutton path="resultado" value="2"/>
                                    <label>Com Acordo</label>
                                    <form:radiobutton path="resultado" value="3"/>
                                    <label>Ausência</label>
                                    <form:radiobutton path="resultado" value="4"/>
                                    <label>Redesignação</label>
                                    <form:radiobutton path="resultado" value="5"/>
                                    <label>Retirada de pauta/Cancelada</label>
                                </div>

                            </div>
                            <div class="box-footer">
                                <button type="submit" onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true;" class="btn btn-primary">Salvar</button>
                                <a href="${pageContext.request.contextPath}/conciliacao/listar" class="btn btn-primary">Cancelar</a>
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
    $('.datepicker').datepicker({
        format: 'dd/mm/yyyy',
        todayHighlight: true
    });

    $(function(){
        $("#valor").maskMoney({prefix:'R$ ', allowNegative: true, thousands:'.', decimal:',', affixesStay: true});
    })

</script>