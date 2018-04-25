<#include "../common/layout.ftl">
<#assign pageTitle>
    ${article.articleTitle!}
</#assign>
<#assign pageBreadCrumbs>
    ${article.articleTitle!}
</#assign>
<#assign pageJavascript>
<script src="${request.contextPath}/static/js/editormd/js/lib/marked.min.js"></script>
<script src="${request.contextPath}/static/js/editormd/js/lib/prettify.min.js"></script>
<script src="${request.contextPath}/static/js/editormd/js/lib/raphael.min.js"></script>
<script src="${request.contextPath}/static/js/editormd/js/lib/underscore.min.js"></script>
<script src="${request.contextPath}/static/js/editormd/js/lib/sequence-diagram.min.js"></script>
<script src="${request.contextPath}/static/js/editormd/js/lib/flowchart.min.js"></script>
<script src="${request.contextPath}/static/js/editormd/js/lib/jquery.flowchart.min.js"></script>
<script>
    var testEditor;
    $(function () {
        testEditor = editormd.markdownToHTML("doc-content", {//注意：这里是上面DIV的id
            htmlDecode: "style,script,iframe",
            emoji: true,
            taskList: true,
            tex: true, // 默认不解析
            flowChart: true, // 默认不解析
            sequenceDiagram: true, // 默认不解析
            codeFold: true
        });
        BaseJs.loadTop();
    });
</script>
</#assign>

<@mainlayout pageTitle=pageTitle pageJavascript=pageJavascript pageBreadCrumbs=pageBreadCrumbs>
<div class="panel panel-default">
    <div class="panel-body">
        <h2>${article.articleTitle!}</h2>
        <div class="article-base">
            <span class="article-tag"><i class="glyphicon glyphicon-calendar"></i> ${article.articleCreateDate?string("yyyy-MM-dd")}</span>
            <span class="article-tag"><i class="glyphicon glyphicon-book"></i> ${article.articleCategoryName!'无'}</span>
            <#if article.tags??>
                <span class="article-tag">
                    <i class="glyphicon glyphicon-tags"></i>
                <#list article.tags as tag>
                     <a>${tag!}</a>
                </#list>
                </span>
            </#if>
            <a href="javascript:void(0)" class="pull-right -mob-share-open"><i class="fa fa-share-alt "></i></a>
        </div>
        <hr>
        <#if article.articleAbstract?? && ""!=article.articleAbstract>
        <#--<div class="article-abstract">-->
            <#--<footer>${article.articleAbstract!}</footer>-->
        <#--</div>-->
            <pre>${article.articleAbstract!}</pre>
        </#if>
        <div id="doc-content">
            <textarea style="display:none;">${article.articleContent!}</textarea>
        </div>
    </div>
</div>

<div class="-mob-share-ui" style="display: none;">
    <ul class="-mob-share-list">
        <li class="-mob-share-weixin"><p>微信</p></li>
        <li class="-mob-share-weibo"><p>新浪微博</p></li>
        <li class="-mob-share-qzone"><p>QQ空间</p></li>
        <li class="-mob-share-qq"><p>QQ好友</p></li>
    </ul>
    <div class="-mob-share-close">取消</div>
</div>
<div class="-mob-share-ui-bg"></div>
</@mainlayout>