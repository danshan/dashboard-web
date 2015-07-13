<#include "/WEB-INF/pages/common/navbar.ftl" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Monitor Chart Preview</title>

    <link href="/static/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/codemirror/codemirror.css" rel="stylesheet">
    <link href="/static/css/codemirror/monokai.css" rel="stylesheet">

    <script src="/static/js/jquery/jquery-1.11.3.js"></script>
    <script src="/static/js/bootstrap/bootstrap.js"></script>
    <script src="/static/js/codemirror/codemirror.js"></script>
    <script src="/static/js/codemirror/javascript.js"></script>
    <script src="/static/js/codemirror/sql.js"></script>
</head>
<body>

<@navbar/>

<!-- container start -->
<div class="container-fluid">
    <div class="row-fluid">
        <div id="sidebar-code" class="col-md-4">
            <div class="form-horizontal">
                <div class="form-group">
                    <label for="datasource" class="col-sm-3 control-label">数据源</label>
                    <div class="col-sm-8">
                        <select class="form-control J_datasource" id="datasource" onchange="changeDatasource()">
                            <option mime="application/json">MongoWedding</option>
                            <option mime="text/x-mysql">MysqlWed</option>
                        </select>
                    </div>
                </div>
            </div>
            <input hidden="hidden" class="J_pageId" value="${pageId}">
            <div class="well sidebar-nav J_codeMirror">
                <div class="nav-header"><a href="#" onclick="autoResize()" class="glyphicon glyphicon-resize-full" id ="icon-resize" ></a>option</div>
                <textarea class="J_code" name="code">${(queryTemplate.query)!demoMongoQuery}</textarea>
            </div><!--/.well -->
            <div class="form-inline">
                <div class="form-group">
                    <label for="xAxisLabel">x轴</label>
                    <input type="text" class="form-control J_xAxis" value="${(queryTemplate.xAxis)!demoMongoXAxis}" id="xAxisLabel" placeholder="x轴">
                </div>
            </div>
        </div><!--/span-->
        <div id="graphic" class="col-md-8">
            <pre class="J_result" style="height:600px"></pre>
            <div>
                <button type="button" class="btn btn-sm btn-success" onclick="refresh(true)">
                    <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷 新
                </button>
                <button type="button" class="btn btn-sm btn-success" onclick="saveQueryTemplate()">
                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>保 存
                </button>
                <span id='wrong-message' style="color:red"></span>
            </div>
        </div><!--/span-->
    </div><!--/row-->

</div><!--/.fluid-container-->
<!-- container end -->
<script src="/static/js/monitor/datapreview.js"></script>
</body>
</html>