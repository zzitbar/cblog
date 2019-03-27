<#assign ctx=request.contextPath>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>

    <title>热点榜</title>

    <meta name="HandheldFriendly" content="True"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <#--<link rel="stylesheet" type="text/css"-->
    <#--href="https://fonts.cat.net/css?family=Merriweather:300,700,700italic,300italic|Open+Sans:700,400"/>-->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/font-awesome-4.5.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">

    <meta name="description" content="能简单化绝不复杂，zzitbar，java，java学习"/>
    <link rel="shortcut icon" href="/favicon.png" type="image/png"/>
    <link rel="canonical" href="http://zzitbar.com/"/>
    <meta name="referrer" content="no-referrer-when-downgrade"/>
    <#--<link rel="next" href="http://zzitbar.com/page/2/"/>-->
    <#--<link rel="stylesheet" href="${request.contextPath}/static/js/editormd/css/editormd.min.css">-->
    <style>
        body {
            overflow-x: hidden;
        }

        .hotnews-rank {
            height: 24px;
            width: 24px;
            border-radius: 5px;
            text-align: center;
            line-height: 24px;
            font-size: 12px;
            background: #000000;
            color: #FFFFFF;
            padding: 0 6px;
            white-space: nowrap;
            margin: 0 20px;
        }

        .hotnews-rank-info {
            background: #909399;
        }

        .hotnews-rank-primary {
            background: #409EFF;
        }

        .hotnews-rank-success {
            background: #67C23A;
        }

        .hotnews-rank-warning {
            background: #E6A23C;
        }

        .hotnews-item {
            padding: 10px 4px;
            border-bottom: 1px #909399;
            transition-duration: .3s;
        }

        .hotnews-item:hover {
            background: #F2F6FC;
        }

        .hotnews-link {
            font-size: 16px !important;
            text-decoration: none !important;
            transition-duration: .3s;
        }

        .hotnews-link:hover {
            color: #409EFF;
        }

        .el-card__body {
            padding: 0 !important;
        }

        .hotnews-category {
            font-size: 16px !important;
        }

        .hotnews-category:hover {
            cursor: pointer;
        }

        .hotnews-category-active {
            color: #409EFF;
            font-weight: 600;
            border-left: 2px solid #409EFF;
        }

        .el-card {
            border-left-width: 0px !important;
        }

        img {
            height: 15px;
            width: 15px;
        }

        .hotnews-point {
            font-size: 13px !important;
            color: #909399;
            margin-left: 10px;
        }

        .hotnews-item-hidden {
            display: none;
        }

        .box-card {
            margin-bottom: 10px;
        }

        .hotnews-author {
            font-size: 13px !important;
            color: #909399;
            margin-left: 10px;
        }
    </style>
</head>
<body class="paged nav-closed" style="background: #e3e3e3">
<div class="nav">
    <h3 class="nav-title">Menu</h3>
    <a href="#" class="nav-close" onclick="navShow()">
        <span class="hidden">Close</span>
    </a>
    <ul>
        <li class=""><a href="${ctx}/">主页</a></li>
        <li class="nav-"><a href="${ctx}/">归档</a></li>
        <li class="nav-"><a href="${ctx}/link/">友链</a></li>
        <li class="nav-"><a href="${ctx}/about/">关于</a></li>
        <li class="nav-current"><a href="${ctx}/hotnews">热点</a></li>
    </ul>

    <a class="subscribe-button icon-feed" href="${ctx}/rss/">Subscribe</a>
</div>
<span class="nav-cover"></span>
<div class="site-wrapper" style="min-height: auto!important;">
    <header class="main-header " style="background-image: url(${ctx}/bing/today)">
        <nav class="main-nav overlay clearfix">
            <a class="menu-button fa fa-reorder" href="#" onclick="navShow()"><span class="word">Menu</span></a>
        </nav>
        <div class="vertical">
            <div class="main-header-content inner">
                <h1 class="page-title">Simplify</h1>
                <h2 class="page-description" id="hitokoto">能简单化绝不复杂</h2>
            </div>
        </div>
        <a class="scroll-down fa fa-angle-double-down " href="#content"><span class="hidden">Scroll Down</span></a>
    </header>

    <main class="content" role="main">
    </main>
