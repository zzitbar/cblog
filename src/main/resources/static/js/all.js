// var console = window.console || { log: function () {} };
var BaseJs={};
BaseJs.baseUrl = "";
BaseJs.POST = "POST";
BaseJs.SUCCESS="success";
BaseJs.jwtToken = "";

$.ajaxSetup({
    cache:false,
    ifModified :true ,
    timeout : 60000, //超时时间设置，单位毫秒
    //发送请求前触发
    beforeSend: function (XMLHttpRequest) {
        XMLHttpRequest.setRequestHeader('Authorization', window.localStorage.getItem("token"));
    },
    complete:function(XMLHttpRequest,textStatus){
        if(textStatus=="parsererror"){
            layer.closeAll();
            BaseJs.showError("登陆超时！请重新登陆！");
            var originUrl = window.location.href, redirectUrl = "";
            redirectUrl = "/login";
            if (self != top) {
                window.parent.location.href=redirectUrl;
            } else {
                window.location.href=redirectUrl;
            }
        } else if(textStatus=="error"){
            layer.closeAll();
            BaseJs.showError("请求超时！请稍后再试！");
        }
    }
});

$(".share-open").bind("click", function (e) {

})

BaseJs.chartOptions = {
    chart: {
//            width: 500,
        height: 400,
        renderTo: "",
        type: "line"
    },
    title: {
        text: "",
        x: -20
        // center
    },
    xAxis: {
        title: {
            text: ""
        },
        categories: [],
        labels: {
            rotation: 340
        }
    },
    yAxis: {
        title: {
            text: ""
        },
        min: 0,
        plotLines: [{
            value: 0,
            width: 1,
            color: "#808080"
        }],
        plotBands: []
    },
    tooltip: {
        formatter: function () {
            var s = '<b>' + this.x + '</b>';
            $.each(this.points, function () {
                s += '<br/>' + this.series.name + ': ' +
                    this.y;
            });
            return s;
        },
        valueSuffix: ""
    },
    legend: {
        x: 0,
        y: 0,
        floating: false,
        margin: 0,
        itemStyle: {
            color: 'green',
            fontWeight: 'bold'
        }
    },
    plotOptions: {
        line: {
            dataLabels: {
                enabled: true
            },
            enableMouseTracking: true
        }
    },
    credits: {
        enabled: false
    },
    colors: ['#8085e8', '#FFB90F', '#91e8e1', '#f15c80', '#FF00FF', '#8d4653', '#e4d354', '#7cb5ec', '#434348', '#8085e9'],
    series: [],
    lang: {
        noData: "无数据"
    },
    exporting: {
        filename: 'chart-file'
    }
};
//加载中
BaseJs.loading = function () {
    return layer.load(0, {shade: [0.8, '#393D49']});
};
BaseJs.endLoading = function(index) {
    layer.close(index);
};

BaseJs.showSuccess = function(msg) {
    layer.msg(msg);
};
BaseJs.showError = function(errorMsg) {
    layer.alert(errorMsg, {icon: 2});
};
BaseJs.confirm = function (msg, confirmFunc) {
    //询问框
    var confirmMsg=msg?msg:"请确认";
    layer.confirm(confirmMsg, {icon: 3, title:'请确认', btn: ['确定','取消']},
        function(index){
            confirmFunc();
            layer.close(index);
        }, function(index){
            layer.close(index);
        });
};
BaseJs.prompt = function (title, func) {
    //prompt层
    layer.prompt({title: title?title:"请输入", formType: 0}, function(value, index, elem){
        func(value);
        layer.close(index);
    });
};

BaseJs.submit = function (formId, url, success, fail) {
    var $form = $("#"+formId);
    $($form).validate();
    if ($($form).valid()) {
        var loading = BaseJs.loading();
        $.post(url, $($form).serialize(), function (result) {
            BaseJs.endLoading(loading);
            if (result && result.status == BaseJs.SUCCESS) {
                if (success) {
                    success(result);
                } else {
                    BaseJs.showSuccess("操作成功");
                }
            } else {
                if (fail) {
                    fail(result);
                } else {
                    if (!result.errorMsg) {
                        result.errorMsg = "操作失败";
                    }
                    BaseJs.showError(result.errorMsg);
                }
            }
        }, "json");
    }
};
BaseJs.loadTop = function () {
    $.get(BaseJs.baseUrl+"/article/top", function (result) {
        if (result && result.status==BaseJs.SUCCESS) {
            var hotest = "";
            if (result.data.hotest.length>0) {
                $.each(result.data.hotest, function (i, val) {
                    hotest+='<li class="list-group-item"><a href="'+BaseJs.baseUrl+'/article/'+val.id+'">'+val.articleTitle+'</a></li>'
                })
            } else {
                hotest+="暂无";
            }
            $("#hotest").html(hotest);

            var lastest = "";
            if (result.data.lastest.length>0) {
                $.each(result.data.lastest, function (i, val) {
                    lastest+='<li class="list-group-item"><a href="'+BaseJs.baseUrl+'/article/'+val.id+'">'+val.articleTitle+'</a></li>'
                })
            } else {
                lastest+="暂无";
            }
            $("#lastest").html(lastest);

            var tags = "";
            if (result.data.tags.length>0) {
                $.each(result.data.tags, function (i, val) {
                    tags+='<a class="btn btn-default btn-sm btn-tag-top" href="'+BaseJs.baseUrl+'/tag/'+val.id+'">'+val.tagTitle+'('+val.tagReferenceCount+')'+'</a>'
                })
            } else {
                tags+="暂无";
            }
            $("#hotTags").html(tags);
        }
    }, "json");
};

BaseJs.openDialog = function (url, title, width, top, callback) {
    var loading = BaseJs.loading();
    $.get(url, function (result, status) {
        BaseJs.endLoading(loading);
        if (status!="success") {
            BaseJs.showError(status);
        } else {
            //页面层
            var index = layer.open({
                type: 1,
                title: title,
                closeBtn: 1,
                area: width,
                offset: undefined==top?'auto':top,
                shade: [0.6, '#393D49'],
                maxmin: true, //开启最大化最小化按钮
                scrollbar: false,
                //zIndex: 100,
                content: result,
                end: function () {
                    if (callback) {
                        callback(index);
                    }
                }
            });
        }
    });
};

//深复制对象方法
BaseJs.deepCloneObj = function (obj) {
    var newObj = {};
    if (obj instanceof Array) {
        newObj = [];
    }
    for (var key in obj) {
        var val = obj[key];
        //newObj[key] = typeof val == 'object' ? arguments.callee(val) : val; //arguments.callee 在哪一个函数中运行，它就代表哪个函数, 一般用在匿名函数中。
        newObj[key] = typeof val == 'object' ? BaseJs.deepCloneObj(val): val;
    }
    return newObj;
};