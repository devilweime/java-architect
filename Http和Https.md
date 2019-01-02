[TOC]

# 基础



## 对称加密

因为同一密钥既用于加密又用于解密

* 优点

  ```
  加密解密的速度都非常快
  ```

* 缺点

  ```
  密钥在传输中会被拦截到，可以非法的篡改传输的内容
  ```

* 算法：DES、3DES、AES、PBE等加密算法，这几种算法安全性依次是逐渐增强的




## 非对称加密

公开密钥（publickey）和私有密钥（privatekey）。公开密钥与私有密钥是一对，如果用公开密钥对数据进行加密，只有用对应的私有密钥才能解密；如果用私有密钥对数据进行加密，那么只有用对应的公开密钥才能解密。一般公钥是公开的（client），私钥是自己（server）保存。

其中公钥和私钥在数学上是有关联的。对称算法的根本原理就是单向函数，f(a)=b,只知道公钥b是很难推算出私钥a来的

* 优点

   ```javascript
   1.简单快捷，密钥较短，且破译困难。
   2.比对称加密安全
   ```

* 缺点

  ```javascript
  1.加密解密耗费时间长，不适合大数据进行加密和解密
  2.因为公钥是公开的，C可以拦截A的公钥，使用A的公钥对B发送消息，解决方案：加密明文+数字签名
  ```

* 算法

  ```javascript
  常见的非对称加密算法有：RSA、ECC（移动设备用）、Diffie-Hellman、El Gamal、DSA（数字签名用）
  ```



## hash算法（摘要算法）

Hash算法的特点是单向不可还原，用户可以通过hash算法对目标信息生成一段特定长度的唯一hash值，却不能通过这个hash值重新获得目标信息。

* 特点

  ```javascript
  只要数据源不同，得到的摘要必定不同
  ```

* 算法

   ```javascript
   常见的Hash算法有MD2、MD4、MD5、HAVAL、SHA
   ```

* 应用

   ```javascript
     常用于不可还原的密码存储、信息完整性校验
   ```




## 应用

* 数字签名

  ```javascript
  A将原始明文通过hash算法（摘要算法）得到一个摘要，然后将明文加密连同摘要一起发送给B，B拿到加密明文先解密成原始明文，再通过同一摘要算法拿到B的摘要，将此摘要与传送过来的A的摘要比较，就可校验明文的完整与否
  *A和B要知道他们使用的是同一套摘要算法
  *如果存在salt（盐值），明文中不能传输此salt，以支付网关为例，支付网关server的数据库会保留授予client请求的凭证（clientId、clientKey），client传输的明文中存在clientId=aa，client直接使用clientKey=bb作为salt计算出摘要，而支付网关server只能先根据解密的原始明文得到clientId=aa，再从自己的数据库拿到clientKey=bb，这样双方拿到salt都是一样的
  ```

* https连接过程

  非对称加密对称密钥（数据小）进行加密，client或server解密出对称密钥后，就可以使用对称密钥对内容（数据大）加密解密【非对称加密+对称加密结合】

  [HTTPS]: #HTTPS	"HTTPS"



# HTTPS

1. 客户端如何获得公钥

   ```javascript
   （1）提供一个下载公钥的地址，回话前让客户端去下载。（缺点：下载地址有可能是假的；客户端每次在回话前都先去下载公钥也很麻烦）
   （2）回话开始时，服务器把公钥发给客户端（缺点：黑客冒充服务器，发送给客户端假的公钥）
   
   “黑客”可以伪装服务器，向客户端发送自己的
   ```

2. 如何确认服务器是真实的而不是黑客

   ```
   上述方法无法解决此问题
   ```

3. 为什么使用CA证书？

   * 能够解决

     ```javascript
     1.“黑客”伪装服务器的问题，CA证书的签名可以防止“黑客”伪造证书
     2.数据传输安全性
     ```

   * SSL/TLS算法流程解析

     ![](E:\git-reposity\java-architect\img\SSl-TCL算法.png)

     ```javascript
     1.pre-master-key是经过公钥加密才发送给服务端，“黑客”没有私钥是无法获得pre-master-key
     2.三个随机数生成了对称密钥
     3.对称密钥不客户端和服务端传输，而是通过算法获得了相同的对称密钥
     ```


# 网络安全

黑客行为

1. 拦截数据
2. 伪装客户端
3. 伪装服务器



参考文章：

[对称加密和不对称加密原理](https://www.cnblogs.com/lvdongjie/p/4241107.html)
[java对称加密与非对称加密](https://blog.csdn.net/chengbinbbs/article/details/78640589)
[对称加密 非对称加密总结](https://www.jianshu.com/p/b078282653b3)
[HTTPS 原理详解](https://baijiahao.baidu.com/s?id=1570143475599137&wfr=spider&for=pc)
[Https如何保证了数据的安全？](https://blog.csdn.net/luweicheng24/article/details/80579731)
[HTTPS知识点整理](https://www.jianshu.com/p/14fdd1a2454a)
[数字证书原理,公钥私钥加密原理](https://blog.csdn.net/ly131420/article/details/38400583)
[SSL/TLS算法流程解析](https://www.cnblogs.com/littleatp/p/6219630.html)