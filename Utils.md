#### 计算 hash 值方法
```bash
# win10 系统
$ certutil -hashfile <文件路径> md5
# mac 系统
$ md5 <文件路径>
```
#### GET
```bash
GET /search?name=gua&height=169 HTTP/1.1
Connection: Keep-Alive
空行
body
```
#### POST
```bash
POST /search HTTP/1.1
 Content-Type:application/x-www-form-urlencoded 
空行
name=gua&height=169
```
#### Response
```bash
HTTP/1.1 200 OK
 Content-Type: text/html; charset=UTF-8
空行
body
```
#### log
```
private static void log(String format, Object... args) {
    System.out.println(String.format(format, args));
}
```
#### ensure
```
public static void ensure(boolean condition, String message) {
    if (!condition) {
        log("%s", message);
    } else {
        log("测试成功");
    }
}
```
#### JS log
```
var log = console.log.bind(console)
```
#### JS 元素选择
```
var e = function (selector) {
    return document.querySelector(selector)
}
```
#### BootstrapCDN
```bash
# https://v3.bootcss.com/getting-started/
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
<#-- jQuery CDN-->
<script src="https://cdn.jsdelivr.cn/npm/jquery@1.12.4/dist/jquery.min.js" integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ" crossorigin="anonymous"></script>
```
#### 引入本地 CSS 和 JS
```bash
<!-- style -->
<#--    <link rel="stylesheet" href="//static2.cnodejs.org/public/stylesheets/index.min.23a5b1ca.min.css" media="all">-->
<link rel="stylesheet" type="text/css" href="/bo.css">

<!-- scripts -->
<#--    <script src="//static2.cnodejs.org/public/index.min.f7c13f64.min.js"></script>-->
<script type="text/javascript" src="/bo.js"></script>
```
#### Atom配置恢复
```bash
# https://www.jianshu.com/p/c69b5369b298
# Personal Access Token
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
# Gist ID
cebe0c86dca8c8f602e08aab4f43d794
```
#### 密码
```bash
# kuaibiancheng
徐陆晨/284115962 20090424ppp
18850042831 20090424ppp
# 解密视频
284115962 20090424ppp
# MySQL Root Password
12345
# IDEA
xuluchen0412@gmail.com
# Coding
284115962@qq.com a123456.
# Github
284115962@qq.com
```
