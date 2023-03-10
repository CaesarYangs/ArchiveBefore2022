# Data Link Layer

两台相邻计算机实现可靠有效的完整信息块通信算法。而不像物理层一样关注单个比特。

存在主机之间的传输限制：延迟，速率，错误。

数据链路层的目标是在两个网络层进程之间提供无差错的、透明的通信。

## 总体设计

数据链路层使用物理层提供的服务在通信信道上发送和接收比特。

* 向网络层提供定义良好的服务接口
* 处理传输错误
* 调节数据流。确保慢速接收方不被淹没。

为了实现这些目标，数据链路层从网络层获得数据包，然后将这些数据包封装成帧以便传输。

**帧的管理构成了数据链路层工作的核心**。帧 = 帧头+有效载荷+帧尾

**提供给网络层的服务——向网络层提供定义良好的服务接口**

广义上数据链路层的功能：为网络层提供服务。

最主要的服务是将数据从源机器网络层传输到目标机器网络层。

数据链路层可以设计成向上提供各种不同的服务。实际提供的服务因具体协议的不同而有所差异。

*   无确认的无连接服务

    采用这种服务，事先不需要建立逻辑连接，事后也不用释放逻辑连接。
*   有确认的无连接服务

    当向网络层提供这种服务时，数据链路层仍然没有使用逻辑连接，但其发送的每一帧都需要单独确认
*   有确认的有连接服务

    源机器和目标机器在传输任何数据之前要建立一个连接。连接上发 送的每一帧都被编号，数据链路层确保发出的每个帧都会真正被接收方收到。它还保证每个帧只被接收一次，并且所有的帧都将按正确的顺序被接收。

> 当使用面向连接的服务时，数据传输必须经过三个不同的阶段。在第一个阶段，要建立连接，双方初始化各种变量和计数器，这些变量和计数器记录了哪些帧己经接收到，哪些帧还没有收到。在第二个阶段，才真正传输一个或者多个数据帧。在第三个也是最后一个阶段中，连接被释放，所有的变量、缓冲区以及其他用于维护该连接的资源也随之被释放。

**成帧——处理传输错误**

检测错误和 纠正错误(有必要的话)的工作正是数据链路层该做的。

对于数据链路层来说，通常的做法是将比特流拆分成多个离散的帧，为每个帧计算一个称为校验和的短令牌，并将该校验和放在帧中一起传输。

即：独立且离散的帧+校验令牌 二者放置在一起一同传输

1.  字节计数法

    利用头部中的一个字段来标识该帧中的字符数。头部设立计数器。
2.  字节填充的标志字节法

    考虑出错后的同步问题。让每个帧用特殊字节作为开始和结束，即标志字节FLAG。同时包括转义字节ESC。
3.  比特填充的标志比特法

    考虑了字节填充的缺点，即只能使用8bit字节。帧的划分可以在比特级完成，因而帧可以包含由任意大小单元组成的二进制比特数。
4. 物理层编码违禁法

{% hint style="success" %}
字节填充方案是 PPP 协议( Point-to-PointProtocol) 使用的略微简化形式，该协议通常用在通信链路上传送数据包。我们将在本章后面讨论 PPP 协议。
{% endhint %}

**差错控制与流量控制——调节数据流**

差错控制

> 如何确保所有的帧最终都被传递给目标机器的网络层，并且保持正确的顺序

利用控制帧实现：肯定确认ACK 否定确认NAK

设置并管理超时计时器以及序号。

流量控制

发送方与接收方速率不匹配所导致的现象。

* 基于反馈的流量控制(feedback-basedflow control)
* 基于速率的流量控制(Crate-based flow control)

## 差错检测和纠正

网络设计者针对错误处理已经研究出两种基本策略。这两种策略都在发送的数据中加 入冗余信息。

* 感知并处理：一种策略是在每一个被发送的数据块中包含足够多的冗余信息，以便接收方能据此推断出被发送的数据是什么。——纠错码
* 感知并请求重传：另一种策略也是包含一些冗余信息，但这些信息只能让接收方推断出是否发生了错误（而推断不出哪个发生了错误），然后接收方可以请求发送方重传。——检错码

## Elementary DataLink Protocol

### **Utopian Simplex Protocol**

无任何流量控制和纠错

### **Stop-Wait for Error-free**

发送方以高于接收方能处理到达帧的速度发送帧，导致接收方被淹没。

**+流量控制**

具体方法：

* ACK

