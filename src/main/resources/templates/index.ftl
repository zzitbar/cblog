<#assign ctx=request.contextPath>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>

    <title>Simplify</title>

    <meta name="HandheldFriendly" content="True"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <#--<link rel="stylesheet" type="text/css"-->
          <#--href="https://fonts.cat.net/css?family=Merriweather:300,700,700italic,300italic|Open+Sans:700,400"/>-->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/font-awesome-4.5.0/css/font-awesome.min.css"/>

    <meta name="description" content="能简单化绝不复杂，zzitbar，java，java学习"/>
    <link rel="shortcut icon" href="/favicon.png" type="image/png"/>
    <link rel="canonical" href="http://zzitbar.com/"/>
    <meta name="referrer" content="no-referrer-when-downgrade"/>
    <link rel="next" href="http://zzitbar.com/page/2/"/>
</head>
<body class="<#if articles.currentPage gt 1>paged<#else >home-template</#if> nav-closed">
<div class="nav">
    <h3 class="nav-title">Menu</h3>
    <a href="#" class="nav-close" onclick="navShow()">
        <span class="hidden">Close</span>
    </a>
    <ul>
        <li class="nav- nav-current"><a href="${ctx}/">主页</a></li>
        <li class="nav-"><a href="${ctx}/">归档</a></li>
        <li class="nav-"><a href="${ctx}/link/">友链</a></li>
        <li class="nav-"><a href="${ctx}/about/">关于</a></li>
    </ul>

    <a class="subscribe-button icon-feed" href="${ctx}/rss/">Subscribe</a>
</div>
<span class="nav-cover"></span>
<div class="site-wrapper">
    <header class="main-header " style="background-image: url(${ctx}/bing/today)">
        <nav class="main-nav overlay clearfix">

            <a class="menu-button fa fa-reorder" href="#" onclick="navShow()"><span class="word">Menu</span></a>
        </nav>
        <div class="vertical">
            <div class="main-header-content inner">
                <h1 class="page-title">Simplify</h1>
                <h2 class="page-description" id="hitokoto">能简单化绝不复杂</h2>
            </div>
        </div>
        <a class="scroll-down fa fa-angle-double-down " href="#content"><span class="hidden">Scroll Down</span></a>
        <span class="image-info fa-stack fa-lg ">
          <i class="fa fa-circle fa-stack-2x"></i>
          <i class="fa fa-info fa-stack-1x fa-inverse"></i>
        </span>
        <div class="bing-info" style="display: none">
            <p class="text-white"><i class="glyphicon glyphicon-calendar"></i> 2018-05-08</p>
            <p class="text-white"><i class="glyphicon glyphicon-map-marker"></i> 英格兰多塞特郡</p>
            <p class="text-white"><i class="glyphicon glyphicon-copyright-mark"></i> (@ Brian Jannsen/Danita Delimont) © (Bing China)</p>
        </div>
    </header>

    <main id="content" class="content" role="main">
    <#list articles.rows as article>
        <article class="post">
            <header class="post-header">
                <h2 class="post-title"><a href="${ctx}/article/${article.id!}">${article.articleTitle!}</a></h2>
            </header>
            <section class="post-excerpt">
                <p>${article.articleAbstract!}<a class="read-more" href="${ctx}/article/${article.id!}">&raquo;</a></p>
            </section>
            <footer class="post-meta">
                <img class="author-thumb"
                     src="http://p7mkg1wor.bkt.clouddn.com//avatar/timg.jpg"
                     alt="${article.author!"zzitbar"}" nopin="nopin"/>
                <a href="#">${article.author!"zzitbar"}</a>

                <i class="fa fa-comment"></i> ${article.articleCommentCount!"0"}
                <i class="fa fa-eye"></i> ${article.articleViewCount!"0"}
                <time class="post-date"
                      datetime="${article.articleCreateDate?string("yyyy-MM-dd")}">${article.articleCreateDate?string("yyyy-MM-dd")}</time>
            </footer>
        </article>
    </#list>
        <nav class="pagination" role="navigation">
            <#if articles.currentPage gt 1>
                <a class="newer-posts" href="${ctx}?page=${articles.currentPage-1}"><span aria-hidden="true">&larr;</span> 上一页</a>
            </#if >
            <span class="page-number">Page ${articles.currentPage} of ${articles.totalPage}</span>
            <#if articles.isMore!=0>
            <a class="older-posts" href="${ctx}?page=${articles.currentPage+1}">下一页 <span aria-hidden="true">&rarr;</span></a>
            </#if>
        </nav>
    </main>
    <footer class="site-footer clearfix">
        <section class="copyright"><a href="http://zzitbar.com">zzitbar.com</a> &copy; ${.now?string("yyyy")}</section>
        <section class="poweredby"></section>
    </footer>
</div>
<script src="${ctx}/static/js/jquery-2.2.3.min.js"></script>
<script src="${request.contextPath}/static/js/front.js"></script>
<script>
    $(function () {
        $(".image-info").on("hover", function (e) {
            $("div.bing-info").show();
        })
    })
</script>
</body>
</html>