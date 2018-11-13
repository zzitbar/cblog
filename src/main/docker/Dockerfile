# Dockerfile
# 基于的镜像id
FROM zzitbar/javaweb:0.1

# VOLUME可以将本地文件夹或者其他container的文件夹挂载到container中
VOLUME /opt/tmp

RUN rm -rf /mydata/apache-tomcat-9.0.12/webapps/
# 将文件<src>拷贝到container的文件系统对应的路径<dest>
ADD cblog.war /mydata/apache-tomcat-9.0.12/webapps/ROOT.war

# ENTRYPOINT：container启动时执行的命令，但是一个Dockerfile中只能有一条ENTRYPOINT命令，如果多条，则只执行最后一条
# -Djava.security.egd=file:/dev/./urandom 可解决tomcat可能启动慢的问题
# 具体可查看：https://www.cnblogs.com/mightyvincent/p/7685310.html
ENTRYPOINT ["/root/run.sh"]
 
# 对外端口
EXPOSE 8080