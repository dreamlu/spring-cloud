# 基于Spring Boot的微服务应用

本项目内容基于Spring Boot、Spring Cloud开发  
创建数据库spring-cloud(表由jpa自动创建)  
表中数据第一次创建均为空,需要自行添加    

## 技术栈
1.spring cloud/boot  
2.mysql  
3.redis  
4.jpa  
5.mybatis  

## 模块  
1.eureka服务发现  
2.api网关  
3.auth登陆/权限  
4.common 公共服务(阿里短信/文件上传/二维码/微信小程序授权/支付/提现等)  
5.shop服务通用增删改查(jpa/mybatis)  
6.配置中心(弃~全局配置替代)  
7.commons 工具类(docker)打包时,clean install或借助插件安装到本地仓库  
8.缓存  
9.feign模块间请求  
10.rabbitmq消息队列[待完善]  

#### 扩展  
- docker 支持  
1.命令或ide插件(clean package docker:build), 打包成镜像  
2.镜像推送私有仓库(有直接推送方式, 不建议, 本地docker测试好再上传)  
3.批量操作脚本执行(docker目录)  

#### 本地测试结果(http://localhost:8761)  
![服务发现结果](common-service/static/file/run.png)  

#### docker测试  
1.改变全局配置模式(根目录pom.xml中)  
```
<!-- 开发模式, dev/prod -->
<devMode>prod</devMode>
```
2.每个服务单独构建镜像(双击配置好的构建命令)  
![构建步骤1](common-service/static/file/docker1.png)  
![构建步骤2](common-service/static/file/docker2.png)  
3.构建结果  
![镜像构建结果](common-service/static/file/docker3.png)  
4.docker-compose管理docker(docker目录下:命令docker-compose -f docker-compose.yaml up -d)  
5.运行结果查看同本地测试结果(http://localhost:8761)  

- 消息队列demo  
1.auth-service服务-->shop-service服务  
2.suth-servicez中login生产队列消息-->shop-service中消费消息  
3.队列消息的可靠性(待了解)  
4.运行结果 
![消息队列运行结果](common-service/static/file/queue.png)  

- 缓存  
1.此处为通用缓存  
2.开启方式  
```go
/**
 * 开启redis通用缓存
 * 此处为通用缓存(业务查询多可用)
 * 具体业务逻辑可根据源码改造
 * 源码参考 {@link com.deercoder.commons.util.sql.CrudUtil}
 * 缓存通用失效时间{@link com.deercoder.commons.lib.Constants}
 *
 * @author dreamlu
 * @date 2019/04/17
 */
@Repository
public class RedisConfig {

	@Autowired
	public void setRedis(RedisConnectionFactory redisConnectionFactory) {
		CrudUtil.setCacheManager(redisConnectionFactory);
	}
}
```  

- 注:  
1.如遇识别maven项目失败, 打开project structure-->Modules-->添加-->import module-->选择maven-->下一步-->完成    
2.如遇全局变量(开发模式)注入编译失败情况,清确定为maven项目,然后build-->rebuild 对应模块,或根pom.xml-->maven重新导入依赖  
~~3.当配置feign进行模块间请求时,模块打包(包含docker镜像打包),在父项目下有的子项目在首次运行clean和install前应该先运行父项目的clean和install~~  

- ps  
1.微服务各个模块间尽量不要产生依赖,如不可避免依赖,请抽取出一个公共模块  

  