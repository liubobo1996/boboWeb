# 目录
- Spring, Spring MVC, Spring Boot
- Spring IoC
    - 定义
        - IoC
        - DI
        - Bean
    - 声明 Bean 的注解
        - 类
        - 方法
- Spring AOP
- Spring MVC
    - 什么是 Spring MVC
    - 处理请求的流程:
        - 哪个类或者实例处理
    - 怎么拿到前端传的参数
        - 拿参数的几种方式
    - 路由怎么返回
        - 说一下大概有几种
        - 说自己日常用的那种
- Spring 设计模式
- Spring 事务
    - 用法
    - 事务作用范围, 回滚规则
- Spring 注解
    - 有哪些注解
    - 用到了哪些注解, 是什么框架提供的(Spring MVC 还是 mybatis 还是 Spring)
- Spring 自动装配模式
    - 什么是自动装配.
    - 有几种模式
    - 用过哪几种

### Spring, Spring MVC, Spring Boot
```bash
# Spring
开源的 Java 开发框架, 集成了多种模块, 主要设计思想是 IoC, 主要功能是 DI 和 AOP
# Spring MVC
Spring 中的一个模块, 能通过 MVC 模式实现 Web 应用
# Spring Boot
为 Spring 提供简化配置
```
### Spring IoC
```bash
# 定义
# IoC, Inversion of Control, 控制反转
controller 中需要的 service 实例交给外部容器创建
# DI, dependency injection, 依赖注入
外部容器创建了需要的 service 实例后通过 controller 的构造函数注入
# Bean
由 IoC 容器管理的对象

# 声明 Bean 的注解
# 类
@Component # 通用, 可标注任意类
@Controller # 控制层, 接收请求, 调用服务层处理请求, 返回响应
@Service # 服务层, 调用持久层 (DAO 层) 处理请求
@Repository # 持久层 (DAO 层), 例如 mapper 接口及其 xml 配置, 与数据库交互
# 方法
@Bean
```
### Spring AOP
```bash
# 什么是切面
# 为什么要面向切面编程
# 可以用来做什么
# 怎么用, 你平常怎么匹配切面, 怎么插入代码
```
