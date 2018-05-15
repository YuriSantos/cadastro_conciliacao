<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: estagio.yuris
  Date: 23/01/2018
  Time: 09:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="${request.contextPath}/top-menu"/>
<c:import url="${request.contextPath}/left-menu"/>



<div class="row">
    <div class="col-xs-12 ">
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">Filtro De Estatística</h3>
            </div>
            <div class="form-group">
                <label>Selecione o Ano</label>
                <select class="form-control form-control-lg" id="ano">
                    <option value="0">Nenhum</option>
                    <option value="2016">2016</option>
                    <option value="2017">2017</option>
                    <option value="2018">2018</option>

                </select>
            </div>


            <div class="form-group">
                <label>Selecione o Mês</label>
                <select class="form-control" id="mes">
                    <option value="0">Nenhum</option>
                    <option value="1">Jan</option>
                    <option value="2">Fev</option>
                    <option value="3">Mar</option>
                    <option value="4">Abr</option>
                    <option value="5">Mai</option>
                    <option value="6">Jun</option>
                    <option value="7">Jul</option>
                    <option value="8">Ago</option>
                    <option value="9">Set</option>
                    <option value="10">Out</option>
                    <option value="11">Nov</option>
                    <option value="12">Dez</option>
                </select>
            </div>

            <div class="form-group">
                <label>Selecione o Conciliador</label>
                <select class="form-control form-control-lg" id="conc">
                    <option value="0">Nenhum</option>
                    <c:forEach items="${conciliadores}" var="conciliadores">
                        <option value="${conciliadores.id}"><c:out value="${conciliadores.nome}"/></option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label>Selecione a vara</label>
                <select class="form-control form-control-lg" id="vara">
                    <option value="0">Nenhum</option>
                    <c:forEach items="${varas}" var="varas">
                        <option value="${varas.id}"><c:out value="${varas.nome}"/></option>
                    </c:forEach>

                </select>
            </div>

            <div class="form-group">
                <label>Selecione Preposto/Procurador</label>
                <select class="form-control form-control-lg" id="prep">
                    <option value="0">Nenhum</option>
                    <c:forEach items="${preposto}" var="prepostos">
                        <option value="${prepostos.id}"><c:out value="${prepostos.nome}"/></option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group text-right m-b-0">
                <button id="btnAtualizar" class="btn btn-primary btn-rounded waves-effect waves-light pull-right">Filtrar</button>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>

    <div class="col-xs-12 ">
        <div class="box box-primary">

            <div class="row">
                <div class="col-xs-12 ">
                    <div class="card-box">
                        <div id="container1" style="min-width: 310px; max-width: 800px; height: 400px; margin: 0 auto"></div>

                        <div id="cejuscTabela"></div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


    </section>
</div>

<c:import url="${request.contextPath}/rodape"/>

<script src="/cadastro_conciliacao/bower_components/js/highcharts/highcharts.js"></script>
<script src="/cadastro_conciliacao/bower_components/js/highcharts/modules/exporting.js"></script>
<script src="/cadastro_conciliacao/bower_components/js/highcharts/modules/data.js"></script>
<script src="/cadastro_conciliacao/bower_components/js/highcharts/modules/series-label.js"></script>


<script>

    function grafico_cejusc(data) {
        Highcharts.chart('container1', {
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: 'ESTATISTÍCA TOTAL'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    }
                }
            },
            series: [{
                name: 'Brands',
                colorByPoint: true,
                data: data
            }]
        });
    }

</script>

<script>
    function atualizarTabelaGraficos(id, data) {
        var tr = '<table class="table table-striped table-bordered">';
        tr += '<tr>';
        data.forEach(function(i, a, b) {
            tr += '<th>' + i.name + '</th>';
        });
        tr += '</tr>';
        tr += '<tr>';
        data.forEach(function(i, a, b) {
            tr += '<td>' + i.y + '</td>';
        });
        tr += '</tr>';
        tr += '</table>';
        $('#' + id).html(tr);
    }
    function atualizarGrafico(url, query, callback, callbackTabela, idTabela) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                callback(JSON.parse(this.responseText));
                callbackTabela(idTabela, JSON.parse(this.responseText));
            }
        };
        xhttp.open('GET', url  + query, true);
        xhttp.send();
    }
    function atualizarTabela(url, query, tabela) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                var dados = JSON.parse(this.responseText);
                dados = dados[0];

                var tr = '<tr>';
                for (var k in dados) {
                    tr += '<td>' + dados[k] + '</td>';
                }
                tr += '</tr>';

                $('#' + tabela).html(tr);
            }
        };
        xhttp.open('GET', url  + query, true);
        xhttp.send();
    }
    var btnAtualizar = document.querySelector('#btnAtualizar');
    btnAtualizar.onclick = function () {
        var ano = document.querySelector('#ano').value;
        var mes = document.querySelector('#mes').value;
        var conc = document.querySelector('#conc').value;
        var vara = document.querySelector('#vara').value;
        var prep = document.querySelector('#prep').value;
        var query = ano;
        if (mes !== '' && mes !== undefined)
            query += '-' + mes +'/'+conc+'/'+vara+'/'+prep;
        atualizarGrafico('/cadastro_conciliacao/estatistica/mensal/', query, grafico_cejusc, atualizarTabelaGraficos, 'cejuscTabela');

    }
    var query = (new Date()).getFullYear();
    atualizarTabela('/cadastro_conciliacao/estatistica/mensal/', query, 'arquivo');

</script>
