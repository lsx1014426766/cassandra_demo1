1从官网下载 适合jdk1.7的cassandra
2启动服务脚本cassandra.bat遇到问题，3次询问是否运行脚本 R 启动服务
3cassandra.cli.bat启动链接客户端
4新建maven项目，在idea里加载maven插件特别慢，所以src会很长时间看不到
 注意选择自己下载的maven，而不是用idea自己的
5练习多种方式的crud操作

6cql语法
cql客户端登陆，先安装python并且配置环境变量
配置python，cassandra环境变量
在cassandra的bin目录下执行python cqlsh;
进入了编辑操作目录
user testkeyspace1;
desc table student;
create index on student(a
ge);
drop index age_idx;
登录方式2：  powershell方式
在cassandra的bin目录下执行./cqlsh;  直接cqlsh.bat链接失败
