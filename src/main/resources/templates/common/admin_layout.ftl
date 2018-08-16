<#macro mainlayout pageTitle="" pageCss="" pageJavascript="" pageBreadCrumbs="" currentMenu="">
    <#assign ctx=request.contextPath>
<!DOCTYPE html>
<html lang="en">
    <#include "./admin_head.ftl">
<body>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Cblog Dashboard</a>
        </div>
        <div>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        ${user.username!} <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#" data-toggle="modal" data-target="#modifyPwdModal">修改密码</a></li>
                        <li class="divider"></li>
                        <li><a href="#" onclick="logout();">退出</a></li>
                    </ul>
                </li>
                <li><a href="#" data-url="${ctx}" class="nav-list">返回前台</a></li>
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
            <a href="#" class="list-group-item menu-list" data-url="${ctx}/openapi">开放 API </a>
        </div>
        <div class="col-sm-9 col-lg-10" id="content">
            <#nested>
        </div>
    </div>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="modifyPwdModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改密码</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="changePwdForm">
                    <div class="form-group">
                        <label>用户名</label>
                        <input type="text" class="form-control" name="username" value="${user.username!}" readonly>
                    </div>
                    <div class="form-group">
                        <label>原密码</label>
                        <input type="password" class="form-control" name="password" required>
                    </div>
                    <div class="form-group">
                        <label>新密码</label>
                        <input type="password" class="form-control" name="newpassword" required minlength="6">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="changePwd()">修改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
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
    function logout() {
        $.post("${ctx}/logout", function (data) {
            if (data.status==BaseJs.SUCCESS) {
                window.location.href = "${ctx}";
            } else {
                BaseJs.showError(data.errorMsg);
            }
        }, "json")
    }
    function changePwd() {
        BaseJs.submit("changePwdForm", "${ctx}/changePwd", function () {
            BaseJs.showSuccess("修改成功");
            logout();
        })
    }
</script>
    ${pageJavascript}
</body>
</html>
</#macro>

