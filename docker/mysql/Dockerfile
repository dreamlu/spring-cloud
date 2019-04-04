ARG MYSQL_VERSION

FROM mysql:$MYSQL_VERSION

# 参数问题:https://stackoverflow.com/questions/48831447/docker-compose-build-args-not-passing-to-dockerfile
ARG MYSQL_DATABASE
ARG MYSQL_USER
ARG MYSQL_PASSWORD

# 工作目录不可与挂载目录相同, 否则复制进取的文件, 会被宿主机挂载目录覆盖
WORKDIR /$MYSQL_DATABASE/bak/
COPY ["setup.sh","bak.sh","./"]


RUN chmod 755 setup.sh bak.sh
RUN ./setup.sh "$MYSQL_DATABASE" "$MYSQL_USER" "$MYSQL_PASSWORD"
EXPOSE 3306