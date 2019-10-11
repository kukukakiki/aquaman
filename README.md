# Aquaman Admin（海王管理系统）

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Spring-Boot](https://img.shields.io/badge/spring--boot-2.1.3.RELEASE-brightgreen)](https://spring.io/projects/spring-boot)
[![Spring-Cloud](https://img.shields.io/badge/spring%20cloud-Greenwich.RELEASE-brightgreen)](https://spring.io/projects/spring-cloud)
[![Vue-Element-Admin](https://img.shields.io/badge/vue--element--admin-3.9.0-brightgreen)](https://panjiachen.github.io/vue-element-admin-site/zh/)
[![MyBatis-Plus](https://img.shields.io/badge/mybatis--plus-3.1.0-brightgreen)](https://mp.baomidou.com/)

## 声明

目前并未发布正式版，不能将其部署至生产环境使用，如若使用后果自负！！！

## 开发进度

需要使用[XMind](https://www.xmind.cn/xmind8-pro/)打开查阅

[Aquaman管理系统.xmind](https://github.com/kukukakiki/aquaman/tree/master/aquaman-doc/1.%20requirements)

## 表设计

目前表结构尚在设计阶段不稳定，待发布正式版本后，将完整表结构提交至GITHUB上！！！

[表设计及SQL语句](https://github.com/kukukakiki/aquaman/tree/master/aquaman-doc/2.design/1.db%20design)

## 模块说明
```lua
aquaman
└── aquaman-base -- 海王系统基础包目录
    └── aquaman-base-spring-boot-starter -- spring boot starter方式加载common和logger等工程
    └── aquaman-common -- 公共模块
    └── aquaman-logger -- 日志模块
    └── code-generator -- mybatis-plus代码生成工具模块
└── aquaman-extension -- 扩展包目录
    ├── aquaman-redis -- redis缓存服务模块
    ├── aquaman-kafka -- kafka消息队列服务模块
    ├── aquaman-zookeeper -- zookeeper服务模块
    └── aquaman-oss -- OSS第三方服务模块[阿里云]
├── aquaman-admin-fe -- 前端工程[9528]
├── aquaman-admin-os -- 海王用户管理系统[8081]
├── aquaman-gateway-os -- 海王网管系统[8888]
└── documents  -- 海王管理系统文档 
     ├── 1.requirements -- 需求整理
     └── 2.design -- 海王管理系统设计文档
            └── 1.db design -- 数据库设置文档
     └── 3.software -- 软件
     └── 4.notes -- 学习笔记
            └── elasticsearch
            └── golang
            └── java
            └── kafka
            └── redis
            └── zookeeper
     └── 99.tool -- 工具&jar包等
	 
```

## 示例代码

类或方法有@ExampleCode注解，均为示例代码，使用则可以通过改方式进行扩展

### aquaman-common工程

package在com.aquaman.example下的文件均为示例代码，可供使用者参考和学习使用

## mybatis-plus 改造点记录

##### 1.空字符串参数不查询

所有查询实体类都继承至PageQuery<T>，该类instanceQueryWrapper将会实例化一个QueryWrapper，且通过反射的方式将入参为空字符串的值，重新设置为null，具体代码如下：

```
/**
 * 改造实例化QueryWrapper方法
 * @return
 */
public QueryWrapper<T> instanceQueryWrapper() {
    // 调用Mybatis-plus底层分页方法需要QueryWrapper对象，此处为了方便实例化查询对象
    Class clazz = this.getClass();
    Field[] fields = clazz.getDeclaredFields();
    if(fields != null && fields.length > 0){
        for(Field field : fields) {
            try {
                field.setAccessible(true);
                if(StringUtils.equals("java.lang.String", field.getType().getCanonicalName())) {
                    String value = (String)field.get(this);
                    if(StringUtils.equals("", value)) {
                        field.set(this, null);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    return new QueryWrapper(this);
}
```

##### 2.继承IService增加pageQuery方法

IService默认page方法只能实例化一个空的QueryWrapper，如果有参数查询将不会把Query的参数带入sql查询，具体代码如下：

```
/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/7/31
 * @since 2019/7/31
 */
public interface IAquamanService<T> extends IService<T> {

    /**
     * 简化分页带参数查询
     * @param query
     * @return
     */
    default IPage<T> pageByQuery(PageQuery query) {
        return page(query, query.instanceQueryWrapper());
    }
}
```

## 系统已使用开源项目

前端框架[vue-element-admin](https://panjiachen.github.io/vue-element-admin-site/zh/)

ORM框架[MyBatis-Plus](https://mp.baomidou.com/)

还有很多，后期一一罗列...

在此鸣谢开源项目贡献者们

