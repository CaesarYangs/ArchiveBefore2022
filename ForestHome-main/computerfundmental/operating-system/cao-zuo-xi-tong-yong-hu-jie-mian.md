# 操作系统用户界面



## Intro

用户界面是操作系统的重要组成部分。用户界面负责用户和操作系统之间的交互。即用户通过用户界面向计算机系统提交服务需求，计算机通过用户界面向用户提供用户所需要的服务。

从两类用户出发：被服务者（使用和管理计算机应用程序的用户）+开发者

操作系统为普通用户，管理员用户和开发者提供不同的用户界面。

命令控制界面+系统调用

* 作业的概念
* spooling系统
* 两个接口

## 一般用户输入输出

### 作业

在一次应用业务处理过程中，从输入开始到输出结束，用户要求计算机所做的有关该次业务处理的全部工作称为一个作业。

**作业步** 作业由不同的顺序相连的作业步组成。作业步是在一个作业的处理过程中计算机所做的相对独立的工作。一般来说，每一个作业步产生下一个作业步的输入文件。

**作业的组成** 作业是一个比程序更广的概念。 作业 = 程序 + 数据 + 作业说明书

系统通过作业说明书控制文件形式的程序和数据，使其执行操作。

**作业组织** 批处理系统中，作业是抢占内存的基本单位。批处理系统以作业为单位把程序和数据调入内存以便执行。

数据+程序 => 完成用户要求的业务处理工作 作业说明书 => 生成作业控制块JCB

**作业说明书** 作业的基本情况 作业控制描述 资源要求描述

作业说明书主要用于批处理系统中。

**JCB** 登记作业要求的执行情况，预计执行时间和优先级等。分配资源和控制作业中的程序和数据的编译，链接，装入，执行。

### 一般用户输入输出

1. 联机输入方式
2. 脱机输入方式
3. 直接耦合方式
4. **spooling系统**
5. 网络联机

#### spooling系统

即 外围设备同时联机操作

多台外围设备通过通道或DMA和主机外存连接起来。作业的输入输出过程由主机中的操作系统控制

**缓冲区--->输入井** spooling系统的输入方式既不同于脱机方式，也不同于直接耦合方式。

* 在系统输人模块收到作业输入请求信号后，输入管理模块中的读过程负责将信息从输入装置读入缓冲区。
* 当缓冲区满时，由写过程将信息从缓冲区写到外存输入井中。读过程和写过程反复循环，直到一个作业输人完毕。
* 当读过程读到一个硬件结束标志后，系统再次驱动写过程把最后一批信息写入外存并调用中断处理程序结束该次输入。然后为该作业建立作业控制块JCB，从而使输入井中的作业进入作业等待队列，等待作业调度程序选中后进入内存。

## 接口1-命令控制界面

**操作系统的命令控制界面就是用来组织和控制作业运行的。**

_即：命令窗口——终端_

操作系统为用户提供两个接口。一个是系统为用户提供的各种命令接口，用户利用这些操作命令来组织和控制作业的执行或管理计算机系统； 另一个接口是系统调用， 编程人员使用系统调用来请求操作系统提供服务，例如申请和释放外设等类资源、控制程序的执行速度等。

#### 脱机控制

脱机控制方式利用作业控制语言来编写表示用户控制意图的作业控制程序，也就是作业说明书。作业控制语言的语句就是作业控制命令。不同的批处理系统提供不同的作业控制语言。

#### 联机控制

联机控制方式不同于脱机控制方式，它不要求用户填写作业说明书，系统只为用户提供一组通过键盘或其他操作方式输人的命令。用户使用系统提供的操作命令和系统会话，交互地控制程序执行和管理计算机系统。 其工作过程是，用户在系统给出的提示符下输入特定的命令，系统在执行完该命令后向用户报告执行结果；然后，用户决定下一步的操作。

## 接口2-系统调用

**系统调用是操作系统提供给编程人员的唯一接口。编程人员利用系统调用，在源程序一级动态请求和释放系统资源，调用系统中已有的系统功能来完成那些与计算机硬件部分相关的工作以及控制程序的执行速度等。** 因此，系统调用像一个黑箱子那样，对用户屏蔽了操作系统的具体动作而只提供有关的功能。

_事实上，命令控制界面也是在系统调用的基础上开发而成的。_

