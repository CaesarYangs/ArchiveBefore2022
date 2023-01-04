# Software Design and Architecture



## Fundamental Concepts and Foundations of SA

***

## 软件体系结构

> is a meaningful engineering representation of some software system/application that is to be built, more specifically, play a role of the backbone for any software system/application

从纸上勾勒出如何实现软件的解决方案。 世界级的软件需要世界级的软件体系结构。

\==在纸上勾勒出的，一种具有工程意义的表示方法。 使用组件，组件的联结机制(连接件)进行表示或连接各种机制。==

纸上：设计文档 工程意义：这种表示能够简单的产生代码 （勾勒）表示：对待构建系统的表示方法或解决方案 这种表示方法对系统的表示起到了系统的脊梁柱（backbone）的作用

软件体系结构是一个中间产品。

世界级的产品一定有世界级的架构。

软件体系结构是一种解决方案

软件体系结构应该承载质量检测的方案和价值。

## 组件

### 组件分类

三种组件/结构设计和表示方法：

* 非标准化表示：图文框，方框，箭头来表达组件
* 标准化表示：UML
* 更高层级：数学表示

**硬件组件** 计算设备和环境组件

计算单元 存储单元

**软件组件** 高内聚 低耦合

实体组件 概念组件 数据访问组件 GUI组件

### 连接方式

* 包含
* 依赖
* 关联

### Structural Elements

* Compoents 组成要素（名称，编号，属性，构成责任和功能）
* Connectors 联结机制
* Boundary 边界
* Purpose and Responsibility 责任 功能
* Control/Work Flow Representation 调用关系
* Implementation Algorithm 算法实现
* Constrains 约束

## 软件体系结构的角色

世界级的产品需要世界级的架构，世界级的架构需要世界级的架构师。

\==软件体系结构是一个桥梁的概念。== 体系结构是一个中间产品，从需求到最终产品的构建的桥梁。

**软件体系结构的重要性**

* 决定了一个产品能达到的高度
* 决定了产品的构建难易程度
* 是管理整个软件更新，成本核算的必要工具

商业价值 质量角度 分析

一致性 可修改性

## Representation Cases

## Categories of SA

总体层级 应用层级 系统层级 组件层级 数据层级

## CRC Model

Component Responsibility Collaborator

也称为CRC卡

> Class-responsibility-collaboration (CRC) cards are **a brainstorming tool used in the design of object-oriented software**. They were originally proposed by Ward Cunningham and Kent Beck as a teaching tool, but are also popular among expert designers and recommended by extreme programming supporters.



性能参考模型 服务参考模型 任务参考模型

上下文机制 组件——使用 CRC 卡 将组件变为类图 类图可以和代码进行交互

设计要验证功能场景图 功能模块图 非功能模块图，在此基础上。产生总体结构和一个系统的组件

* **Core Technical Foundation**
  * 性能参考模型 Performance Reference Model
    * 图形界面 业务流程 高内聚低耦合 severance
  * 业务参考模型 Business Reference Model
  * 技术参考模型 Technical Reference Model
    * 总体结构考虑 软件要使得什么计算设备发挥计算潜能
  * 服务参考模型
  * 数据参考模型
* Methodology

### 性能参考模型

* 目标和愿景
* 对用户使用结果的影响（e.g. 数字银行同时服务客户）
* 业务
* 技术结果

#### 应用场景图

Application Scenerio 将设计一个软件系统变为这些基本要素

验证应用场景：将复杂问题利用信息抽象隐藏 层次化维度分解的方法 用最简便的方式表达出来

在不同的使用场景下不同：单机 网络 物联网 etc.

**性能参考模型**——将复杂的系统变为以下维度

* 硬件基础设施（计算环境）：用图文框 方框表示
* 软件基础设施：操作系统 使硬件变为计算场所
* 软件应用系统——使用软件应用系统所要达到的要求
  * 包括：用户界面 外部访问组件 etc.
  * 性能要求：功能性需求 非功能性需求

### 技术参考模型

对硬件环境 软件环境进行进一步说明

访问机制 ：浏览器 or 图形界面窗口 or dos 推送机制 ：web 或单机软件 授权性 访问控制

#### 总体结构图

Enterprise Architechure Model

### 业务参考模型

验证功能性需求

用方框 图文框 单向双向箭头来表示

#### 功能模块图

利用图文框进行相应的功能表示

功能性需求图

### 服务参考模型

#### 业务组件图

从功能模块图衍生过来即可 一个功能模块分为三个软件组件：功能组件+控制组件+主程序组件

系统的概要设计：包括多少组件 分层结构

**Abstract Software System Architecture(SDD Model)**

图形界面 业务逻辑组件 数据访问组件 外部服务集成组件 控制组件：各种功能模块 实现单点登录





\==**软件体系结构实际上就是对组件和连接机制 对待构建系统 做工程上的表达**==

利用中间件的软件组件设计 在纸上勾勒出待构建系统的特征

三大支柱：

* 体系架构：发现 识别 决策一个待构建的系统由多少组成要素构成 决定了软件系统能达到的高度，难易程度，成本核算与任务管理
* 先进的工具：中间件 各种库函数来支撑开发复杂的软件
* 优秀的开发环境：VisualStudio MyEclipse

***

## 组件

组件名称 编号 功能 父类 等 用 CRC 卡表达

