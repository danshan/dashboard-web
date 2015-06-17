<#assign ava=JspTaglibs["/WEB-INF/tld/avatar-tags.tld"]>
<@ava.content holderid="PlaceHolderStyleSheet">
<link rel="stylesheet" href="<@ava.staticResource resource="/s/css/g.base.css" />" type="text/css" />
<link rel="stylesheet" href="<@ava.staticResource resource="/s/css/c.about.css" />" type="text/css" />
</@ava.content>
<title>
<#if exception.title?exists>${exception.title}<#else>出错</#if>-大众点评网
</title>
<body>
<div>
</div>

<div class="section_w">
    <div class="aboutBox errorMessage">
        <h2><#if exception.title?exists>${exception.title}<#else>出错</#if></h2>
        <p><#if exception.title?exists>${exception.message}<#else>出错</#if></p>
        <p>感谢您使用大众点评网！</p>
        <ul>
            <li>• <a href="/" class="BL">返回首页</a></li>

        </ul>
    </div>
</div>
</body>