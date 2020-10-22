# soc-spring-boot-example
云海 spring boot 模板示例项目，依赖：https://github.com/treeyh/soc-common
项目结构：
* soc-spring-boot-api: api定义模块，输入输出对象定义
* soc-spring-boot-common: 基础模块，配置参数，常量等定义
* soc-spring-boot-dao：持久化模块
* soc-spring-boot-manager：管理模块，业务对象定义，基础逻辑封装以及调用第三方业务封装
* soc-spring-boot-service：业务逻辑模块；
* soc-spring-boot-web：接口实现及过滤器模块
* soc-spring-boot-worker：计划任务模块；

依赖`soc-common` 项目，先拉取该项目代码执行`mvn install`，打依赖包.
