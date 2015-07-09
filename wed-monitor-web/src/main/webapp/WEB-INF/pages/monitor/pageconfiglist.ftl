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

    <p>
        <a type="button" class="btn btn-primary" href="/monitor/pageconfigdetail"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>New Page</a>
    </p>

    <table class="table table-striped table-hover">
        <caption>所有监控页</caption>
        <thead>
            <th>Page ID</th>
            <th>Page Name</th>
            <th>Page Desc</th>
            <th>Add Time</th>
            <th>Update Time</th>
            <th>Actions</th>
        </thead>
        <tbody>
            <#if pageConfigs?has_content>
                <#list pageConfigs as pageConfig>
                    <tr>
                        <td>${pageConfig.pageId}</td>
                        <td>${pageConfig.pageName}</td>
                        <td>${pageConfig.pageDesc}</td>
                        <td>${pageConfig.addTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                        <td>${pageConfig.updateTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                        <td>
                            <p>
                                <a href="/monitor/monitor?pageId=${pageConfig.pageId}" type="button" class="btn"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></a>
                                <a href="/monitor/pageconfigdetail?pageId=${pageConfig.pageId}" type="button" class="btn"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                                <a href="#" type="button" class="btn"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
                            </p>
                        </td>
                    </tr>
                </#list>
            </#if>
        </tbody>
    </table>

</div>

</body>
</html>