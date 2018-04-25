
[TOC]

启动banner
======================
####修改启动banner
1. 在src/main/resources下新建一个banner.txt文档
2. 通过http://patorjk.com/software/taag网站生成需要的字符，
将字符拷贝到步骤1所创建的txt文档中，比如我这里为Hello CBlog!
####关闭Banner

```
public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(CblogApplication.class);
        //修改Banner的模式为OFF
        builder.bannerMode(Banner.Mode.OFF).run(args);
}
```
