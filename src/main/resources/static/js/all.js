// var console = window.console || { log: function () {} };
var BaseJs={};
BaseJs.baseUrl = "";
BaseJs.POST = "POST";
BaseJs.SUCCESS="success";

$(".share-open").bind("click", function (e) {

})

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