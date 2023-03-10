---
description: Abstract
---

# 绪论

### 操作系统概念

计算机软件包括系统软件和应用软件。系统软件：OS 汇编和编译程序等。应用软件：为应用编制的程序

操作系统与硬件软件的关系：

裸机->操作系统->编译软件->各种应用软件



引入操作系统的目的：

1. 用户观点：让计算机更好地服务用户，构建交互环境，保证服务的安全可靠。
2. 系统管理员观点：为了更合理地组织计算机工作流程。管理和分配软硬件资源。使其能高效共享。
3. 发展的观点：提供扩展支撑平台。

*   **操作系统定义**：

    操作系统是计算机系统中的一个系统软件，它是这样一些程序模块的集合—它们管理和控制计算机系统中的硬件及软件资源，合 理地组织计算机工作流程，以便有效地利用这些资源为用户提供一个具有足够的功能、使用 方便、可扩展、安全和可管理的工作环境，从而在计算机与其用户之间起到接口的作用。
*   操作系统特点：

    管理软硬件资源的系统软件；为用户提供服务；让用户高效地共享计算机软硬件资源，但保证其可靠性、安全性、可用性和可管理性。

### 操作系统历史

#### 手工处理阶段

#### 早期批处理

联机批处理

脱机批处理

#### 多道程序系统

* 多道。计算机内存同时存放几道相互独立的程序。
* 宏观上并行
* 微观上串行

#### 分时操作系统

批处理方式下，用户以脱机操作方式使用计算机，用户在提交作业以后就完全脱离了自 己的作业.在作业运行的过程中，不管出现什么情况，用户都不能加以干预，只有等该批作业处理结束。

所谓分时技术，就是把处理机的运行时间分成很短的时间片，按时间片轮流把处理机分配给各联机作业使用。若某个作业在分配给它的时间片内不能完成其计算，则该作业暂时中断，把处理机让给另一作业使用，等待下一轮时再继续其运行。由于计算机速度很快，作业运行轮转得很快，给每个用户的印象是好像他独占了一台计算机。而每个用户可以通过自己终端向系统发出各种操作控制命令，完成作业的运行。

_多用户分时操作系统是当今计算机操作系统中最普遍使用的一类操作系统。_

#### 实时操作系统

实时操作系统是以在允许的时间范围之内做出响应为特征。

#### 通用操作系统



### 操作系统基本类型

批处理，分时，实时，个人计算机，网络，分布式

#### 批处理操作系统

* 用户脱机使用计算机
* 成批处理
* 多道程序运行

多道批处理系统的优点是由于系统资源为多个作业所共享，其工作方式是作业之间自 动调度执行，并在运行过程中用户不干预自己的作业，从而大大提髙了系统资源的利用率和 作业吞吐量。其缺点是无交互性，用户一旦提交作业就失去了对其运行的控制能力;而且是 批处理的，作业周转时间长，用户使用不方便。

#### 分时操作系统

> 分时操作系统是一个联机(on-line)、多用户(multi-user)、交互式(interactive)的操作系统。

分时系统一般采用时间片轮转的方式，使一台计算机为多个终端用户服务，对每个用户 能保证足够快的响应时间，并提供交互会话能力。

* 交互性
* 多用户同时性
* 独立性

#### 实时操作系统

实时系统是另外一类联机的操作系统。

实时系统的主要特点是提供即时响应和高可靠性。系统必须保证对实时信息的分析和处理的速度比其进入系统的速度要快，而且系统本身要安全可靠。

* 实时时钟管理
* 连续的人机对话
* 过载保护
* 高可靠性和安全性所采取的冗余措施

#### 网络操作系统

计算机网络是通过通信设施将物理上分散的、具有自治功能的多个计算机系统互联起 来的，实现信息交换、资源共享、可互操作和协作处理的系统



### 操作系统功能

操作系统的职能是管理和控制计算机系统中的所有硬件和软件资源，合理地组 织计算机工作流程，并为用户提供一个良好的工作环境和友好的接口。

{% hint style="info" %}
对于后续的深入学习的内容有着很强的提示和概要性作用。

几乎涵盖后续所有章节的本质作用和意义。
{% endhint %}

#### 处理机管理

在单道作业或单用户的情况下.处理机为一个作业或一个用户所独占，对处理机的管理 十分简单。但在多道程序或多用户的情况下，要组织多个作业同时运行，就要解决处理机分 配调度策略、分配实施和资源回收等问题。

#### 存储管理

存储管理的主要工作是对存储器进行分配、保护和扩充的管理。

* 内存分配
* 存储保护
* 内存扩充

#### 设备管理

* 通道、控制器和输入输出设备的分配和管理
*   设备独立性。

    设备管理应为用户提 供一个良好的界面，而不必去涉及具体的设备特性，以使用户能方便、灵活地使用这些设备。

#### 文件管理

上述3种管理都是针对计算机的硬件资源的管理。信息管理(文件系统管理)则是对系统的软件资源的管理。

#### 用户接口（操作系统用户界面）

* 命令控制界面（作业一级的接口）
* 系统调用（程序一级的接口）

### 计算机硬件简介

详见计算机组成原理

#### 基本硬件元素

处理器 存储器 输入输出控制 总线和外部设备

* 用户可编程寄存器
* 控制与状态寄存器

#### 指令的执行与中断

计算机提供的最基本功能是执行指令。任何应用程序都只有通过指令的执行才能得以 完成。执行指令的基本过程分为两步，即处理机从内存读人指令的过程和指令执行的过程。 其中，读指令是根据程序计数器(PC)所指的地址读人，而执行的指令则是指令寄存器(IR) 中的指令。

中断给操作系统设计带来许多好处，首先使得实时处理许多紧急事件成为可能;其次， 中断可以增加处理机的执行效率;另外，中断还可以简化操作系统的程序设计。



### 研究操作系统的几种观点

#### 计算机资源管理者

#### 用户界面的观点

#### 进程管理的观点

上述两种实际上是静态的观点，没有揭示一个程序在系统中运行的本质过程和管理资 源的各种子程序存在的关系。实质上操作系统调用当前程序运行是一个动态过程。



### 小结



### Q\&A

* 什么是操作系统的基本功能
* 什么是批处理、分时、实时操作系统
* 多道程序和多重处理有何区别
* 讨论操作系统可以从哪些角度出发，如何把他们统一起来



