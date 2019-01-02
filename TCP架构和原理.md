[TOC]

### TCP分层结构



![TCP分层结构](img\TCP分层结构.png)



### TCP-在HTTP的应用

![TCP-Http响应](img\TCP-Http响应.png)

注：*请求过程，箭头为上图的反方向*



### TCP报文结构

[TCP报文结构](https://blog.csdn.net/sinat_32487221/article/details/55272283)
[TCP详解](https://www.cnblogs.com/yueminghai/p/6646043.html)



### TCP三次握手和四次挥手

1. 三次握手

   ![](img\TCP-三次握手.png)



2. 四次挥手

   ![](img\TCP-四次挥手.png)



### TCP滑动窗口协议

1. 缓存分类

    * 发送方数据分类

      ```javascript
      1.已发送，已确认
      2.已发送，未确认
      3.未发送，但允许发送
      4.未发送，不允许发送
      
      其中2、3属于发送窗口
      
      发送窗口的的后沿是跟缓存的后沿重合，已经被确认的数据会从缓存中移除掉
      ```

      * 接收方数据分类

        ```javascript
        1. 已接收 
        2. 未接收但准备接收 
        3. 未接收而且不准备接收
        
        其中2属于接收窗口
        ```

2. 滑动窗口原理

   * 发送窗口变化

     ```javascript
     1.窗口左沿向右移动，从“已发送但未确认的数据区”中确认发送成功的数据
     2.窗口右沿向右移动，“未发送但允许发送的数据区”允许加入的新的数据
     3.窗口右沿向左移动，Host Requirement RFC强烈建议不要这样做，但TCP必须能够在某一端产生这种情况时进行处理。
     ```

   * 对ACK的认识

     ```javascript
     发送从序号n开始的，和后面的100个数据，数据序号为n~(n+100)
     接收方完全接收后这序号n~(n+100)数据后，会向发送方回复一个ACK，其中包含ack=（n+100）+1，要求发送方发送从序号(n+100)+1开始往后的数据
     
     *如果接收不能完整接收数据，就会回复序号连续的最后一个数据的序号，如从n+50后出现不连续,回复的ACK中ack=(n+50)+1,当接收方接收接收到新数据后会丢弃原来的重复的旧数据
     
     *接收方不是每接收到发送方的收据后，就回复一个ACK，有可能接收几次后才会回复一个ACK
     ```

4. 流量控制

   ```javascript
   1.接收端会在ACK中回复自己的窗口大小，发送方的发送窗口不能超过接收方给出的接受窗口的数值
   
   2.当发送方接收到 接收端ACK中窗口大小为0时，会启动“持续计时器”，主动向接收端询问窗口大小
     a.返回的还是0窗口大小，继续启动“持续计时器”
     b.当返回窗口大小不是0时，打破死锁，继续发送数据
   ```

5. 与报文的联系

   ```javascript
   报文结构的窗口大小影响着滑动窗口的大小
   ```

6. 与java `Socket` 缓冲区的联系

   ```javascript
   一、TCP的滑动窗口大小实际上就是socket的接收缓冲区大小的字节数
   
   二、对于server端的socket一定要在listen之前设置缓冲区大小，因为，accept时新产生的socket会继承监听socket的缓冲区大小。对于client端的socket一定要在connet之前设置缓冲区大小，因为connet时需要进行三次握手过程，会通知对方自己的窗口大小。在connet之后再设置缓冲区，已经没有什么意义。
   
   三、由于缓冲区大小在TCP头部只有16位来表示，所以它的最大值是65536，但是对于一些情况来说需要使用更大的滑动窗口，这时候就要使用扩展的滑动窗口，如光纤高速通信网络，或者是卫星长连接网络，需要窗口尽可能的大。这时会使用扩展的32位的滑动窗口大小。
   ```

   **注：**滑动窗口传输的是一个个字节，不是一段段报文



参考文章

[TCP的分层结构](https://www.cnblogs.com/nuomin/p/5708728.html)
[分布式架构的基础-TCP通信协议](https://www.jianshu.com/p/77bd686b946a)
[TCP报文结构](https://blog.csdn.net/sinat_32487221/article/details/55272283)
[TCP详解](https://www.cnblogs.com/yueminghai/p/6646043.html)
[(传输层)TCP协议](https://www.cnblogs.com/kzang/articles/2582957.html)
[TCP-滑动窗口原理](https://www.jianshu.com/p/6ce48b731f1e)
[TCP滑动窗口详解](https://www.cnblogs.com/freebird92/p/6442155.html)
[TCP的流量控制机制与滑动窗口](https://blog.csdn.net/xiaomicjh/article/details/76643559)








