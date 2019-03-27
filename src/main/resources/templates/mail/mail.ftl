<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <head>
        <title>激活邮箱验证</title>
        <style>
            body {
                font: 300 14px/18px 'Lucida Grande', Lucida Sans, Lucida Sans Unicode, sans-serif, Arial, Helvetica, Verdana, sans-serif;
                color: #333;
            }
            p {
                font-size: 14px;
            }
            .code{
                font-weight: 600;
                font-size: 16px;
                color: #f95b2b;
            }
            .copyright{
                margin: 10px 0;
            }
            .tips {
                margin: 10px 0;
                font-size: 12px;
                color: #000;
            }
        </style>
    </head>
<body>
<div style="width: 65%; margin: 0 auto;">
    <h3> Bing 壁纸 API 激活邮箱验证 </h3>
    <p>${mailAddress}，您好，</p>

    <p>您正在 bing.zzitbar.com 请求激活邮箱。本次操作验证码是：<span class="code">${code}</span></p>
    <p>出于安全原因，该验证码将于1小时后失效。请勿将验证码透露给他人。如果您没有进行该操作，忽略该邮件。</p>

    <p class="tips">本邮件由系统自动发送，请勿直接回复！</p>
    <hr>
    <p class="copyright"><a href="http://bing.zzitbar.com">bing.zzitbar.com</a></p>

    <p>扫一扫加关注</p>
    <img src="http://cdn.zzitbar.com//wechat/qrcode/zzitbar.jpg" width="100px" height="100px;">
</div>

</body>
</html>