<#macro mainlayout pageTitle="" pageCss="" pageJavascript="" pageBreadCrumbs="" currentMenu="">
    <#assign ctx=request.contextPath>
<!DOCTYPE html>
<html lang="en">
    <#include "./admin_head.ftl">
<body>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Stock View</a>
        </div>
        <div>
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="#" data-url="${ctx}/index" class="nav-list">首页</a></li>
                <li><a  href="#" data-url="${ctx}/stock" class="nav-list">股票交易数据</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid" id="page">
    <div class="row">
        <div class="col-sm-3 col-lg-2">
            <a href="#" class="list-group-item menu-list active" data-url="${ctx}/admin/index">
                首页
            </a>
            <a href="#" class="list-group-item menu-list" data-url="${ctx}/admin/article/manager"><i class="fa fa-list"></i> 文章列表</a>
            <a href="#" class="list-group-item menu-list" data-url="${ctx}/admin/article/edit"><i class="fa fa-pencil"></i> 写文章</a>
            <a href="#" class="list-group-item menu-list" data-url="${ctx}/admin/category"><i class="fa fa-columns"></i> 分类管理</a>
            <a href="#" class="list-group-item menu-list" data-url="${ctx}/stockInfo/index"><i class="fa fa-tags"></i> 标签管理</a>
            <a href="#" class="list-group-item menu-list" data-url="${ctx}/bing/api">Bing API </a>
            <a href="#" class="list-group-item menu-list" data-url="${request.contextPath}/job">定时任务管理</a>
        </div>
        <div class="col-sm-9 col-lg-10" id="content">
            <#nested>
        </div>
    </div>
</div>

<#include "./static_js.ftl">
<script>
    $(function () {
        $(".menu-list").on("click", function (e) {
            var $this = e.target;
            $($this).siblings().removeClass("active");
            $($this).addClass("active");
            var url = $($this).attr("data-url");
            $.get(url, function (result) {
                $("#content").html(result);
            });
        })
    })
</script>
    ${pageJavascript}
</body>
</html>
</#macro>

