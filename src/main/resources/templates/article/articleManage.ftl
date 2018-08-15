<#assign ctx=request.contextPath>
<script>
    $(function () {
        $(".bootstrapTable").bootstrapTable({pagination: true});
    })
    function formatAction(value, row, index) {
        return '<a href="${request.contextPath}/admin/article/edit?id='+row.id+'" class="btn btn-success btn-sm"><i class="fa fa-pencil"></i> 修改</a>';
    }
</script>
<table style="width:100%;" data-toggle="table" class="bootstrapTable"
<#--data-content-type="application/x-www-form-urlencoded; charset=UTF-8"-->
       data-url="${ctx}/admin/article/page" data-method="post"
       data-pagination="true" data-side-pagination="server">
    <thead>
    <tr>
        <th data-field="id">ID</th>
        <th data-field="articleTitle" >标题</th>
        <th data-field="articleCategoryName" >分类</th>
        <th data-field="articleTags" >标签</th>
        <th data-field="articleCreateDate" >创建时间</th>
        <th data-field="action" data-formatter="formatAction">操作</th>
    </tr>
    </thead>
</table>