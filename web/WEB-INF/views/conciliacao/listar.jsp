<%--
  Created by IntelliJ IDEA.
  User: estagio.yuris
  Date: 09/11/2017
  Time: 09:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="${request.contextPath}/top-menu"/>
<c:import url="${request.contextPath}/left-menu"/>


        <div class="row">
        <div class="col-xs-12">
            <div class="box box-primary">


                <table id="tabela" class="table table-striped">
                    <thead>
                        <tr>
                            <th>Número do Processo</th>
                            <th>Conciliador</th>
                            <th>Co Conciliador</th>
                            <th>Observador 1</th>
                            <th>Acordo</th>
                            <th>Data</th>
                            <th>Orgao</th>
                            <th>Vara</th>
                            <th>Valor em R$</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${conciliacaos}" var="conciliacao">
                        <tr>
                            <td>${conciliacao.numeroprocesso}</td>
                            <td>${conciliacao.conciliador.nome}</td>
                            <td>${conciliacao.co_conciliador.nome}</td>
                            <td>${conciliacao.observador_1.nome}</td>
                            <td>${conciliacao.acordo}</td>
                            <td><fmt:formatDate pattern="dd/MM/yyyy" value="${conciliacao.data}"/></td>
                            <td>${conciliacao.orgao.nome}</td>
                            <td>${conciliacao.vara.nome}</td>
                            <td>${conciliacao.valor}</td>
                            <td class="col-xs-1">
                                <a href="${conciliacao.id}/atualizar" class="fa fa-2x fa-edit"></a>

                            </td>
                            <td class="col-xs-1">
                                <a href="${conciliacao.id}/deletar" onclick="return confirm('Deseja excluir o registro?')"class="fa fa-2x fa-trash"></a>

                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>


                </table>

            </div>
        </div>
    </div>
    </section>
</div>

<c:import url="${request.contextPath}/rodape"/>



<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/plug-ins/1.10.16/i18n/Portuguese-Brasil.json"></script>


<script type="text/javascript">
    $(document).ready(function() {
        $('#tabela').DataTable({
            "bJQueryUI": true,
            "oLanguage": {
                "sEmptyTable": "Nenhum registro encontrado",
                "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                "sInfoEmpty": "Mostrando 0 até 0 de 0 registros",
                "sInfoFiltered": "(Filtrados de _MAX_ registros)",
                "sInfoPostFix": "",
                "sInfoThousands": ".",
                "sLengthMenu": "_MENU_ resultados por página",
                "sLoadingRecords": "Carregando...",
                "sProcessing": "Processando...",
                "sZeroRecords": "Nenhum registro encontrado",
                "sSearch": "Pesquisar",
                "oPaginate": {
                    "sNext": "Próximo",
                    "sPrevious": "Anterior",
                    "sFirst": "Primeiro",
                    "sLast": "Último"
                },
                "oAria": {
                    "sSortAscending": ": Ordenar colunas de forma ascendente",
                    "sSortDescending": ": Ordenar colunas de forma descendente"
                }
            }
        });
    });
</script>



