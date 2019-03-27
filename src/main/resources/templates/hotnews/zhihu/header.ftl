<#assign ctx=request.contextPath>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>

    <title>知乎API请求头设置</title>

    <meta name="HandheldFriendly" content="True"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <#--<link rel="stylesheet" type="text/css"-->
    <#--href="https://fonts.cat.net/css?family=Merriweather:300,700,700italic,300italic|Open+Sans:700,400"/>-->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/font-awesome-4.5.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <meta name="description" content="能简单化绝不复杂，zzitbar，java，java学习"/>
    <link rel="shortcut icon" href="/favicon.png" type="image/png"/>
    <link rel="canonical" href="http://zzitbar.com/"/>
    <meta name="referrer" content="no-referrer-when-downgrade"/>
    <#--<link rel="next" href="http://zzitbar.com/page/2/"/>-->
    <#--<link rel="stylesheet" href="${request.contextPath}/static/js/editormd/css/editormd.min.css">-->
</head>
<body class="paged nav-closed">
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
    </header>

    <main class="content" role="main">
        <div class="container-fluid">
            <div class="rows">
                <div class="col-md-8 col-md-offset-2">
                    <h3 class="text-center">知乎API请求头设置</h3>
                    <form class="form-horizontal" id="zhuhuHeaderForm">
                        <#list header?keys as key>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">${key}</label>
                                <div class="col-sm-10">
                                    <input type="text" rows="3" class="form-control" name="${key}"
                                           value="${header[key]!}"/>
                                </div>
                            </div>
                        </#list>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="button" class="btn btn-primary" onclick="saveHeader()">保存</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </main>
</div>
<script src="${ctx}/static/js/jquery-2.2.3.min.js"></script>
<script src="${ctx}/static/js/front.js"></script>
<script>
    function saveHeader() {
        $.post("${ctx}/hotnews/zhihu/header", $("#zhuhuHeaderForm").serialize(), function (result) {
            if (result.status == "success") {
                alert("成功");
                location.reload();
            } else {
                alert(result.errorMsg)
            }
        }, "json");
    }
</script>
</body>
</html>