* 为了提供系统调用功能，操作系统内必须有事先编制好的实现这些功能的子程序或过程。同时，为了保证操作系统程序不被用户程序破坏，一般操作系统都不允许用户程序直接访问操作系统的系统程序和数据。

#### 主要分类

* 设备管理。请求和释放有关设备，启动设备操作等。
* 文件管理。对文件的读写创建和删除等。
* 进程控制。进程是一个在功能上独立的程序的一次执行过程。在进程创建，执行，撤销，执行等待和执行优先级控制等。
* 进程通信。在进程之间传递消息或信号。
* 存储管理。调查作业占据内存区大小，获取作业内存区始地址。
* 线程管理。线程的创建，调度，撤销。

_编程人员如何调用操作系统内部的程序或数据？_

#### 陷阱（trap）处理机构

类似于硬件中断的处理机制。

**使用的原因：OS不能让用户直接访问相应的系统调用子程序，需要极强的封装性和安全性。**

* 当用户使用系统调用时，产生一条相应的指令，处理机在执行到该指令时发生相应的中断，并发出有关信号给处理机制。处理机在收到信号后，启动相关的处理程序去完成该系统调用的所要求的功能。

在系统中为控制系统调用服务的处理机构称为陷阱(trap)处理机构。与此相对应，把由于系统调用引起处理机中断的指令称为**陷阱指令（或称访管指令）**。

**驱动具体步骤**

1. 在操作系统中，每个系统调用都对应一个事先给定的**功能号**，例如0、1、2和3等。在陷阱指令中必须包括对应系统调用的功能号。而且，在有些陷阱指令中，还带有传递给陷阱处理机构和内部处理程序的**有关参数**。
2. 再设计相关的实现各种系统调用功能的子程序编制**入口地址表**，每个入口地址都与相应的系统子程序名对应起来。
3. 然后由陷阱处理程序将陷阱指令中的**功能号与地址表项对应起来**，从而由系统调用功能号驱动有关子程序运行。
4. 由于在系统调用处理结束之后，用户程序还需利用系统调用的返回结果继续执行，因此，在进入系统调用处理之前，陷阱处理机构还需**保存处理机现场**。再者，在系统调用处理结束之后，**陷阱处理机构还要恢复处理机现场**。

另外，在系统发生访管中断或陷阱中断时，为了不让用户程序不直接访问系统程序，反映处理机硬件状态的**处理机状态字(PSW)中的相应位要从用户执行模式转换为系统执行模式**。这一转换在发生访管中断时由硬件自动实现。

**参数传递详细理解** _如何实现用户程序和系统程序之间的参数传递？_

* 陷阱指令自带参数。 只能携带很有限的几个参数进入处理机内部。
* 使用通用寄存器来传递参数。寄存器长度短，无法传递众多参数。
* **在内存中开辟专用堆栈区来传递参数。**

**一般把处理机在用户程序中执行称为用户态，而把处理机在系统程序中执行称为系统态/内核态。**

## 小结

> 本章简要介绍了操作系统的用户界面。操作系统的用户界面是评价一个操作系统优劣的重要指标。操作系统的用户界面包括命令控制界面和编程界面两部分，其中命令控制界面是基于编程界面，也就是系统调用之上开发而成的。

> 操作系统的命令控制界面已从早期的脱机控制方式（批处理系统）和联机控制方式（分时系统)转向多窗口、菜单、按钮以及声控等图形化多媒体方式。命令控制界面的革命与进步是操作系统最显著的变化之一。

> 系统调用是操作系统提供给编程人员的唯一接口。编程人员通过系统调用使用操作系统内核所提供的各种功能。系统调用的执行不同于一般用户程序的执行。系统调用执行是在核心态下执行系统子程序，而用户程序则是在用户态下执行。一般来说，操作系统提供的系统调用越多，功能也就越丰富，系统也就越复杂。

## Q\&A

1. 什么是作业和作业步
2. 作业由哪些部分组成，有什么功能
3. 作业输入方式有几种，特点
4. 阐述spooling系统工作原理
5. OS为用户提供哪些接口，区别？
6. 作业控制方式有哪几种。
7. 什么是系统调用，和一般的用户程序有什么不同。库函数与实用程序有何区别。
8. 为什么说分时系统没有作业的概念。
