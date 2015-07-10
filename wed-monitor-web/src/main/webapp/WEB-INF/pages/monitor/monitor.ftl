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
    <script src="/static/js/bootstrap/bootstrap-datepicker.js"></script>
    <script src="/static/js/echarts/echarts-2.2.3.js"></script>
</head>
<body>

<@navbar/>
<!-- container start -->
<div class="container">

    <div class="jumbotron">
        <h1>${pageConfig.pageName}</h1>
        <p>${pageConfig.pageDesc}</p>
    </div>
    <!-- input filter start -->
    <#if pageConfig.inputFilters?has_content || pageConfig.timeFilter?has_content>
    <div class="J_form">
        <div class="form-inline">
            <#if pageConfig.timeFilter?has_content>
                <div class="form-group">
                    <label for="J_datepicker">时间范围</label>
                    <div class="input-daterange input-group" id="J_datepicker">
                        <input type="text" class="input-sm form-control" readonly="readonly" name="timeFilterStart" />
                        <span class="input-group-addon">to</span>
                        <input type="text" class="input-sm form-control" readonly="readonly" name="timeFilterEnd" />
                    </div>
                </div>
            </#if>
        </div>
        <div class="form-inline">
            <#if pageConfig.inputFilters?has_content>
                <#list pageConfig.inputFilters as filter>
                    <div class="form-group">
                        <label for="id_${filter.name}">${filter.desc}</label>
                        <input id="id_${filter.name}" type="text" class="input-sm form-control" name="${filter.name}">
                    </div>
                </#list>
            </#if>
        </div>
        <button type="button" class="btn btn-sm btn-success" onclick="refresh(true)">查询</button>
    </div>
    </#if>
    <!-- input filter end -->

    <#if queryTemplate?has_content>
        <div class="alert alert-success" role="alert">
            <a href="/monitor/datapreview?pageId=${pageId}" class="btn btn-link">Query template here.</a>
        </div>
    <#else>
        <div class="alert alert-warning" role="alert">
            <p>
                Can not find query template.<a href="/monitor/datapreview?pageId=${pageId}" class="btn btn-link">Create one.</a>
            </p>
        </div>
    </#if>

    <#if chartOption?has_content>
        <div class="alert alert-success" role="alert">
            <a href="/monitor/optionpreview?pageId=${pageId}" class="btn btn-link">Chart option here.</a>
        </div>
    <#else>
        <div class="alert alert-warning" role="alert">
            <p>
                Can not find chart option.<a href="/monitor/optionpreview?pageId=${pageId}" class="btn btn-link">Create one.</a>
            </p>
        </div>
    </#if>

    <p id='wrong-message' style="color:red"></p>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="chart_${pageId}" style="height:400px"></div>
    <div style="height:400px">
        <table id="table_${pageId}" class="table table-striped table-condensed table-hover"/>
    </div>

</div>
<!-- container end -->

<script type="text/javascript">
    chartData = {
        pageId: "${pageId}"
    };
</script>
<script src="/static/js/monitor/chartfactory.js"></script>
<script src="/static/js/monitor/monitor.js"></script>
</body>
</html>