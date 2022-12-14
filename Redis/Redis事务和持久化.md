Redis是 单线程 + IO多路复用

Redis事务是一个单独的隔离操作：事务中的所有命令都会序列化，按顺序执行
事务在执行过程中，不会被其他客户端发送来的命令请求所打断
Redis事务的主要作用就是串联多个命令防止别的命令插队
multi开启事务 -> 发送命令进入队列 -> 等命令发完然后exec依次执行队列里的命令结束事务 / 或者discard取消所有发送的命令结束事务

Redis的事务和MySQL的事务是有区别的
注意点：
1、假如在multi之后，往队列里加命令的时候，如果错写了一条错误的命令，会导致后续执行exec时，往队列里加的命令全部执行失败
比如：
multi
set k1 v1
set k2 v2
set k3
exec
全部执行失败
2、如果在multi之后、往队列里加命令的时候，全部是正确的命令，然后exec命令执行队列里所有的命令，如果其中有命令执行失败，不会影响其他命令执行
比如：
multi
set k1 v1
incr k1 (对v1 + 1，虽然命令没问题，但是v1是string类型的，不能加1，后续执行会出错，但不会影响其他命令执行)
set k2 v2
exec
其中只有incr k1失败

watch 可以取监视某个key，当redis客户端，只要有一个改成功了这个key的值，就会让其他客户端事务的全部操作失败，（乐观锁）
每个客户端只会监视一次，当某个客户端对其操作成功后，就需要重新watch

使用watch+redis事务虽然能解决并发安全超卖的问题，但是会出现库存遗留的问题
因为watch+redis事务的这种操作，有些线程会失败，假如商品足够多，但是并发下大部分线程同时抢由于乐观锁出现抢失败了，如果客户没有重新抢的话就可能会有商品遗留的问题
lua脚本解决超卖和遗留问题，实质上是利用redis单线程解决并发问题，当一个客户端在操作时，其他客户端都不能操作

内存数据持久化
RDB（默认开启）：主进程调用fork函数生成一个子进程，子进程拥有和父进程fork当时相同的数据，但是子进程有自己的CPU资源和内存资源，所以如果数据量很大的话，fork的时候会占用另一片相同大小的内存空间
缺点：可能两次生成快照rdb文件时，第二次还没生成就挂了，导致这之间的数据丢失，二是更加占用内存
AOF（默认不开启）：Append Only File，只会记录所有的写操作拼接在AOF文件后，读操作不记录
注意：当开启AOF，重启redis-server时，之前RDB里的数据不会加载到内存里，因为两种都开启的时候，Redis默认会去读取AOF的文件数据，RDB里的数据不会丢失，只是没有进入到内存中
如果有误操作，想要恢复误操作之前的数据，这就要求我们尽早给dump.rdb文件/appendfile文件做备份，以便恢复
如果AOF文件损坏，可以使用redis自带的命令进行AOF文件修复 redis-check-aof --fix appendonly.aof.1.incr.aof

AOF同步频率（默认everysec）：
appendfsync always 每次写操作都立刻记录日志，性能较差但数据完整
appendfsync everysec 每秒写一次日志，如果挂了，可能导致丢失1秒的数据
appendfsync no redis不主动同步日志，交给操作系统去同步

AOF采用文件追加的方式，文件会越来越大，当达到文件设置阈值的一倍（假如原来设置AOF文件最大为64MB，当文件达到128MB时才进行rewrite），
Redis就会对AOF文件压缩，使用重写机制，只保留可以恢复数据的最小命令集（在不影响数据的前提下合并命令），压缩命令：bgrewriteaof