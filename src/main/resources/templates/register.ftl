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
    <style>
        .btn-toggle{
            font-size: 18px;
            color: rgba(8,33,85,.4);
            transition: color .3s, border .3s ease-in-out;
        }
        .btn-toggle:hover{
            text-decoration: none;
            color: #082155;
            font-weight: 500;
        }
        .btn-toggle.active {
            color: #082155;
            border-bottom: 3px solid #93a2bb;
        }
    </style>
</head>
<body class="home-template nav-closed">
<div class="site-wrapper">
    <header class="main-header-login " style="background: #ffffff">
        <div class="vertical">
            <div class="main-header-content login" style="text-align: left">
                <div class="btn-group btn-block">
                    <button type="button" class="btn btn-link btn-toggle active" data-target="#loginpage">登录</button>
                    <button type="button" class="btn btn-link btn-toggle" data-target="#registerpage">注册</button>
                </div>
                <#--<div>-->
                    <#--<span style="font-weight: 600; font-size: 15px; border-bottom: 2px solid #000">登录</span>-->
                    <#--<span>注册</span>-->
                <#--</div>-->
                <div>
                    <div id="loginpage">
                        <h3 class="text-center" style="color: #000;">登录</h3>
                        <form class="" id="loginForm" role="form">
                            <div class="form-group">
                                <div class="input-group ">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                                    <input type="email" class="form-control" placeholder="请输入邮箱" name="username"
                                           required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group ">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                    <input type="password" class="form-control" placeholder="请输入密码" name="password"
                                           required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div>
                                    <div id="captcha1">
                                        <p id="wait1" style="display: block;">正在加载验证码......</p>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group has-feedback">
                                <p id="notice1" style="display: none;">请先完成验证</p>
                                <button type="button" class="btn btn-primary btn-block" id="submit1">登录</button>
                                <p class="help-block pull-right"><a href="#" data-toggle="tooltip" data-placement="bottom" title="忘记密码，请点击注册，输入之前注册的邮箱重新注册">忘记密码？</a></p>
                            </div>
                        </form>
                    </div>
                    <div id="registerpage" style="display: none;">
                        <h3 class="text-center" style="color: #000;">注册</h3>
                        <p class="text-left">将发送一封验证邮件到你的邮箱，此邮箱将作为 登录用户名，请谨慎填写</p>
                        <form class="" id="registerForm" role="form">
                            <div class="form-group">
                                <div class="input-group ">
                                <#--<span class="input-group-addon"><i class="fa fa-envelope-o"></i></span>-->
                                    <input type="email" class="form-control" id="sendMail" placeholder="请输入邮箱" name="email" required>
                                    <span class="input-group-btn">
                                        <button class="btn btn-success" type="button" id="submit3">发送验证码</button>
                                    </span>
                                </div>
                            </div>
                            <div class="form-group validate-after" style="display: none;">
                                <input type="text" class="form-control" placeholder="请输入验证码" name="code"
                                       required>
                            </div>
                            <div class="form-group validate-after" style="display: none;">
                                <input type="password" class="form-control" placeholder="请输入密码" name="password"
                                       required>
                            </div>
                            <div class="form-group">
                                <div>
                                    <div id="captcha2">
                                        <p id="wait2" style="display: block;">正在加载验证码......</p>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group has-feedback">
                                <p id="notice2" style="display: none;">请先完成验证</p>
                                <button type="button" class="btn btn-primary btn-block" id="submit2">注册</button>
                                <p class="help-block"> 1.如果您没有收到邮件，请检查垃圾邮箱或广告邮箱目录。</p>
                                <p class="help-block"> 2.如果您一直没有收到邮件，请您加入 QQ 群咨询，<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=d982a3d8e467b7a1ea4c3f16c6f12df2a804a684ba4ad2acfb02aed30fc62614"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="Java 技术交流群" title="Java 技术交流群"></a>。</p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <section class="login-copyright"><a href="http://zzitbar.com">zzitbar.com</a> &copy; ${.now?string("yyyy")}
        </section>
    </header>
</div>
<script src="${ctx}/static/js/jquery-2.2.3.min.js"></script>
<script src="${ctx}/static/js/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/static/js/gt.js"></script>
<script src="${ctx}/static/js/layer-v3.0.3/layer/layer.js"></script>
<script src="${ctx}/static/js/all.js"></script>

