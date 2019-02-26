# Aquaman Admin（海王管理系统）

#### 1. mybatis自动生成工具

- 修改generatorConfig.xml文件
> 注意：46行，可新多个同时生成多个表

    tableName：需要生成的表名
    domainObjectName：生成实体对象名称
    
- 控制台输入如下命令

    java -jar mybatis-generator-core-1.3.7.jar -configfile generatorConfig.xml -overwrite