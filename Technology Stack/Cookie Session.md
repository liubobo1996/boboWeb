### [Cookie和Session]
#### 1. Cookie
  * 作用: 记住用户状态
  * 实现:
    1. 服务器在响应头加上set-cookie字段和value
    2. 浏览器接受响应 生成对应的cookie并储存
    3. 浏览器再度访问服务器将在请求头加上cookie字段和value

#### 2. Session
  * 作用: 在记住用户状态的基础上解决伪造
  * 实现:
    1. 浏览器访问服务器的/login
    2. 服务器验证帐号密码 找到该用户
    <!-- 2. 服务器检查请求头的cookie字段有没有sessionId -->
    3. 生成一个sessionId (token)
    4. 用户id和sessionId的映射保存到数据库
    5. 响应头增加字段 "Set-Cookie: session_id=随机字符串“
    6. 之后浏览器每次访问服务器 请求头都会带上该字段
    7. 服务器通过currenUser函数 从请求的cookie中获取sessionId
    8. 调用SessionService的findBySessionId获取该session对象s
    9. s为空则用户身份为游客, s非空则通过s获得用户id
    10. 通过UserService.findById获取该用户 继而获取用户名
