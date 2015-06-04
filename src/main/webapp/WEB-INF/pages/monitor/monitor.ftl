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
    <#if pageConfig.inputFilters?has_content>
        <div class="form-inline">
            <#list pageConfig.inputFilters as filter>
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
    <div id="chart_${pageId}" style="height:400px"></div>
    <div style="height:400px">
        <table id="table_${pageId}" class="table table-condensed"/>
    </div>

</div>
<!-- container end -->

<script type="text/javascript">
    chartData = {
        pageId: ${pageId}
    };
</script>
<script src="/static/js/chartfactory.js"></script>
<script src="/static/js/monitor.js"></script>
</body>
</html>