# 4 代码生成



## 编译器的中间端和后端

* 整体结构图
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205231333710.png)
  * 抽象语法树——流水线模式结构
  * 翻译 1
  * 中间表示
  * 翻译 2
  * 更多的翻译和中间表示
  * 汇编——后端
* _Q：为什么会有这样的架构？_
  * 编译器是由高级语言翻译到低级语言 如汇编
  * 将高级语言一层一层逐渐向低层级降低 直至最基本的汇编语言
  * 是一个抽象层次逐渐降低的过程 也是编译器的核心任务
* 最简单结构
  * 仅仅涵盖一个翻译的过程，即代码生成。
  * 其最终仅通过一个翻译的过程就输出汇编代码

## 代码生成的任务

* **代码生成的任务**
  * 负责把源程序翻译成目标机器上的代码
  * 最重要的一个目标是保证**等价性**
  * **目标机器**：
    * 可以是真实的物理机器 x86 arm
    * 可以是虚拟机 JVM
* 两个重要子任务：
  * **给源程序的数据分配计算资源**
  * **给源程序的代码选择指令**
    * 首先考虑等价性
    * 其次考虑效率问题

### 给数据分配计算资源

* 源程序的数据：
  * 全局变量、局部变量、动态分配等
* 机器计算资源：
  * 寄存器、数据区、代码区、栈区、堆区
* 根据程序的特点和编译器的设计目标，合理的为数据分配计算资源
  * 例：变量放在内存里还是寄存器里？应该尽量放在寄存器内

### 给代码选择合适的机器指令

* 源程序的代码
  * 表达式运算、语句、函数等
* 机器指令：
  * 算数运算、比较、跳转、函数调用返回
* 用机器指令实现高层代码的语义
  * 等价性
  * 对**机器指令集体系结构（ISA）** 的熟悉

### 路线图

* 研究两种不同的 ISA 上的代码生成技术
  * 栈计算机 Stack——虚拟机
  * 寄存器计算机 Reg——RISC

## 栈计算机

* 栈式计算机在历史上非常流行
  * 上世纪 70 年代有很多栈式计算机
* 但今天已经基本退出历史舞台
  * 效率问题
* 我们还要讨论栈式计算机的原因：
  * 给栈式计算机生成代码是最容易的
  * 仍然有许多栈式虚拟机
    * Pascal P code
    * JVM
    * Postscript
    * ...

### 最简单的栈式计算机 Stack 的结构

* **内存**
  * 存放所有变量
* **栈**
  * 进行运算的空间
  * top 指向当前最后一个有元素的位置
* **执行引擎**
  * 指令的执行

#### 指令集

* 可能的指令集
  * 使用上下文无关文法的形式给出
  * 是 Java 字节码的一个子集（256 条）
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205231409263.png)

```c
//指令的语义：Push
push NUM:
    top++;
    stack[top] = NUM;
```

```c
//指令的语义：load x
load x:
    top++;
    stack[top] = x;
```

```c
//指令的语义：store x
store x:
    x = stack[top];
    top--;
```

```c
//指令的语义：add
add:
    temp = stack[top-1] + stack[top];
    top-=2;
    push temp;
```

* 变量的内存分配伪指令
  * Stack 机器只支持一种数据类型 int，并且给变量 x 分配内存的伪指令式：
    * .int x
  * Stack 机器在装在一个程序时，就会读取伪指令，并且给相关变量分配内存空间

### 栈式计算机的代码生成

#### 递归下降代码生成算法

* 写几个递归函数，采用递归的方式不断下降实现。
* 从 C--到 Stack
  * 这里是代码生成函数
  * 语法分析部分写的是分析函数
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205231423615.png)

**表达式的代码生成**

* 表达式
  * 不变式：表达式的值总在栈顶
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205231424352.png)

**语句的代码生成**

* 语句
  * 体现出来为什么称为递归下降分析算法
  * 要根据每个部分逐层递归生成导出
  * _**根据抽象语法树的观点，是同样在进行后续遍历**_
  * 和语义分析中的顺序是完全一样的 并且可以将这两个部分组合起来
  * 代码生成其实就是对这样一颗抽象语法树的后续遍历
  * 和语义分析的情况极为类似
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205231429579.png)

**类型的代码生成**

* 只生成.int 类型
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205310953741.png)

**变量声明的代码生成**

* 变量声明
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205231437741.png)

**程序的代码生成**

* 整个程序
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205231437859.png)
* 整个实例
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205231438874.png)

### 运行

* _Q：如何运行生成的代码？_
  * 找一台真实的物理机
  * 写一个虚拟机（解释器）
    * 类似 JVM
  * 在非栈式计算机上进行模拟
    * 例“x86 模拟栈式计算机的行为
      * 用 x86 的调用栈模拟栈

## 寄存器计算机

* 寄存器计算机是目前最流行的机器体系结构之一
  * 效率很高
  * 机器体系结构规整
* 机器基于寄存器架构：
  * 典型的有 15、32 或更多个寄存器
    * 所有操作都在寄存器中进行
  * 访存都通过 load/store 进行
    * 内存不能直接运算
    * 运算都需要在寄存器中进行

### 寄存器计算机 Reg 的结构

* **内存**
  * 存放溢出的变量
* **寄存器**
  * 进行运算的空间
  * 假设有无限多个
* **执行引擎**
  * 指令的执行

#### 指令集

* 也是由上下文无关文法给出
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205231447073.png)
* **变量的寄存器分配伪指令**
  * Reg 机器只支持一种数据类型 int，并且给变量 x 分配寄存器的伪指令是：
    * .int x
  * 在代码生成的阶段，假设 Reg 机器上有无限多个寄存器
    * 因此每个声明变量和临时变量都会占用一个虚拟寄存器
    * **把虚拟寄存器分配到物理寄存器中的过程称为寄存器分配**

### 寄存器计算机代码生成

#### 递归下降代码生成算法

* 整体
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205231451529.png)

**表达式代码生成**

* 表达式
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205231452974.png)
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205231454034.png)

**语句的代码生成**

* 语句
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205231457190.png)

### 运行

* 写一个虚拟机（解释器）
* 在真实的物理机器上运行
  * 需进行寄存器分配
