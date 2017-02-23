用到的框架:Spring,SpringMvc,Mybitis以及一个前台框架Extjs.
## 使用
数据表有一个叫webservice.sql的文件。根据自己的数据库密码，改一下resource目录下配置文件`application.yml`的这几个内容

```
spring:
    datasource:
        url: jdbc:mysql://127.0.0.1:3306/webservice
        username: root
        password: admin
```

以Maven工程导入到eclipse，在这个工程目录下执行命令：`mvn install`,然后`cd admin`，运行`mvn spring-boot:run -Drun.profiles=dev`，浏览器输入`localhost:8088/admin`