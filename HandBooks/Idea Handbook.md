# 功能
  * 内置Jshell: Tools -> Jshell

  * 设置根目录: 右键src文件夹 -> Mark Directory as -> sources root

  * 在类里面右键: Generate -> Getter and Setter 或 toString()

  * 左侧 上方齿轮图标 Compact Middle Packages 折叠中间包

  * IDE连接数据库 (实现SQL语句高亮和补全 )   
    1. 打开IDE最右侧的Database
    2. + -> Data Source -> MySQL
      * Name: ssm
      * Driver: MySQL
      * Host: localhost
      * Port: 3306
      * User: root
      * Password: 12345
      * Database: ssm (对应MySQL内的ssm数据库)
      * URL: jdbc:mysql://localhost:3306/ssm?characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
        * (application.properties的spring.datasource.url属性)
    3. APPLY

  * 默认 LF
    1. File -> Settings
    2. Editor -> Code Style 

  * 右边栏显示database: view->tool windwos->database

  * Edit->find->replace 点击Aa变蓝 区分大小写

  * 清空缓存再重启IDE

  * **导入/导出配置**

# 快捷键
  * Ctrl+C 复制行
  * Ctrl+D 复制并粘贴行
  * Ctrl+X 剪切并删除行
  * Ctrl+鼠标滚轮 字体放大/缩小
  * alt+shift+左键，多行光标
  * alt+shift+↑/↓ 相邻行置换

# 主题
  * Hiberbee Dark

# 插件
  * MyBatisCodeHelper Pro
  * MyBatisX
    * 在接口中写方法可以直接在mapper.xml文件中写CRUD方法生成相应的标签
    * 快捷键是: alt+enter
