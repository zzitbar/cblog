<script>
    function saveCategory() {
        BaseJs.submit("categoryEditForm", "${request.contextPath}/admin/category/edit", function (result) {
            layer.closeAll();
            BaseJs.showSuccess("保存成功");
            search();
        })
    }
</script>
<div class="container-fluid" style="margin: 10px;">
    <form id="categoryEditForm">
        <input type="hidden" name="id" value="${category.id!}">
        <div class="form-group">
            <label for="categoryName">分类名</label>
            <input type="text" name="categoryName" required value="${category.categoryName!}" id="categoryName"
                   placeholder="请输入分类名" autocomplete="off" class="form-control">
        </div>
        <button type="button" class="btn btn-success" onclick="saveCategory()">保存</button>
        <button type="button" class="btn btn-info" onclick="layer.closeAll()">取消</button>
    </form>
</div>