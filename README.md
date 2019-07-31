# Aquaman Admin（海王管理系统）

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Spring-Boot](https://img.shields.io/badge/spring--boot-2.1.3.RELEASE-brightgreen)](https://spring.io/projects/spring-boot)
[![Spring-Cloud](https://img.shields.io/badge/spring%20cloud-Greenwich.RELEASE-brightgreen)](https://spring.io/projects/spring-cloud)
[![Vue-Element-Admin](https://img.shields.io/badge/vue--element--admin-3.9.0-brightgreen)](https://panjiachen.github.io/vue-element-admin-site/zh/)
[![MyBatis-Plus](https://img.shields.io/badge/mybatis--plus-3.1.0-brightgreen)](https://mp.baomidou.com/)

## 声明

本项目会长期更新并维护，不定期提交更新，可能会因为工作，生活等原因导致更新不及时问题请谅解）

## 开发进度

需要使用[XMind](https://www.xmind.cn/xmind8-pro/)打开查阅

[Aquaman管理系统.xmind](https://github.com/kukukakiki/aquaman/tree/master/aquaman-doc/1.%20requirements)

## 表设计

目前表结构尚在设计阶段不稳定，待发布正式版本后，将完成表结构提交至GITHUB上！！！

[表设计及SQL语句](https://github.com/kukukakiki/aquaman/tree/master/aquaman-doc/2.design/1.db%20design)

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

## 前端fe工程

前端工程基于[vue-element-admin](https://github.com/PanJiaChen/vue-element-admin)开发

在此鸣谢vue-element-admin贡献者们

## 问题记录跟踪

[有道笔记传送门](http://note.youdao.com/noteshare?id=d212a2fa2f7850518572b904e28d05ad)
