# 前言
    多数据源示例

# 项目简介
## 组织结构
 ```
 multipkle-data-source-parent
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
- [x] 静态数据源
   - [x] 事物管理
   - [x] 多事物管理
- [x] 动态数据源
  - [x] 事物管理
  - [ ] 多事物管理
 - [ ] 分布式事物
 
 - 通用模块
 - [x] 字段校验
 - [x] 日志输出
 - [x] 生成数据库操作代码
 - [x] 生成接口文档

# 运行指南
## 启动服务
```
 运行 *Application.java 文件，(*--对应目标文件)

```

## swagger文档地址
    http://localhost:port/doc.html

## 生成数据操作代码
- 查找对应 `generatorCOnfig.xml`配置文件，修改`jdbcConnection`和`table`配置
- 查找对应的 `MybatisRunner`类， 运行`main`函数
