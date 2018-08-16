<#assign ctx=request.contextPath>

<script src="${ctx}/static/js/clipboardjs/clipboard.js"></script>
<script>
    $(function () {
        var clipboard = new ClipboardJS('#copyBtn');
        clipboard.on('success', function(e) {
            console.info('Action:', e.action);
            console.info('Text:', e.text);
            console.info('Trigger:', e.trigger);
            BaseJs.showSuccess("复制成功");
        });
        clipboard.on('error', function(e) {
            BaseJs.showError("复制失败，请使用 ctrl + c ")
        });
    })
    function resetSecret() {
        BaseJs.confirm("重置后原 app secret 将失效，确定要重置吗？", function () {
            $.post("${ctx}/openapi/resetAppSecret", function (data) {
                if (data.status==BaseJs.SUCCESS) {
                    BaseJs.showSuccess("重置成功");
                    $("#appSecret").html(data.data);
                } else {
                    BaseJs.showError(data.errorMsg);
                }
            }, "json");
        })
    }
</script>
<div class="panel panel-default">
    <div class="panel-heading">
        <h4 class="panel-title">
            Bing Open API
        </h4>
    </div>
    <div class="panel-body">
        <table class="table table-bordered table-hover" style="margin-bottom: 0; font-size: 16px;">
            <tr>
                <td class="text-right" style="width: 120px;">邮箱</td>
                <td><span class="text-success">${user.email!}</span></td>
            </tr>
            <tr>
                <td class="text-right" style="width: 120px;">注册时间</td>
                <td><span class="text-success">${user.createTime!}</span></td>
            </tr>
            <tr>
                <td class="text-right">app secret</td>
                <td>
                    <span class="text-success" id="appSecret">${user.appSecret!}</span>
                    <button type="button" data-clipboard-target="#appSecret"
                            class="btn btn-default btn-xs" id="copyBtn">复制</button>
                    <button type="button" class="btn btn-primary btn-xs" onclick="resetSecret()">重置</button>
                </td>
            </tr>
            <tr>
                <td class="text-right">请求限额</td>
                <td>
                    <span class="label label-success">${user.minuteLimit!} 次/分钟</span> <span class="label label-danger">${user.dayLimit!} 次/天</span>
                </td>
            </tr>
        </table>
    </div>
</div>
<div class="panel panel-default">
    <div class="panel-heading">
        <h4 class="panel-title">
            Bing Open API 调用统计
        </h4>
    </div>
    <div class="panel-body">
    </div>
</div>