<#assign ctx=request.contextPath>

<script src="${ctx}/static/js/clipboardjs/clipboard.js"></script>
<script src="${ctx}/static/js/highcharts/highcharts.js"></script>
<script src="${ctx}/static/js/highcharts/no-data-to-display.js"></script>
<script src="${ctx}/static/js/highcharts/sand-signika.js"></script>

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
        $(".btn-report:first-child").click();
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
    function showChart(obj) {
        var duration = $(obj).attr("data-duration");
        $(obj).addClass("active");
        $(obj).siblings().removeClass("active");
        $.post("${ctx}/openapi/report", {duration:duration}, function (result) {
//            BaseJs.endLoading(index);
            var options = BaseJs.deepCloneObj(BaseJs.chartOptions);
            options.chart.renderTo = "chart";
            options.chart.type = result.type;
            options.title.text = result.chartTitle;
            options.yAxis.title.text = result.yTitle;
            options.yAxis.plotBands = [];
            options.xAxis.categories = result.xAxis;
            options.series = result.chartDataDtos;
            options.exporting.filename = result.chartTitle;
            var chart = new Highcharts.Chart(options);
        }, "json");
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
        <div class="btn-group">
            <button type="button" class="btn btn-default btn-report" onclick="showChart(this)" data-duration="1">今日</button>
            <button type="button" class="btn btn-default btn-report" onclick="showChart(this)" data-duration="2">昨日</button>
            <button type="button" class="btn btn-default btn-report" onclick="showChart(this)" data-duration="3">7天</button>
            <button type="button" class="btn btn-default btn-report" onclick="showChart(this)" data-duration="4">30天</button>
        </div>
        <div id="chart">

        </div>
    </div>
</div>