> 这个问题的更一般化解决方案是让接收方给发送方提供反馈信息。接收方将数据包传 递给网络层之后给发送方返回一个小的哑帧，实际上这一帧的作用是给发送方一个许可， 允许它发送下一帧。发送方在发出一帧之后，根据协议要求，它必须等待一段时间直到短 哑帧(即确认〉到达。这种延缓就是流量控制协议的 一个简单例子。

发送方发送一帧，等待对方确认到达后才能继续发送，这样的协议称为停·等式协议 (stop-and-wait)。

### **Stop-Wait for Noisy Channel**

现在让我们来考虑比较常规的情形，即通信信道可能会出错。帧可能会被损坏，也可能完全被丢失。然而，我们假设，如果一帧在传输过程中被损坏，则接收方硬件在计算校 验和时能检测出来。

**+差错控制**

具体方法：

* timer
* Seq 接收方通过此来区分到达的帧是第一次发来的新帧还是被重传的老帧
* ARQ自动重复请求 发送方在前移到下一个数据之前必须等待一个肯定确认

> 协议 3 与其前任协议的不同之处在于，当发送方和接收方的数据链路层处于等待状态 时，两者都用一个变量记录下了有关的值。发送方在 next 企ame to send 中记录了下一个 要发送的帧的序号:接收方则在企ame\_expected 中记录了下一个期望接收的序号。每个协 议在进入无限循环之前都有一个简短的初始化阶段。 已经开始类似于滑动窗口的两方同时监听的思路了。

## Sliding Window Protocol

之前的协议所考虑的仅仅是一个单工的，简单低效的情况。数据帧只在一个方向上传输。而在实际中，数据通常要在两个方向上同时传输。即：全双工。

实现方法讨论：

1. 使用之前的某种协议两次。同时建立前向与逆向传输信道。--->_两种条件下总有一条信道被浪费_
2. **使用同一条链路来传输两个方向上的数据** 只需分辨确认帧头部kind字段，便可以区分数据帧还是确认帧。

* 进一步改进：**捎带确认**Piggybacking 确认信息ack搭乘下一个处境数据帧的便车。暂时延缓确认信息并将其搭载在下一个出境数据帧的技术。通过这种方法更好地利用了信道的可用带宽，减少对带宽没有利用的等待时间。

以下均为滑动窗口协议，为一整个大类协议。在效率，复杂性和缓冲区需求方面有所不同。

**滑动窗口本质** 所有滑动窗口协议的本质是在任何时刻发送方总是维持着一组序号,分别对应于允许它发送的帧。我们称这些帧落在发送窗口(sending window)内。

接收方维持一个接收窗口(receiving window)，对应于一组允许它接受的帧。

发送方与接收方的窗口不必有同样的上下界，甚至也不必有同样的大小。

**发送窗口** 上边界：已发送的帧 下边界：收到确认的帧 **接收窗口** 上下边界构成的窗口：可以接受的帧。

### **One-bit Sliding Window**

本协议使用了停等式方法。

结合了timer 发送帧的seq 但由于过于简单，在传输过程复杂起来后会出现很多问题。

### **Go-Back-N Protocol**

> 到现在为止,我们一直有这样的默契假设,即一个帧到达接收方所需要的传输时间加上确认帧回来的传输时间可以忽略不计。有时候,这种假设明显是不正确的。在这些情形下,过长的往返时间对于带宽的利用效率有严重的影响

**考虑帧的往返时间对带宽效率造成的影响**

造成的原因是：[ARQ自动重复请求](data-link-layer.md#stop-wait-for-noisy-channel)放松这一限制，便能够得到更好的带宽利用率。

定义链路利用率

**管道化 pipeline** 保持多个帧同时在传送的技术。在不可靠的通信信道上像管道一样传送帧会引发严重的问题。这一点便引出了此两种滑动窗口协议的特点。

_位于数据流中的一个帧被损坏或丢失，会发生什么？_

回退N 接收方只需简单丢弃所有坏帧到达后的所有后续帧，针对这些丢弃的帧不返回确认。

> 这种策略对应于接收窗口大小为1 的情形。换句话说,除了数据链路层必须要递交给网络层的下一帧以外,它拒绝接受任何帧。如果在计时器超时以前,发送方的窗口己被填满,则管道将变为空闲。最终,发送方将超时,并且按照顺序重传所有未被确认的帧,从那个受损或者被丢失的帧开始。如果信道的错误率很高,这种方法会浪费大量的带宽。

### **Selective Repeat Protocol**

针对管道化传送会发送错误帧的情况，通用的方法是选择重传。

> 选择重传协议185 如果错误很少发生,则回退n协议可以工作得很好:但是,如果线路质量很差,那么重传的帧要浪费大量带宽。另一种处理错误的策略是选择重传协议,允许接收方接受并缓存坏帧或者丢失帧后面的所有帧。

**接收方将收到的坏帧丢弃，但接受并缓存坏帧后面的所有好帧。** **当发送发超时，它只重传那个最早未被确认的帧。**

通常结合否定策略一起使用。当接收方检测到错误，发送一个否定确认NAK。NAK可以直接出发该帧的重传操作，而不需要等到相应的计时器超时。协议性能得以提高。



{% hint style="info" %}
六种讲解的基本协议，层次递进，逐步从特殊到一般。

0-->NAK-->Timer-->Seq-->ARQ-->NAK
{% endhint %}

## 数据链路协议实例

#### SONET上的数据包

在物理层上涉及过这种协议。 为了在这些链路上承载数据包，需要某种成帧机制，以便将偶尔出现的数据包从传输他们的持续比特流中分离出来。运行在IP路由器上的PPP协议提供了这种运行机制。

PPP属于数据链路层，在网络层之下，专门用于为IP层服务。

PPP功能包括处理错误检测链路的配置，支持多种协议，允许身份认证等。提供了3个主要特性：

1. 一种成帧方法。
2. 一个链路控制协议 Link Control Protocol。
3. 一种协商网络层选项的方式。

## 小结

* 数据链路层的任务是将物理层提供的原始比特流转换成由网络层使用的帧流。
* 链路层为这样的帧流提供不同程度的可靠性,范围从无连接无确认的服務到可靠的面向连接服务不等。
* 链路层采用的成帧方法各种各样,包括字节计数、字节填充和比特填充。数据链路协议提供了差错控制机制来检测或纠正传输受损的帧,以及重新传输丢失的帧。为了防止快速发送方淹没慢速接收方,数据链路协议还提供了流量控制机制。滑动窗口机制被广泛用来以一种简单方式集成差错控制和流量控制两大机制。当窗口大小为1 个数据包时,则协议是停·等式的。
* Internet使用PPP作为点到点线路上的主要数据链路协议。PPP协议提供了无连接的无确认服务,使用标志字节区分帧的边界

## Q\&A

* 为什么数据链路层要用帧的形式传播
* 具体的数据链路层的网络应用
* 检测纠错机制的最底层原因
* 为什么做流量控制
* 流量控制的方法
* ARQ解释
* 什么是滑动窗口 发送端与接收端
* 如何实现go back n
