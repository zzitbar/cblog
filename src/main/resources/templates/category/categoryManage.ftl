<#include "../common/admin_layout.ftl">
<#assign pageTitle>
    分类管理
</#assign>
<#assign pageBreadCrumbs>
    分类管理
</#assign>
<#assign pageJavascript>
<script>
    $(function () {
        $("#categoryTable").bootstrapTable({pagination: false});
    })
    function search() {
        $('#categoryTable').bootstrapTable("refreshOptions", {
            queryParams: function (params) {
                // var query = $.extend(true, params, $("#vipQueryForm").serializeJSON());
                return query;
            }
        });
    }
    function formatAction(value, row, index) {
        return '<button type="button" class="btn btn-success btn-sm" onclick="edit(\''+row.id+'\')"><i class="fa fa-pencil"></i> 修改</button>';
    }
    function edit(id) {
        BaseJs.openDialog("${request.contextPath}/admin/category/edit"+(id?"?id="+id:""), "修改分类", ["60%", "50%"])
    }
</script>
</#assign>

<@mainlayout pageTitle=pageTitle pageJavascript=pageJavascript pageBreadCrumbs=pageBreadCrumbs currentMenu="category">
<button type="button" class="btn btn-primary pull-right" onclick="edit()" style="margin-bottom: 10px;"><i class="fa fa-plus"></i> 新增</button>
<table style="width:100%;" data-toggle="table" class="bootstrapTable" id="categoryTable"
       data-content-type="application/x-www-form-urlencoded; charset=UTF-8"
       data-url="${ctx}/admin/category/page" data-method="post"
       data-pagination="true" data-side-pagination="server">
    <thead>
    <tr>
        <th data-field="id">ID</th>
        <th data-field="categoryName" >分类名</th>
        <th data-field="articleCount" >文章数</th>
        <th data-field="createTime" >创建时间</th>
        <th data-field="action" data-formatter="formatAction">操作</th>
    </tr>
    </thead>
</table>
</@mainlayout>