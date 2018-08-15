<#assign ctx=request.contextPath>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>

    <title>登录</title>

    <meta name="HandheldFriendly" content="True"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link rel="stylesheet" type="text/css"
    href="https://fonts.cat.net/css?family=Merriweather:300,700,700italic,300italic|Open+Sans:700,400"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/font-awesome-4.5.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/js/bootstrap/css/bootstrap.min.css"/>

    <meta name="description" content="能简单化绝不复杂，zzitbar，java，java学习"/>
    <link rel="shortcut icon" href="/favicon.png" type="image/png"/>
    <link rel="canonical" href="http://zzitbar.com/"/>
    <meta name="referrer" content="no-referrer-when-downgrade"/>
    <#--<link rel="next" href="http://zzitbar.com/page/2/"/>-->
</head>
<body class="home-template nav-closed">
<div class="site-wrapper">
    <header class="main-header-login " style="background-image: url(${ctx}/bing/today)">
        <div class="vertical">
            <div class="main-header-content login">
                <h3 class="text-center" style="color: #fff;">登录</h3>
                <form class="" id="loginForm" role="form">
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user"></i></span>
                            <input type="text" class="form-control" placeholder="username" name="username" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                            <input type="password" class="form-control" placeholder="password" name="password" required>
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <button type="button" class="btn btn-primary btn-block" onclick="login()">登录</button>
                    </div>
                </form>
            </div>
        </div>
        <section class="login-copyright"><a href="http://zzitbar.com">zzitbar.com</a> &copy; ${.now?string("yyyy")}</section>
    </header>
</div>
<script src="${ctx}/static/js/jquery-2.2.3.min.js"></script>
<script>
    function login() {
        $.post("${request.contextPath}/login", $("#loginForm").serialize(), function (result) {
            console.log(result);
            if (result && result.status === "success") {
                window.localStorage.setItem("token", 'Bearer '+result.data);
                window.location.href = "${request.contextPath}/admin/index";
            } else {
                alert(result.errorMsg);
            }
        }, "json")
    }
</script>
</body>
</html>