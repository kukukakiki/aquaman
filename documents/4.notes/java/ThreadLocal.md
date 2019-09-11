****# ThreadLocal

## 概念

ThreadLocal是线程本地变量，可以实现多线程的数据隔离。ThreadLocal为每一个使用该变量的线程都提供了一份独立的副本，线程间的数据是隔离的，
每个线程只能访问各自内部的副本变量。

## ThreadLocal类图

[]()[![ThreadLocal](https://github.com/kukukakiki/aquaman/blob/master/documents/3.software/java/images/ThreadLocal.png?raw=true)](ThreadLocal)

## 常用方法

ThreadLocal常用的三个方法：

> set

以下是ThreadLocal的set方法源码，代码不多很好理解

``` java
public void set(T value) {
    Thread t = Thread.currentThread();
    ThreadLocalMap map = getMap(t);
    if (map != null)
        map.set(this, value);
    else
        createMap(t, value);
}
```
1. 获取当前线程
2. 从ThreadLocalMap获取
3. 判断map不为空
4. 如果不为空则值保存至map中
5. 如果为空则创建一个新的createMap，然后将值保存新建的map中

> get

获取当前当前线程的属性值

``` java
public T get() {
    Thread t = Thread.currentThread();
    ThreadLocalMap map = getMap(t);
    if (map != null) {
        ThreadLocalMap.Entry e = map.getEntry(this);
        if (e != null) {
            @SuppressWarnings("unchecked")
            T result = (T)e.value;
            return result;
        }
    }
    return setInitialValue();
}
```
1. 获取当前线程t
2. 通过t获取类型为ThreadLocalMap的map集合对象
3. 判断map不为空
4. 如果不为空则通过map.getEntry(this)获类型为ThreadLocalMap.Entry的e
5. 判断e不为空，则过调用e.value属性，获取实际存储的值并返回
6. 如果为空则调用setInitialValue()进行初始化

> initialValue

> remove

## spring security中ThreadLocal使用场景

### 上下文获取用户登陆信息

- 通过调用如下方法，获取登陆用户信息，其中"SecurityContextHolder.getContext()"就是一个ThreadLocal

```
SecurityContextHolder.getContext().getAuthentication()
```

- 跟进SecurityContextHolder类的getContext()方法，我们发现调用如下，strategy是ThreadLocalSecurityContextHolderStrategy对象

```
public static void setContext(SecurityContext context) {
    strategy.setContext(context);
}
```

- 继续跟进ThreadLocalSecurityContextHolderStrategy类的getContext()方法

不难发现spring security默认情况下，使用的是ThreadLocal存储登陆信息到上下文中

```
private static final ThreadLocal<SecurityContext> contextHolder = new ThreadLocal<>();

public SecurityContext getContext() {
    SecurityContext ctx = contextHolder.get();

    if (ctx == null) {
        ctx = createEmptyContext();
        contextHolder.set(ctx);
    }

    return ctx;
}
```
