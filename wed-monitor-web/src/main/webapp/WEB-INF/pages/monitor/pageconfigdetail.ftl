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
<div class="container">

    <div>
        <input hidden="hidden" class="J_pageId" value="${(pageConfig.pageId)!""}">
        <div class="form-group">
            <label for="pageName">Page Name</label>
            <input type="text" class="form-control J_pageName" id="pageName" value="${((pageConfig.pageName)!"")?html}" placeholder="Page Name">
        </div>
        <div class="form-group">
            <label for="pageDesc">Page Description</label>
            <input type="text" class="form-control J_pageDesc" id="pageDesc" value="${((pageConfig.pageDesc)!"")?html}" placeholder="Page Description">
        </div>

        <button type="submit" class="btn btn-default" onclick="submitPageConfig();">Submit</button>
    </div>

</div>

<script src="/static/js/monitor/pageconfigdetail.js"></script>
</body>
</html>