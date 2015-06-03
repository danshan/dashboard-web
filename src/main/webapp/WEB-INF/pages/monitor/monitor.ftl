<#include "/WEB-INF/pages/common/navbar.ftl" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Monitor</title>

    <link href="/static/css/bootstrap/bootstrap.min.css" rel="stylesheet">

    <script src="/static/js/jquery/jquery-1.11.3.js"></script>
    <script src="/static/js/bootstrap/bootstrap.js"></script>
    <script src="/static/js/echarts/echarts-2.2.3.js"></script>
</head>
<body>

<@navbar/>
<!-- container start -->
<div class="container">
    <!-- input filter start -->
    <#if inputFilters?has_content>
        <div class="form-inline">
            <#list inputFilters as filter>
                <div class="form-group">
                    <label for="ID_${filter.name}">${filter.desc}</label>
                    <input id="ID_${filter.name}" type="text" class="form-control" dataname="${filter.name}">
                </div>
            </#list>
            <button type="button" class="btn btn-success">查询</button>
        </div>

    </#if>
    <!-- input filter end -->

    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div>
        <div id="chart_${pageId}" style="height:400px"></div>
    </div>
    <div>
        <div id="table_${pageId}" style="height:400px"></div>
    </div>

</div>
<!-- container end -->

<script type="text/javascript">
    // 路径配置
    require.config({
        paths: {
            echarts: '/static/js/echarts'
        }
    });

    var options = [];

    function loadOptions() {
        $.getJSON('http://localhost:8080/ajax/monitor/options', function (data) {
            options = data.msg.options;
        });
    }

    function loadData() {
        $.getJSON('http://localhost:8080/ajax/monitor/data?pageId=1', function (data) {
            for (var optionIndex = 0; optionIndex < options.length; optionIndex++) {
                for (var seriesIndex= 0; seriesIndex < options[optionIndex].series.length; seriesIndex++) {
                    options[optionIndex].series[seriesIndex].data = data.msg.data[seriesIndex];
                }
            }
        });
    }

    function buildChart() {
        // 使用
        require(
                [
                    'echarts',
                    'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
                ],
                function (ec) {
                    // 基于准备好的dom，初始化echarts图表
                    var myChart = ec.init(document.getElementById('chart_${pageId}'));

                    myChart.setOption(options[0]);
                }

        );
    }

    loadOptions();
    loadData();
    buildChart();

</script>
</body>
</html>