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
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h3>${image.imageTitle!}</h3>
            <img src="${image.imageUrl!}" alt="${image.imageAlt!}" class="img-rounded img-responsive">
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <p class="text-muted image-info"><i class="glyphicon glyphicon-calendar"></i> ${image.imageDateEnd!}</p>
            <p class="text-muted image-info"><i class="glyphicon glyphicon-map-marker"></i> ${image.imagePlace!}</p>
            <p class="text-muted image-info"><i class="glyphicon glyphicon-copyright-mark"></i> ${image.imageProvider!}</p>
        </div>
    </div>
</div>
<script>
</script>
</body>
</html>