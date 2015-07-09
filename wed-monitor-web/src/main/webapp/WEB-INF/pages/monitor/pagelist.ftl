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

    <table class="table table-striped table-hover">
        <caption>所有监控页</caption>
        <thead>
            <th>ID</th>
        </thead>
        <tbody>

        </tbody>
    </table>

</div>

</body>
</html>