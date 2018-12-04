### mysql_binlog

记录mysql的数据更新或者潜在更新

1. 潜在更新：`delete from table where id = x`当不存在x的id，并没有报错，也会被记录下来
2. 主从复制依赖binlog，`master-salve`



#### 原理流程

![mysql_binlog](img/mysql-binlog.png)



#### binlog日志模式

1. statement：sql语句模式
2. row：每一行数据被修改的形式，如`update table set value= x`(无主键ID)会修改到多条记录，这些修改后记录会被写入到日志里
3. mixed：混合模式，mysql自动选择一种



#### binlog的使用









