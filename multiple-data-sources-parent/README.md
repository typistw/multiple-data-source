# 前言
在开发过程中，避免不了需要同时操作多个数据库的情况，通常的应用场景如下 ：

- 数据库高性能场景：主从，包括一主一从，一主多从等，在主库进行增删改操作，在从库进行读操作。
- 数据库高可用场景：主备，包括一往一备，多主多备等，在数据库无法访问时可以切换。
- 同构或异构数据的业务处理：需要处理的数据存储在不同的数据库中，包括同构（如都是 MySQL ），异构（如一个 MySQL ，另外是 PG 或者 Oracle ）。

使用 Spring Boot 该如何处理多个数据库的读写，一般有以下几种策略：

- 多套数据源：即针对一个数据库建立一套数据处理逻辑，每套数据库都包括数据源配置、会话工厂（ sessionFactory ）、连接、SQL 操作、实体。各套数据库相互独立。
- 动态数据源：确定数量的多个数据源共用一个会话工厂，根据条件动态选取数据源进行连接、SQL 操作。
- 参数化变更数据源：根据参数添加数据源，并进行数据源切换，数据源数量不确定。通常用于对多个数据库的管理工作。

# 一、项目简介
## 组织结构
 ```
 multiple-data-source-parent
 ├── multiple-api -- 接口入口
 ├── multiple-common -- 工具类及普通代码
 ├── multiple-dao -- MybatisRunner生成的数据库操作代码
 ├── multiple-service -- 服务层
 ┕── multiple-vo -- vo对象
 ```
 
 ## 技术选型
 |技术|说明|
 |----|----|
 |SpringBoot|容器+MVC框架|
 |Mybatis|ORM框架|
 |Swagger-UI|文档生产工具|
 | Hibernator-Validator|验证框架|
 | Druid|数据库连接池|
 | Lombok|简化对象封装工具|
 
 ## 任务清单
- 多数据源  
- [x] 静态数据源(多套数据源)
   - [x] 事物管理
   - [x] 多事物管理
- [x] 动态数据源
  - [x] 事物管理
  - [ ] 多事物管理(分布式方案解决)
 - [ ] 分布式事物
 
 - 通用模块
 - [x] 字段校验
 - [x] 日志输出
 - [x] 生成数据库操作代码
 - [x] 生成接口文档

# 二、运行指南
## 启动服务
```
 运行 *Application.java 文件，(*--对应目标文件)

```

## swagger文档地址
    http://localhost:port/doc.html

## 生成数据库操作代码
- 查找对应 `generatorCOnfig.xml`配置文件，修改`jdbcConnection`和`table`配置
- 查找对应的 `MybatisRunner`类， 运行`main`函数

# 三、 参考资料
[springboot多数据源](https://www.cnblogs.com/xichji/p/12208141.html)  
[分布式事物](https://www.cnblogs.com/cxyyh/p/10743350.html)  
