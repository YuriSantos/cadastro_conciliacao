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
        <div class="card-box">
            <div class="form-group">
                <select class="form-control form-control-lg" id="ano">
                    <option value="2018">2018</option>
                    <option value="2017">2017</option>
                </select>
            </div>

            <div class="form-group">
                <select class="form-control" id="mes">
                    <option value="">Mês</option>
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

            <div class="form-group text-right m-b-0">
                <button id="btnAtualizar" class="btn btn-primary btn-rounded waves-effect waves-light pull-right">Atualizar</button>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</div>

<div class="content-wrapper">
    <section class="content">
        <div class="col-md-6">
            <!-- DONUT CHART -->

            <div class="card-box">


                <div id="container1" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>

            </div>

            <!-- /.box -->


        </div>
    </section>
</div>

<c:import url="${request.contextPath}/rodape"/>

<script src="/bower_components/js/highcharts/highcharts.js"></script>
<script src="/bower_components/js/highcharts/modules/exporting.js"></script>
<script src="/bower_components/js/highcharts/modules/data.js"></script>
<script src="/bower_components/js/highcharts/modules/series-label.js"></script>


<script>
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            grafico_cejusc(JSON.parse(this.responseText));
        }
    };

    xhttp.open('GET', '/estatistica/total', true);
    xhttp.send();

    function grafico_cejusc(data) {
        Highcharts.chart('container1', {
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: 'ESTATISTÍCA ANUAL'
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
        data.forEach(function(i, a, b) {
            tr += '<tr>';
            tr += '<th>' + i.name + '</th>';
            tr += '<td>' + i.y + '</td>';
            tr += '</tr>';
        });
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

    var query = '?ano='+ (new Date()).getFullYear();
    atualizarTabela('/estatistica/mensal', query, 'arquivo');
</script>
