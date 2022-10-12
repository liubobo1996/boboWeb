#### Linux基础
###### 终端 (terminal)  / 壳程序 (shell) / 控制台(console)
    - windows 用 cmder，cmder 所在目录必须是全英文
    - mac: terminal
    - 终端程序可以运行不同的 Shell (壳)
    - Windows Shell 有 cmd PowerShell
    - Linux/Mac Shell 有 Bash 和 Zsh

###### 常用命令
- 帮助
    - man
    - --help
- pwd
    - print working dir
    - 显示现在所处的目录
- ls
    - 不带参数就显示当前目录下的所有文件
    - 程序可以加参数
    - `-l` 显示详细信息
    - `-h` 人性化显示文件尺寸
    - `-a` 显示所有文件， 以 . 开头的文件是隐藏文件
    - 还可以带一个目录当参数，这样就会显示这个目录
    - 下面两个是等价的
        - `ls -l -h`
        - `ls -lh`
- cd
    - 改变当前目录 change directory
    - . 代表当前目录 `cd .`
    - .. 代表上级目录
    - cd 不带参数就回到默认的家目录
    - 每个用户都有一个家目录，默认在 `/home/用户名`
    - root 用户的家目录是 `/root`
- touch
    - `touch a.gua`
    - 如果 a.gua 存在就更新修改时间
    - 如果 a.gua 不存在就创建文件
- mkdir
    - 创建一个目录
    - `-p` 可以一次性创建多层目录
    - `mkdir -p a/b/c`
- cp
    - 复制出一个文件，用法如下
    - `cp a.txt b.txt`
    - 复制 a.txt 并把新文件取名为 b.txt
    - 复制目录要加上 -r 参数
    - `cp -r a b`
- rmdir
    - 只能用来删除一个空目录
- rm
    - 这个命令直接删除东西，很危险，一般不要用
    - 删除文件或者目录
    - `-f` 强制删除
    - `-r` 用来删除目录
- mv
    - 移动文件或者文件夹
    - 也可以用来改名
    - `mv a.txt b.txt`
    - `mv b.txt ../`
    - `mv b.txt ../gua.txt`
    - 可以用 `mv xx /tmp` 的方式来将文件放入临时文件夹
    - tmp是操作系统提供的临时文件夹，重启会删除里面的所有文件
- cat
    - 显示文件内容
- less head tail
    - less 可以前后退看文件 q 退出
    - head 可以显示文件的前 10 行
    - tail 可以显示文件的后 10 行
      - tail -f filename 显示文件末尾 并且不断刷新 更新就可以看到最新内容
    - head 和 tail 有一个 -n 参数
    - `head -n 20 a.gua`
- echo
    - 用于将输入的字符串送往标准输出
- **信息查找**
  - file
      - 显示文件的类型（不是百分之百准确）
  - uname
      - 显示操作系统的名字或者其他信息
      - `uname -r`
      - `uname -a`
  - which
      - `which pwd`
      - 显示 pwd 的具体路径
  - whereis
      - `whereis ls`
      - 显示更全面的信息
  - whoami
  - find
      - `find . name "a*"` 当前目录下a开头的文件
      - `find /usr/bin/* -name py*`

  - history
      - 查看历史命令
  - grep
      - 查找
      - `grep "a" a.gua` 在文件a.gua里面找a
- **权限控制**
  ```
  bash
  文件权限    文件类型 用户 用户组 文件大小  修改日期     文件名
  -rw-rw-r--  1       gua gua     10     .gua 11/09 20:28 b
  drwxrwxr-x  2       gua gua     4096    11/09 20:28 tmp

  文件类型    是否可读  是否可写  是否可执行
  d           r       w           x
  -           r       w           x
  ```
  - sudo
      - 用管理员帐户执行程序
      - 比如安装程序或者修改一些系统配置都需要管理员权限
  - su
      - switch user， 切换用户
      - `su ubuntu`
      - `sudo su`

  - 三组 rwx 分表代表 所属用户|同组用户|其他用户
  - rwx 可以用数字表示为 421
    - r-- 就是 4
    - rw- 就是 6
    - rwx 就是 7
    - r-x 就是 5
  - chown
      - 改变文件的用户
      - `chown gua c.gua`
      - `chown gua:gua c.gua`
  - chmod
      - 改变文件权限，不要用数字
      - `chmod 666 root.gua`
      - 修改所属用户权限 `chmod u+x tmp`
      - 修改同组用户权限 `chmod g+x tmp`
      - 修改其他用户权限 `chmod o+x tmp`

###### 目录结构
- `/home` 家目录
- `/root` root 用户目录
- `/etc` 放配置文件
- `/tmp` 临时文件
- `/var/log` 日志
- `/bin` binary 命令存放的地方。有好几个 bin，不用理解。
- `/lib` library 库


###### 常见符号
``` bash
# 这是注释
# $ 用来表示普通用户的提示符
$ ls
# # 还可以用来表示 root 用户的提示符
# service ssh restart
```

```bash
# ~ 家目录快捷方式
$ cd ~
$ pwd

# > 覆盖式重定向
$ echo hello > test.txt
$ echo hello > test.txt
$ cat test.txt

# >> 追加重定向
$ echo hello >> test.txt
$ echo hello >> test.txt
$ cat test.txt

# |  管道, 用来组合命令
$ history | grep "find"
`car a.txt | grep "a"`
`ps aux | grep "java"` 在当前运行程序中搜索java

# ``  获取命令执行的结果
echo `pwd` > test.txt
```

```bash
# &   后台执行
$ python3 server.py &
# 可以用 fg 命令把一个在后台的程序拉到前台来
$ fg
# 可以用 Ctrl-z 来把一个前台的程序放到后台去挂起
```

###### 其他命令
- ps
    - 查看进程, 一般用下面的用法
    - `ps ax`
    - `ps ax | grep python`
    - 查看带 python 字符串的进程
    - `ps axf`
- kill 和 killall 杀进程
    - 用 `ps ax` 找到进程id (pid)
    - 普通杀： `kill [pid]` / `kill -15 [pid]` / `kill -TERM [pid]`
    - 强制杀： `kill -9 [pid]` / `kill -KILL [pid]`
    - 先用 `kill` 再用 `kill -KILL`，不要用带数字的
    - killall 是用进程名字来杀进程
- 后台前台
    - fg
    - jobs
- 快捷键
    - C-z 挂起到后台
    - C-c 中断程序
    - C-d 退出程序
- reboot
    - 重启
- shutdown
    - 关机
    - 可以用参数指定时间
- halt
    - 关机
- **ssh相关命令**
  - 本地查看 ssh 日志 `ssh -v`
  - ssh 流程
  - 服务器 ssh 日志 `grep sshd /var/log/auth.log`
- **软件安装和设置相关命令**
  ``` bash
  apt update
  apt install -y ssh git
  ```
