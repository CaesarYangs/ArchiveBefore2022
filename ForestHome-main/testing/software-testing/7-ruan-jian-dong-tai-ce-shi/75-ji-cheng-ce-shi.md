# 7-5集成测试

## 集成测试概念

* 将所有模块按照设计要求，逐步装配成高层的功能模块，并进行测试，直到整个软件成为一个整体
* 检验软件单元之间的接口关系，并把经过测试的单元组成符合设计要求的软件
* 集成测试验证程序和概要设计说明的一致性，任何不符合该说明的程序模块行为都应该进行标记并上报
* 集成测试是发现和改正模块接口错误的重要阶段
* \==**Q：为什么需要集成测试**==
  * 1.在把各个单元模块连接起来时，穿越模块接口的数据是否会丢失：
  * 2.一个单元模块的功能是否会对另一个模块的功能产生不利的影响；
  * 3.各个子功能组合起来，能否达到预期要求的父功能；
  * 4.全局数据结构是否有问题
  * 5.单个模块的误差积累起来，是否会放大， 从而达到不能接受的程度；

## 集成测试时机

* 理论上
  * 单元测试之后
* 实际上
  * 单元和集成测试同步进行
* **依据概要设计文档**
* 集成测试例子——按照操作流程进行的集成测试
* 集成测试中的一个主要问题：
* _Q：执行失败了，是不是一定是这个模块出现了问题？_
  * 不一定。因为是对整个系统进行测试。一个模块有问题，同样也需要对其他模块进行检查。
  * 有可能问题的根源出在其他模块
* 可以按照系统执行流程和需求进行测试
  * 通常建议按系统执行流程。更加有条理，能验证整个流程是否正确

## 集成测试模式

* 把模块组装起来有两种方法：
  * **渐增式集成模式与非渐增式集成模式**

### 非渐增式测试模式

* **非渐增式测试模式：** 先分别测试每个模块，再把所有模块按设计要求放在一起结合成所要的程序，如大棒模式
* 非渐增式测试模式
* 采用大棒集成方法先是对每一个子模块进行测试（单元测试阶段)，然后将所有模块一次性的全部集成起来进行集成测试
* 因为所有的模块一次集成的，**所以很难确定出错的真正位置、所在的模块、错误的原因。**
* 这种注并不推荐在任何系统中使用，适合在规模较小的应用系统中使用。

### 渐增式测试模式

* **渐增式测试模式：** 把下一个要测试的模块同已经测试好的模块结合起来进行测试，测试完以后再把下一个应该测试的模块结合进来测试。——_**每次增加一个模块**_
* 将下一个要测试的单元同已经测试的单元集合起来然后再测试。_**边连接，边测试**_
* 渐增测试方法：
  * 自顶向下
    * 深度
    * 广度
  * 自底向上

#### 自顶向下测试步骤

* 以主控模块作为测试驱动模块，把对主控模块进行单元测试时引入的所有桩模块用实际模块替代
* 依据所选集成策略（深度优先或广度优先），以及新模块的选择原则，每次用一个实际单元替换一个被调用的桩模块，并开发该单元需要的桩模块
* 每集成一个模块的同时立即进行测试，排除组装过程中可能引进的错误，如果测试发现错误，则要在修改后进行回归测试
* 判断组装测试是否完成，没有完成则转到第二步循环进行，直到集成结束
* 自顶向下法(Top-down Integration)- 按深度方向组装
* 自顶向下法(Top-down Integration)-按广度方向组装
* **优点：功能可行性可以较早得到验证**
* **缺点：测试高层模块时，低层采用桩程序替代，无法反映实际情况**

#### 自底向上测试的步骤

* 开发一个测试驱动模块， 由驱动模块控制最底层模块的并行测试
* 用真实模块代替驱动模块，与它已经通过测试的下属模块组装成为完成更大功能的新模块群
* 判断程序组装的过程是否已经达到主模块如果是，则结束测试，否则从第一步循环热行直到组装结束
* 自底向上法(Bottom-up Integration)
* **优点：不用开发桩模块**
* **当最后一个模块尚未测试时，被测软件系统的雏形还未被显示**

#### 混合策略

* **对软件结构中较上层，使用的是“自顶向下”法；对软件结构中较下层，使用的是“自底向上”法，两者相结合**

#### 三明治集成方法

* 三明治集成方法(Sandwich Integration)
  * 优点：它将自顶向下和自底向上的集成方法有机地结合起来， 不需要写桩程序因为在测试初自底向上集成已经验证了底层模块的正确性。
  * 主要缺点：在真正集成之前中间层每一个独立的模块没有完金测试过。
* 改善的三明治集成方法
  * **改进的三明治集成方法，不仅自两头向中间集成， 而且保证中间层每个模块得到单独的测试，使测试进行得比较彻底**

### 对比两种主要集成测试模式

* 非渐增测试需要更多工作量
* 非渐增测试发现错误晚
* 非渐增测试不易进行错误定位
* \==**渐增测试使用已测试过的模块取代非渐增中所需的驱动或桩模块，可以帮助程序获得更多检验**==

***

单元测试与集成测试的区别： 测试对象不同。单元测试对象是实现了具体功能的程序单元；集成测试对象是概要设计规划中的模块及模块间的组合。 测试方法不同。单元测试中的主要方法是基于代码的白盒测试；集成测试中主要使用基于功能的黑盒测试。 测试时间不同。集成测试晚于单元测试。、 测试内容不同。单元测试主要是模块内程序的逻辑、功能、参数传递、变量引用、出错处理及需求和设计中具体要求方面的测试；集成测试主要验证各个接口、接口之间的数据传递关系，及模块组合后能否达到预期效果。 单元测试与系统测试的区别： 单元测试输入白盒测试，从开发者的角度出发，关注的是单元的具体实现、内部逻辑结构和数据流向；系统测试属于黑盒测试，从用户角度出发，证明系统已满足用户的需要。 单元测试使问题及早暴露，便于定位解决，属于早期测试；系统测试是一种后期测试，定位错误比较困难。 单元测试允许多个被测单元同时进行测试；系统测试时基于需求规格说明书的。 ———————————————— 版权声明：本文为CSDN博主「zhang\_xiaoyi」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。 原文链接：https://blog.csdn.net/zhang\_xiaoyi/article/details/79041145