</div>
<div id="app">
    <el-row type="flex" class="row-bg" justify="center" :gutter="20">
        <el-col :span="5">
            <div class="grid-content">
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <span>热点来源</span>
                        <el-button style="float: right; padding: 3px 0" type="text" @click="dialogFormVisible = true">
                            设置
                        </el-button>
                    </div>
                    <div v-for="category in categoryList" :key="category.id"
                         :class="category.visible?(category.id==activeIndex?'hotnews-category-active':''):'hotnews-item-hidden'"
                         class="hotnews-item hotnews-category" @click="changeContent(category.id)">
                        {{category.name }}
                    </div>
                </el-card>
            </div>
        </el-col>
        <el-col :span="12">
            <div class="grid-content">
                <el-card class="box-card" id="hotnewsContentCard">
                    <div slot="header" class="clearfix">
                        <span>{{categoryList[activeIndex].contentTitle}}</span>
                    </div>
                    <a v-for="content in contentList" :key="content.id"
                       :href="content.newsUrl" class="hotnews-link" target="_blank">
                        <div class="hotnews-item">
                            <span class="hotnews-rank"
                                  :class="content.newsOrder==1?'hotnews-rank-primary':(content.newsOrder==2?'hotnews-rank-success':(content.newsOrder==3?'hotnews-rank-warning':'hotnews-rank-info'))">
                            {{content.newsOrder}}</span>
                            <span v-html="content.newsTitle"></span> <span class="hotnews-author"
                                                                           :class="content.newsAuthor!=null?'':'hotnews-item-hidden'"
                                                                           v-html="'公众号: '+content.newsAuthor"></span>
                            <span class="hotnews-point" v-html="content.newsPoint"></span>
                        </div>
                    </a>
                </el-card>
            </div>
        </el-col>
    </el-row>
    <el-dialog title="设置热点来源" :visible.sync="dialogFormVisible">
        <el-form :model="form" label-position="top">
            <el-form-item>
                <el-checkbox-group v-model="form.type">
                    <el-checkbox v-for="category in categoryList" :key="category.id" :label="category.name"
                                 name="type"></el-checkbox>
                </el-checkbox-group>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="changeCategory">确 定</el-button>
        </div>
    </el-dialog>
</div>
<script src="${ctx}/static/js/jquery-2.2.3.min.js"></script>
<script src="${ctx}/static/js/front.js"></script>
<!-- import Vue before Element -->
<script src="https://unpkg.com/vue/dist/vue.js"></script>
<!-- import JavaScript -->
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
<script>
    new Vue({
        el: '#app',
        data: function () {
            return {
                dialogFormVisible: false,
                form: {
                    type: []
                },
                categoryList: [{
                    id: 0,
                    name: "微博",
                    type: "weibo",
                    icon: "",
                    visible: false,
                    contentTitle: "微博热搜榜"
                }, {
                    id: 1,
                    name: "百度",
                    type: "baidu",
                    icon: "",
                    visible: true,
                    contentTitle: "百度实时热点"
                }, {
                    id: 2,
                    name: "知乎",
                    type: "zhihu",
                    icon: "",
                    visible: true,
                    contentTitle: "知乎热榜"
                }, {
                    id: 3,
                    name: "搜狗",
                    type: "sogou",
                    icon: "",
                    visible: true,
                    contentTitle: "搜狗实时热点"
                }],
                contentList: [{
                    newsRank: 1,
                    newsTitle: "",
                    newsUrl: "",
                    id: ""
                }],
                activeIndex: 0
            }
        },
        methods: {
            changeContent: function (index) {
                var _this = this;
                var loading = this.$loading({lock: true, target: document.querySelector('#hotnewsContentCard')});
                _this.activeIndex = index;
                var categroy = _this.categoryList[this.activeIndex];
                $.get("${ctx}/hotnews/list?category=" + categroy.type, function (result) {
                    loading.close();
                    _this.contentList = result;
                }, "json");
            },
            changeCategory: function () {
                this.dialogFormVisible = false;
                var _this = this;
                $.each(_this.categoryList, function (i, val) {
                    var index = $.inArray(val.name, _this.form.type);
                    if (index >= 0) {
                        val.visible = true;
                    } else {
                        val.visible = false;
                    }
                });
                localStorage.setItem("categoryList", _this.form.type);
                var oldIndex = _this.activeIndex;
                _this.updateActiveIndex();
                if (oldIndex != _this.activeIndex) {
                    _this.changeContent(_this.activeIndex);
                }
            },
            updateActiveIndex: function () {
                var _this = this;
                $.each(_this.categoryList, function (i, val) {
                    if (val.visible) {
                        _this.activeIndex = val.id;
                        return false;
                    }
                });
            }
        },
        created: function () {
            var _this = this;
            _this.categoryList = ${categroyList};
            _this.updateActiveIndex();
            var localCache = localStorage.getItem("categoryList");
            if (localCache) {
                _this.form.type = localCache.split(",");
                _this.changeCategory();
            }
            _this.changeContent(_this.activeIndex);
        }
    })
</script>
</body>
</html>