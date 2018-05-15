<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: estagio.yuris
  Date: 22/11/2017
  Time: 09:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="${request.contextPath}/top-menu"/>
<c:import url="${request.contextPath}/left-menu"/>



<div class="content-wrapper">
    <section class="content">
        <div class="col-md-6">
            <!-- DONUT CHART -->

            <div class="card-box">


                <div id="container1" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>

                <div id="cejuscTabela"></div>



            </div>

            <!-- /.box -->


        </div>
    </section>
</div>

<c:import url="${request.contextPath}/rodape"/>

<script src="/cadastro_conciliacao/bower_components/js/highcharts/highcharts.js"></script>
<script src="/cadastro_conciliacao/bower_components/js/highcharts/modules/exporting.js"></script>
<script src="/cadastro_conciliacao/bower_components/js/highcharts/modules/data.js"></script>
<script src="/cadastro_conciliacao/bower_components/js/highcharts/modules/series-label.js"></script>


<script>
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            grafico_cejusc(JSON.parse(this.responseText));
        }
    };

    xhttp.open('GET', 'estatistica/total', true);
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
                text: 'ESTATÍSTICA EM TEMPO REAL ' +
                '(Conciliômetro)'
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
    function TabelaGraficos(id, data) {
        var soma=0;
        var tr = '<table class="table table-striped table-bordered">';
        tr += '<tr>';
        data.forEach(function(i, a, b) {
            tr += '<th>' + i.name + '</th>';
        });
        tr += '<th>' + 'Total' + '</th>';
        tr += '</tr>';
        tr += '<tr>';
        data.forEach(function(i, a, b) {
            tr += '<td>' + i.y + '</td>';
            var p = parseInt(i.y,10);
            soma = soma + p;
        });
        tr += '<td>' + soma + '</td>';
        tr += '</tr>';
        tr += '</table>';
        $('#' + id).html(tr);}

    function atualizarGrafico(url,  callback, callbackTabela, idTabela) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                callback(JSON.parse(this.responseText));
                callbackTabela(idTabela, JSON.parse(this.responseText));
            }
        };
        xhttp.open('GET', url, true);
        xhttp.send();
    }
    atualizarGrafico('estatistica/total', grafico_cejusc, TabelaGraficos, 'cejuscTabela');

</script>