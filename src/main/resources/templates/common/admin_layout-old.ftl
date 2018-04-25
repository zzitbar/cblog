<#macro mainlayout pageTitle="" pageCss="" pageJavascript="" pageBreadCrumbs="" currentMenu="">
    <#assign ctx=request.contextPath>
<!DOCTYPE html>
<html lang="en">
    <#include "./admin_head.ftl">
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">博客后台管理</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:void(0);">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    管理员
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">退出</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="${ctx}">返回前台</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item" id="article">
                    <a href="javascript:void(0);"><i class="fa fa-archive"></i> 文章管理</a>
                    <dl class="layui-nav-child">
                        <dd id="articleList"><a href="${ctx}/admin/article/manager"><i class="fa fa-list"></i> 文章列表</a></dd>
                        <dd id="articleEdit"><a href="${ctx}/admin/article/edit"><i class="fa fa-pencil"></i> 写文章</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">分类管理</a></li>
                <li class="layui-nav-item"><a href="">标签管理</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <#nested>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © ${.now?string("yyyy")} coderme.cn
    </div>
</div>
<#include "./static_js.ftl">
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;
        var currentMenu = '${currentMenu!}';
        if (currentMenu && ''!=currentMenu) {
            $("li.layui-nav-itemed").removeClass("layui-nav-itemed");
            $(".layui-this").removeClass("layui-this");
            var $currentMenu = $("#"+currentMenu);
            $($currentMenu).addClass("layui-this");
            var $parent = $($currentMenu).parents("li.layui-nav-item");
            if ($($parent).length>0) {
                $($parent).addClass("layui-nav-itemed")
            }
        }
    });

</script>
    ${pageJavascript}
</body>
</html>
</#macro>

