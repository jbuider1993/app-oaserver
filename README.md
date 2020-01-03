# app-oaserver

#### 项目介绍

智能办公OA系统[SpringCloud-快速开发平台]，适用于医院，学校，中小型企业等机构的管理。Activiti5.22+动态表单实现零java代码即可做到复杂业务的流程实施，同时包含文件在线操作、日志、考勤、CRM、项目、拖拽式生成问卷、日程、笔记、计划、行政等多种复杂业务功能。同时，可进行授权二开。</br>

> QQ群号：(群一：[696070023](http://shang.qq.com/wpa/qunwpa?idkey=e9aace2bf3e05f37ed5f0377c3827c6683d970ac0bcc61b601f70dc861053229))(群二：[836039567](https://shang.qq.com/wpa/qunwpa?idkey=7bb6f29b27f772aadca9c7c4e384f7833c64e9c3c947b5e946c7b303d1fe174a))`项目持续更新，欢迎进群讨论`

- 注：开源社区版只限学习，切勿使用此版本商用，内设授权码，默认十天删除所有非基础数据
- 系统新增传统风格界面，layui左菜单右内容风格
- 该项目只作为接口服务端

> 现在本人正在找工作，体验地址关了，需要体验的，请联系作者远程查看

|项目|地址|
|-------|-------|
|主项目地址|https://gitee.com/doc_wei01/skyeye|
|APP端接口微服务地址|https://gitee.com/doc_wei01/app-oaserver|
|APP端地址|https://gitee.com/doc_wei01/oa-app|
|小程序端地址|https://gitee.com/doc_wei01/small-pro|

#### 介绍

- 服务监控中心Spring Boot Admin；地址：http://localhost:8100
- 服务注册中心Spring Eureka；地址：http://localhost:8000/
- 网关zuul
- 熔断监控Hystrix；地址：http://localhost:8005
- API接口文档；地址：http://localhost:8888/doc.html

#### 服务器部署注意事项

1.ActiveMQ链接地址、账号、密码的修改<br />
2.Redis集群的修改<br />
3.MySQL数据库链接地址、账号、密码的修改<br />
4.图片资源路径存储的修改<br />

#### 本地开发环境搭建

- windows搭建nginx负载均衡（[下载](https://download.csdn.net/download/doc_wei/11010749)）
- windows搭建activemq单机版（[下载](https://download.csdn.net/download/doc_wei/11010746)）
- windows搭建redis集群（[下载](https://download.csdn.net/download/doc_wei/11010741)）

#### 技术选型

##### 后端技术:

技术|名称|官网
---|---|---
SpringCloud|核心框架|https://springcloud.cc/
MyBatis|ORM框架|http://www.mybatis.org/mybatis-3/zh/index.html
Druid|数据库连接池|https://github.com/alibaba/druid
Maven|项目构建管理|http://maven.apache.org/
redis|key-value存储系统|https://redis.io/
Activiti|工作流引擎|https://www.activiti.org/
Quartz 2.2.2|定时任务|http://www.quartz-scheduler.org/
ActiveMQ|消息队列|http://activemq.apache.org/replicated-leveldb-store.html
solr|企业级搜索应用服务器|https://lucene.apache.org/solr/

#### 环境搭建

开发环境对应的文档以及安装包地址：链接：https://pan.baidu.com/s/1msVBhDcf_I_VN63YCcS-kA 提取码：w8sr；不要告诉我你没有云盘

#### 效果图

| 效果图 | 效果图 | 效果图 |
| ------ | ------ | ------ |
| ![输入图片说明](https://images.gitee.com/uploads/images/2020/0103/224635_56784b6b_1541735.png "在这里输入图片标题") |![输入图片说明](https://images.gitee.com/uploads/images/2020/0103/232018_527da844_1541735.png "在这里输入图片标题")|![输入图片说明](https://images.gitee.com/uploads/images/2020/0103/232057_82da7f47_1541735.png "在这里输入图片标题")|


#### 项目交流：

QQ群号：[696070023](http://shang.qq.com/wpa/qunwpa?idkey=e9aace2bf3e05f37ed5f0377c3827c6683d970ac0bcc61b601f70dc861053229)

> 需要了解的请加微信或者进群：wzq_598748873，备注：码云-公司（姓名）。

|QQ群|公众号|微信群|
|-------|-------|-------|
|![](https://images.gitee.com/uploads/images/2018/1205/145236_4fce6966_1541735.jpeg "微信图片_20181205145217.jpg")|![](https://images.gitee.com/uploads/images/2018/1207/083137_48330589_1541735.jpeg "qrcode_for_gh_e7f97ff1beda_258.jpg")|![输入图片说明](https://images.gitee.com/uploads/images/2019/1026/125556_ff89219a_1541735.jpeg "123.jpg")|
