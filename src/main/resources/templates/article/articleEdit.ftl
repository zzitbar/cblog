<#assign ctx=request.contextPath>

<script>
    $(function () {
        laydate.render({
            elem: '#articleCreateDate' //指定元素
            ,type: 'datetime'
            ,value: "${article.articleCreateDate!.now?string('yyyy-MM-dd HH:mm:ss')}"
        });
        var editor = editormd("editormd", {
            width   : "100%",
            height  : 640,
            syncScrolling : "single",
            path    : "${request.contextPath}/static/js/editormd/js/lib/",
            //这个配置在simple.html中并没有，但是为了能够提交表单，使用这个配置可以让构造出来的HTML代码直接在第二个隐藏的textarea域中，方便post提交表单。
            saveHTMLToTextarea : true,
            /**上传图片相关配置如下*/
            imageUpload : true,
            imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL : "${request.contextPath}/file/upload",//注意你后端的上传图片服务地址
            onload : function() {
                // this.setMarkdown("### onloaded");
                // console.log("onload =>", this, this.id, this.settings);
            }
        });
        /*
        // or
        var editor = editormd({
            id   : "editormd",
            path : "../lib/"
        });
        */
    })
    var tagnames = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('name'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        prefetch: {
            url: '${request.contextPath}/tags/list',
            filter: function(list) {
                return $.map(list, function(tagname) {
                    return { name: tagname }; });
            }
        }
    });
    tagnames.initialize();

    $('#articleTags').tagsinput({
        typeaheadjs: {
            name: 'tagnames',
            displayKey: 'name',
            valueKey: 'name',
            source: tagnames.ttAdapter()
        }
    });
    function saveArticle() {
        BaseJs.submit("articleEditForm", "${request.contextPath}/admin/article/edit", function (result) {
            BaseJs.showSuccess("保存成功");
            window.location.href = "${request.contextPath}/admin/article/manager"
        })
    }
</script>

<form id="articleEditForm">
    <input type="hidden" name="id" value="${article.id!}">
    <div class="form-group">
        <label for="articleTitle">标题</label>
        <input type="text" name="articleTitle" required value="${article.articleTitle!}" id="articleTitle"
               placeholder="请输入标题" autocomplete="off" class="form-control">
    </div>
    <div class="form-group">
        <label for="articleAbstract">摘要</label>
        <textarea name="articleAbstract" id="articleAbstract"
                  placeholder="请输入摘要" class="form-control">${article.articleAbstract!}</textarea>
    </div>
    <div class="form-group">
        <label for="articleContent">内容</label>
        <div class="editormd" id="editormd">
            <textarea class="editormd-markdown-textarea" name="test-editormd-markdown-doc">${article.articleContent!}</textarea>
            <!-- 第二个隐藏文本域，用来构造生成的HTML代码，方便表单POST提交，这里的name可以任意取，后台接受时以这个name键为准 -->
            <textarea name="articleContent" id="articleContent" placeholder="请输入内容" rows="10" id="articleContent"
                      class="editormd-html-textarea" required>${article.articleContent!}</textarea>
        </div>
    </div>
    <div class="form-group">
        <label for="articleCategoryId">分类</label>
        <select name="articleCategoryId" id="articleCategoryId" class="form-control" value="${article.articleCategoryId!}" required>
                <#if articleCategorys?? && articleCategorys?size gt 0>
                <#list articleCategorys as category>
                <option value="${category.id!}" <#if article.articleCategoryId?? && category.id==article.articleCategoryId>selected</#if>>${category.categoryName}</option>
                </#list>
                </#if>
        </select>
    </div>
    <div class="form-group">
        <label for="articleTags">标签</label>
        <input type="text" class="form-control" name="articleTags" id="articleTags"
               value="${article.articleTags!}" data-role="tagsinput" required/>
    </div>
    <div class="form-group">
        <label for="articleCreateDate">创建时间</label>
        <input type="text" class="form-control" name="articleCreateDate" id="articleCreateDate"
               value="${article.articleCreateDate!}" />
    </div>
    <button type="button" class="btn btn-success" onclick="saveArticle()">保存</button>
</form>
