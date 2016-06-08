# 相关设计介绍

## 秒杀API的URL设计

```
GET     /seckill/list                   秒杀列表
GET     /seckill/{id}/detail            详情页
GET     /seckill/time/now               系统时间
POST    /seckill/{id}/exposer           暴露秒杀
POST    /seckill/{id}/{md5}/execution   执行秒杀
```

## spring mvc 运行流程

![spring mvc 运行流程](images/spring-mvc-folw.png)


## 注解映射技巧

@RequestMapping注解：

1. 支持标准的URL,
2. Ant风格URL,(即?和*和**等字符)
3. 带{XXX}占位符的URL


例如：

- /user/*/creation    匹配 /user/aaa/creation、 /user/bbb/creation等URL
- /user/**/creation   匹配 /user/creation、 /user/aaa/bbb/creation等URL
- /user/{userId}      匹配 /user/123、 /user/abc等URL