<script>
    $(function () {
        loginCaptcha(handlerLogin);
        loginCaptcha(handlerRegister);
        loginCaptcha(handlerSendMail, "bind");
        $(".btn-toggle").on("click", function (e) {
            var $this = e.target;
            document.title=$($this).html();
            $($this).addClass("active");
            $($this).siblings().removeClass("active");
            var target = $($this).attr("data-target");
            $(target).show();
            $(target).siblings().hide();
        });
        $("[data-toggle='tooltip']").tooltip();
    });
    var handlerLogin = function (captchaObj) {
        $("#submit1").click(function (e) {
            var result = captchaObj.getValidate();
            if (!result) {
                $("#notice1").show();
                setTimeout(function () {
                    $("#notice1").hide();
                }, 2000);
                e.preventDefault();
            } else {
                var loading = BaseJs.loading();
                $.ajax({
                    url: '${ctx}/login',
                    type: 'POST',
                    dataType: 'json',
                    data: $("#loginForm").serialize(),
                    complete: function() {
                        BaseJs.endLoading(loading);
                    },
                    success: function (data) {
                        captchaObj.reset();
                        if (data.status === 'success') {
                            BaseJs.showSuccess("登录成功");
                            window.location.href = "${ctx}/admin/index";
                        } else if (data.status === 'failed') {
                            BaseJs.showError(data.errorMsg);
                        }
                    }
                })
            }
        });
        // 将验证码加到id为captcha的元素里，同时会有三个input的值用于表单提交
        captchaObj.appendTo("#captcha1");
        captchaObj.onReady(function () {
            $("#wait1").hide();
        });
        // 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
    };
    var handlerRegister = function (captchaObj) {
        $("#submit2").click(function (e) {
            var result = captchaObj.getValidate();
            if (!result) {
                $("#notice2").show();
                setTimeout(function () {
                    $("#notice2").hide();
                }, 2000);
                e.preventDefault();
            } else {
                var loading = BaseJs.loading();
                $.ajax({
                    url: '${ctx}/register',
                    type: 'POST',
                    dataType: 'json',
                    data: $("#registerForm").serialize(),
                    complete: function() {
                        BaseJs.endLoading(loading);
                    },
                    success: function (data) {
                        BaseJs.endLoading(loading);
                        captchaObj.reset();
                        if (data.status == 'success') {
                            BaseJs.showSuccess("注册成功");
                        } else if (data.status == 'failed') {
                            BaseJs.showError(data.errorMsg);
                        }
                    }
                })
            }
        });
        // 将验证码加到id为captcha的元素里，同时会有三个input的值用于表单提交
        captchaObj.appendTo("#captcha2");
        captchaObj.onReady(function () {
            $("#wait2").hide();
        });
        // 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
    };
    var handlerSendMail = function (captchaObj) {
        captchaObj.onReady(function () {
            $("#wait").hide();
        }).onSuccess(function () {
            var result = captchaObj.getValidate();
            if (!result) {
                BaseJs.showError("请完成验证");
                return false;
            }
            var loading = BaseJs.loading();
            $.ajax({
                url: '${ctx}/sendMail',
                type: 'POST',
                dataType: 'json',
                data: {
                    email: $("#sendMail").val(),
                    geetest_challenge: result.geetest_challenge,
                    geetest_validate: result.geetest_validate,
                    geetest_seccode: result.geetest_seccode
                },
                complete: function() {
                    BaseJs.endLoading(loading);
                },
                success: function (data) {
                    if (data.status === 'success') {
                        $(".validate-after").show();
                        BaseJs.showSuccess("邮件发送成功");
                    } else if (data.status === 'failed') {
                        BaseJs.showError(data.errorMsg);
                        captchaObj.reset();
                    }
                }
            });
        });
        $('#submit3').click(function () {
            if (!$("#sendMail").val()) {
                BaseJs.showError("请填写邮箱");
                return false;
            }
            captchaObj.verify();
        })
    };
    var loginCaptcha = function (handler, product) {
        $.ajax({
            url: "${ctx}/geetest/startCaptcha?t=" + (new Date()).getTime(), // 加随机数防止缓存
            type: "get",
            dataType: "json",
            success: function (data) {
                // 调用 initGeetest 初始化参数
                // 参数1：配置参数
                // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它调用相应的接口
                initGeetest({
                    gt: data.gt,
                    challenge: data.challenge,
                    new_captcha: data.new_captcha, // 用于宕机时表示是新验证码的宕机
                    offline: !data.success, // 表示用户后台检测极验服务器是否宕机，一般不需要关注
                    product: product ? product : "float", // 产品形式，包括：float，popup, bind
                    width: "100%"
                    // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
                }, handler);
            }
        });
    }
</script>
</body>
</html>