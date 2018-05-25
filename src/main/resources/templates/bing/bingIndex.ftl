<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Bing 每日高清壁纸</title>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
    <meta content="text/html;charset=UTF-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1"/>
    <link href="${request.contextPath}/static/js/bootstrap/css/bootstrap.css" rel="stylesheet"/>
    <link href="${request.contextPath}/static/css/base.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${request.contextPath}/static/js/progressively/progressively.css">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <#list pageBean.rows as picture >
            <div class="col-xs-12 col-sm-6 col-md-4 col-no-padding">
                <div class="progressive__bg progressive--not-loaded bing-picture-bg"
                     data-progressive="${picture.image!}"
                     data-progressive-sm="${picture.image!}"
                     style="background-image: url('${picture.image!}');">
                </div>
                <div class="bing-description">
                    <h3>${picture.title!} (© ${picture.provider!}y)</h3>
                    <p class="calendar"><i class="icon icon-calendar"></i><em class="t">${picture.date!}</em></p>
                    <p class="location"><i class="icon icon-location"></i><em class="t">${picture.continent!} ${picture.country!} ${picture.city!}</em></p>
                    <p class="view"><i class="icon icon-eye"> </i><em class="t">136</em></p>
                </div>
                <div class="bing-options">
                    <a class="ctrl share" href="#"
                       target="_blank" rel="nofollow" title="分享到微博">
                        <i class="icon icon-share"></i>
                        <em class="t">分享</em>
                    </a>
                    <span class="ctrl heart" photo="1731" likes="4">
                        <i class="icon icon-heart"></i>
                        <em class="t">4</em>
                    </span>
                    <a class="ctrl download" href="/photo/ClaretCup_EN-AU11621919077?force=download"
                       target="_blank" rel="nofollow">
                        <i class="icon icon-download"></i>
                        <em class="t">151</em></a>
                </div>
            </div>
        </#list>
    </div>
</div>
<script src="${request.contextPath}/static/js/jquery-2.2.3.min.js"></script>
<script src="${request.contextPath}/static/js/bootstrap/js/bootstrap.min.js"></script>
<script src="${request.contextPath}/static/js/progressively/progressively.js"></script>
<script>
    $(function () {
        console.log("progressively: ", progressively);
        progressively.init({
            delay: 20,
            throttle: 300,
            smBreakpoint: 600,
            onLoad: function(elem) {
                console.log(elem);
            },
            onLoadComplete: function() {
                console.log('All images have finished loading!');
            }
        });
    })
</script>
</body>
</html>