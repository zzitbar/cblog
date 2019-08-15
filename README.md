[TOC]

#Cblog
 [![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
 
使用spring boot + freemarker 开发的个人博客项目。

博客地址： [Simplify](https://zzitbar.com/)

# 运行环境
- JDK 8
- Maven
- MySQL

# 主要技术
- Spring boot
- spring-data-jpa
- bootstrap
- freemarker


  
# 安装步骤

* clone project

* 创建数据库，执行 **blog.sql** 交班

* 修改src/main/resources/application.properties文件，需修改datasource、redis、七牛相关配置

* 执行maven命令 **mvn package -DskipTests**

* 命令行启动 **java -jar target/blog.jar**

* 浏览器打开 **http://localhost:8080/** 

## 后台模块

登陆路径 /login

默认账号 admin
默认密码 123456

## License

[Apache License](http://www.apache.org/licenses/LICENSE-2.0).