每个组件工程意义的设计

**组件的设计** 每个组件需要一个张 CRC 卡：

* 编号
* 名称
* 描述
* 属性值
* 功能
* 所需数据
* 外部接口

SDD 描述的是一张 CRC 卡

组件的发现 设计

### 组件的构成

* **主程序组件**
* 欢迎界面
* 控制组件
* 主程序组件
* GUI boundary 组件
* 业务逻辑组件
* 概念 算法组件
* 数据访问组件

#### 主程序组件

程序的入口点。相当于操作系统 `int main(int argc,char** argv) {}`

`class CMyApp: CWinApp {}` `CFramework` `CDialogCFormView` `CDocument`——构建组件的序列化处理

`class MyApp{ public static void main(String args[]) }`

**主程序组件的 CRC 卡** ![](https://github.com/CaesarYangs/MyPictureHotel/blob/main/JuniorClass/SoftwareStructure/%E6%88%AA%E5%B1%8F2022-03-30%2008.31.08.png?raw=true)

CRC 卡在 cpp 中由两个部分来实现：.h 文件与.cpp 文件

#### 图形用户界面组件GUI

基于 windows 的应用

_什么是基于图形界面的应用？_ 目标是提供易操作易学习易使用的系统，通过**统一化的**、**标准化的**、**各种图形要素组成的**图形用户界面

一种服务系统

* 统一化的图形界面
* 包括以下可视化要素
  * 顶层容器：窗架
  * 各种视窗
  * 菜单条
  * 工具栏
  * 状态栏
* 优点：
  * 提供了易学习易操作 简便的计算机使用处理机制
  * 方便非计算机专业人士进行操作和使用
  * 与具体的硬件设备松耦合
  * 多任务 多线程
  * 标准化，统一化的图形界面
* GUI
  * MFC: GUI 视窗
  * java：AWT Swing
  * ASP.net： 资源编辑器

**需要会识别出常见的图形界面内容和组件** _窗架如何设计？_ _菜单栏如何设计？_ _各种工具栏如何设计？_ button label 文本框 图文框

**图形界面主要元素** ![](https://github.com/CaesarYangs/MyPictureHotel/blob/main/JuniorClass/SoftwareStructure/%E6%88%AA%E5%B1%8F2022-04-06%2009.19.25.png?raw=true)

SDI 模型 MDI 模型

* 基于 Dialog 的应用模式
  * CWinAPP
  * CFrameWnd——顶层组件要继承自
* 基于 Java
  * AWT Swing
  * 顶层容器：JFrame
    * JPanel

#### 业务逻辑组件

**Entity**

## 开发环境和概念

* AppWIzard
* 组件/类 Wizard
* 资源管理器-将外部资源注入系统
* CDC-绘图区对象：将计算机屏幕当做画布
  * CPen：屏幕上绘图的笔
  * CBrush：屏幕上的刷子
  * BitBlt

***

* MFC：微软设计的，基于开发 window 应用的基本类库
* 工程设计：指生成代码框架
* AppWizard：应用程序生成器/导航器
* ClassWizard：类/组件生成器
* 继承 CWinApp
* 窗架继承 CFrameWnd

主要考察风格： 管道 N 层 MVC BS CS

之前的那些组件按照什么方式来组织

* 什么是设计模式和设计风格
* 经典软件设计风格：mvc n 层 管道 基于消息处理机制 分布式 面向服务

软件设计内容维度：

* 层次化表达
* 抽象和信息隐藏
* 分而治之 精细化方法

连接件就是将识别出来的组件进行如何交互和连接

* 基本连接机制
  * 层次化表达机制
  * 包含
  * 依赖
  * 关联
  * 调用
  * 共享
* 复杂连接机制
  * 客户端 服务端协议
  * 连接数据库
  * 非同步广播
  * 管道数据流

1. 什么是设计风格及其重要性
2. 掌握经典的设计风格
3. 在设计的过程中能会使用这样的架构

_什么是设计风格_ 是一种体系架构

* 对重复出现的重复问题的解决方法论 是最佳实践
* 提供了设计的经验 能够重用
* 实现不同层级的抽象
* 采用通用标准化的词汇来进行人员间的交流
* 进行文本化 更好地构建设计文档
* 能够预先将要定义的要素预设到系统中

管道设计风格 过滤器 数据流模式-特殊的管道设计风格

n 层 将组件分为若干个类型 实现图形层与用户层 用户层与业务逻辑层 业务逻辑层与数据访问层的分离 最典型的分布式结构程序设计

### 管道设计风格

* 软件架构中反复出现的一种基本风格是管道架构（也称为管道和过滤器架构）。一旦开发人员和架构师决定将功能拆分为独立的部分，这种模式就随之而来了。大多数开发人员都知道这种架构是Unix终端shell语言（如Bash和Zsh）背后的基本原理。
* 许多函数式编程语言的开发人员将看到语言构造和这种架构元素之间的相似之处。实际上，许多使用MapReduce编程模型的工具都遵循这个基本拓扑结构。虽然这些示例展示了管道架构风格的底层实现，但它也可以用于更高级别的业务应用。
* 管道和过滤器以特定的方式协调，管道通常以点对点的方式在过滤器之间形成单向通信。

[第11章 管道架构风格 - 简书](https://www.jianshu.com/p/3842a4183fd1)
