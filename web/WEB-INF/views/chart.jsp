<%--
  Created by IntelliJ IDEA.
  User: estagio.yuris
  Date: 09/01/2018
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chart teste</title>
</head>
<body>
<div class="col-xs-12 col-lg-12 col-xl-4">
    <div class="card-box">


        <div id="container1" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>

    </div>
</div><!-- end col-->


<script src="bower_components/js/highcharts/highcharts.js"></script>
<script src="bower_components/js/highcharts/modules/exporting.js"></script>
<script src="bower_components/js/highcharts/modules/data.js"></script>
<script src="bower_components/js/highcharts/modules/series-label.js"></script>


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
                text: 'CEJUSC - PROCESSUAL'
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
</body>
</html>
