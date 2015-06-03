<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Monitor</title>

    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!-- header start -->
<header class="navbar navbar-static-top" role="banner">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle collapsed" type="button" data-toggle="collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="/" class="navbar-brand">Monitor</a>
        </div>
        <nav class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li>
                    <a href="/css/">CSS</a>
                </li>
                <li class="active">
                    <a href="/components/">Components</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="">Expo</a></li>
            </ul>
        </nav>
    </div>
</header>
<!-- header end -->

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

<script src="/static/js/jquery-1.11.3.js"></script>
<script src="/static/js/bootstrap.js"></script>
<script src="/static/js/echarts/echarts-2.2.3.js"></script>
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
        $.getJSON('http://localhost:8080/ajax/monitor/data', function (data) {
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