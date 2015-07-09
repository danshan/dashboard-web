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
                    <label for="J_datasource" class="col-sm-3 control-label">数据源</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="J_datasource" onchange="changeDatasource()">
                            <option mime="application/json">MongoWedding</option>
                            <option mime="text/x-mysql">MysqlWed</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="well sidebar-nav J_codeMirror">
                <div class="nav-header"><a href="#" onclick="autoResize()" class="glyphicon glyphicon-resize-full" id ="icon-resize" ></a>option</div>
                <textarea id="code" name="code">
{
    "collection": "Event",
    "query": {}
    "keys": {
        "createTime": 1,
        "pcUrlRewriteID": 1,
        "mUrlRewriteID": 1
    },
    "xAxis": "createTime"
}
                </textarea>
            </div><!--/.well -->
        </div><!--/span-->
        <div id="graphic" class="col-md-8">
            <div class="main" style="height:400px"></div>
            <div>
                <button type="button" class="btn btn-sm btn-success" onclick="refresh(true)">
                    <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷 新
                </button>
                <button type="button" class="btn btn-sm btn-success" onclick="refresh(true)">
                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>保 存
                </button>
                <span id='wrong-message' style="color:red"></span>
            </div>
        </div><!--/span-->
    </div><!--/row-->

</div><!--/.fluid-container-->
<!-- container end -->
<script src="/static/js/datapreview.js"></script>
</body>
</html>