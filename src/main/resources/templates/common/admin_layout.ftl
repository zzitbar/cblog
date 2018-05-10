<#macro mainlayout pageTitle="" pageCss="" pageJavascript="" pageBreadCrumbs="" currentMenu="">
    <#assign ctx=request.contextPath>
<!DOCTYPE html>
<html lang="en">
    <#include "./admin_head.ftl">
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">
                            <img alt="Brand" src="...">
                        </a>
                    </div>
                    <div class="collapse navbar-collapse" id="navbar-collapse">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="#">Link</a></li>
                            <li><a href="${request.contextPath}/">返回前台</a></li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </div>
            </nav>
        </div>
    </div>
    <div class="row">
        <div class="col-md-2">
            <ul id="main-nav" class="nav nav-tabs nav-stacked" style="">
                <li class="active" id="dashboard">
                    <a href="#">
                        <i class="glyphicon glyphicon-th-large"></i>
                        首页
                    </a>
                </li>
                <li>
                    <a href="#article" class="nav-header collapsed" data-toggle="collapse">
                        <i class="glyphicon glyphicon-cog"></i>
                        文章管理
                        <span class="pull-right glyphicon glyphicon-chevron-down"></span>
                    </a>
                    <ul id="article" class="nav nav-list collapse secondmenu" style="height: 0px;">
                        <li id="articleList"><a href="${ctx}/admin/article/manager"><i class="fa fa-list"></i> 文章列表</a></li>
                        <li id="articleEdit"><a href="${ctx}/admin/article/edit"><i class="fa fa-pencil"></i> 写文章</a></li>
                    </ul>
                </li>
                <li id="category">
                    <a href="${ctx}/admin/category">
                        <i class="glyphicon glyphicon-credit-card"></i>
                        分类管理
                    </a>
                </li>
                <li>
                    <a href="./grid.html">
                        <i class="glyphicon glyphicon-globe"></i>
                        标签管理
                        <span class="label label-warning pull-right">5</span>
                    </a>
                </li>
                <li>
                    <a href="./charts.html">
                        <i class="glyphicon glyphicon-calendar"></i>
                        统计
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="glyphicon glyphicon-fire"></i>
                        关于系统
                    </a>
                </li>
            </ul>
        </div>
        <div class="col-md-10">
            <div style="padding: 15px;">
            <#nested>
            </div>
        </div>
    </div>
</div>
<#include "./static_js.ftl">
<script>
    var currentMenu = '${currentMenu!}';
    if (currentMenu && ''!=currentMenu) {
        $("li.active").removeClass("active");
        $("ul.collapse").removeClass("in");
        var $currentMenu = $("#"+currentMenu);
        $($currentMenu).addClass("active");
        var $parent = $($currentMenu).parents("ul.collapse");
        if ($($parent).length>0) {
            $($parent).collapse('show')
            // $($parent).addClass("in");
        }
    }
</script>
    ${pageJavascript}
</body>
</html>
</#macro>

