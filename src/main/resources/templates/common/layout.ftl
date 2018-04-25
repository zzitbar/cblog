<#macro mainlayout pageTitle="" pageCss="" pageJavascript="" pageBreadCrumbs="" currentMenu="">
<#assign ctx=request.contextPath>
<!DOCTYPE html>
<html lang="en">
<#include "./head.ftl">
<body class="">
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <#include "./navbar.ftl">
            </div>
        </div>
        <div class="row clearfix">
            <div class="col-sm-8 col-md-8 col-lg-8">
                <section class="content">
                        <#nested>
                </section>
            </div>
            <div class="col-sm-4 col-md-4 col-lg-4">
                    <#include "./right.ftl">
            </div>
        </div>
    </div>
    <#include "./footer.ftl">
    <#include "./static_js.ftl">
    <#--<script src="${request.contextPath}/static/js/jquery-2.2.3.min.js"></script>-->
    <#--<script src="${request.contextPath}/static/js/bootstrap/js/bootstrap.min.js"></script>-->
    <!--[if lt IE 9]>
    <!--<script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>-->
    <#--<script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>-->
    <#--<![endif]&ndash;&gt;-->
    <#--<script id="-mob-share" src="http://f1.webshare.mob.com/code/mob-share.js?appkey=2557fb993deeb"></script>-->
    <script>
        BaseJs.baseUrl="${request.contextPath}"
        var currentMenu = '${currentMenu!}';
        if (currentMenu && ''!=currentMenu) {
            $("li.active").removeClass("active");
            $("#"+currentMenu).addClass("active");
        }
    </script>
        ${pageJavascript}
</body>
</html>
</#macro>

