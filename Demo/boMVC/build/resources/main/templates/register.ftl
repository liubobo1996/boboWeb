<html>
    <head>
        <!-- meta charset 指定了页面编码, 否则中文会乱码 -->
        <meta charset="utf-8">
        <!-- title 是浏览器显示的页面标题 -->
        <title>注册</title>
    </head>
    <body>
        <a href='/login'>LOGIN</a>
        <a href='/'>HOME</a>
        <h1>注册</h1>
        <form action="/register" method="post">
            <input type="text" name="username" placeholder="请输入用户名">
            <br>
            <input type="text" name="password" placeholder="请输入密码">
            <br>
            <button type="submit">注册</button>
        </form>
        <h3>${result}</h3></body>
    </body>
</html>