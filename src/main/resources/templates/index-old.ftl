<#include "./common/layout.ftl">
<#assign pageTitle>
    首页
</#assign>
<#assign pageBreadCrumbs>
    首页
</#assign>
<#assign pageJavascript>
<script>
    $(function () {
        BaseJs.loadTop();
    })
</script>
</#assign>

<@mainlayout pageTitle=pageTitle pageJavascript=pageJavascript pageBreadCrumbs=pageBreadCrumbs currentMenu="blog">
<#list articles.rows as article>
<div class="panel panel-default">
    <div class="panel-body">
        <p class="article-title"><a href="${ctx}/article/${article.id!}">${article.articleTitle!}</a></p>
        <p class="text-left">
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
        </p>
        <p>
            <span class="article-tag"><i class="fa fa-comment"></i> <span class="text-danger">${article.articleCommentCount!"0"}</span> 回复</span>
            <span class="article-tag"><i class="fa fa-eye"></i> <a href="${ctx}/article/${article.id!}"><span class="text-success">${article.articleViewCount!"0"}</span> 浏览</a></span>
        </p>
        <p>${article.articleAbstract!}</p>
    </div>
    <#--<div class="panel-footer panel-footer-white">-->
        <#--<div><i class="fa fa-share-alt-square"></i></div>-->
        <#--<div class="social-share" data-sites="weibo,qq,qzone,tencent,wechat"></div>-->
    <#--</div>-->
</div>
</#list>
<nav aria-label="...">
    <ul class="pager">
        <#if articles.currentPage==1>
        <li class="previous disabled"><a href="javascript:void(0)"><span aria-hidden="true">&larr;</span> 上一页</a></li>
        <#else >
        <li class="previous"><a href="${ctx}?page=${articles.currentPage-1}"><span aria-hidden="true">&larr;</span> 上一页</a></li>
        </#if>
        <li>${articles.currentPage} / ${articles.totalPage}</li>
        <#if articles.isMore==0>
        <li class="next disabled"><a href="javascript:void(0)">下一页 <span aria-hidden="true">&rarr;</span></a></li>
        <#else >
        <li class="next"><a href="${ctx}?page=${articles.currentPage+1}">下一页 <span aria-hidden="true">&rarr;</span></a></li>
        </#if>
    </ul>
</nav>

</@mainlayout>