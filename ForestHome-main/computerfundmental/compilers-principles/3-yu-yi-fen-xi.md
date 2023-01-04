# 3 语义分析



* 编译器前端最后的一个重要部分——语义分析器
* 语义分析器
  * 输入：抽象语法树AST
  * 输出：另一种中间表示
  * 早期编译器会直接生成目标代码
* 编译器前端：
  * **三个核心模块：词法分析器 语法分析器 语义分析器**
  * **两个核心数据结构：记号流 抽象语法树**
  * **编译器的阶段无关性**
* 语义分析的阶段无关性
  * 简化编译器中的任务

## 语义分析的任务

* 语义分析也称为**类型检查**、**上下文相关分析**
* 抽象语法树是由上下文无关文法CFG定义的
* _Q：为什么要叫做上下文无关文法？_\[\[2 语法分析#上下文无关文法 CFG|CFG]]
* 负责检查程序（抽象语法树）的上下文相关的属性：
  * 这是具体语言相关的，典型的情况包括：
    * **变量在使用前进行声明**
    * **每个表达式都有合适的类型**
    * **函数调用和函数的定义一致**
    * ...
  * 检查的属性和语法分析是完全不想关的
* **即：在语法正确的情况下产生的错误**——语法合法，语义不合法
* 语义分析实例
  * **不能仅分析一个局部的信息，需要分析程序上下文的声明和调用情况**
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205161346221.png)

## 语义分析器概念上的结构

![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205161349003.png)

* **输入：抽象语法树+程序语言的语义**——与语法分析阶段类似
* 输出：判断+中间代码（目标代码）
* _**在语义分析阶段后，不能够包含任何的语法和语义等和程序源代码相关的错误**_

## 程序语言的语义

* 传统上，大部分的程序设计语言都采用**自然语言**来表达程序语言的语义
  * 例如“+”运算：要求左右操作数都必须是整形数
  * _在学习一门语言的过程中，更多的还是在学习这门语言的语义_
  * _要真正深刻和全面的理解一门语言，就是要为其书写一个编译器_
* 编译器的实现者必须对于严重的语义规定有全面的理解
  * 主要的挑战是如何能够正确高效的实现

## 语义检查

* **语义检查的最重要部分即——类型检查**
* 此章节讨论的就是利用语义分析器输出yes/no 即是否满足相关的语义规则
* 生成中间代码包含在下个章节
* C--语言：

```cpp
//C--
E ->  n
    | true
    | false
    | E + E
    | E && E
```

```cpp
//类型合法程序：
3 + 4
false && true

//类型非法的程序
3 + true
true + false

//对这个语言，语义分析的任务是：对给定的一个表达式e 写一个函数
type check(e);
//返回表达式e的类型；若类型不合法，则报错
```

* 语义分析的功能子集——类型检查

### 类型检查算法

#### 对表达式的处理

```cpp
enum type(INT, BOOL){};

enum type check_exp(Exp_t e){
    switch(e->kind){
        case EXP_INT:return INT;
        case EXP_TRUE:return BOOL;
        case EXP_FALSE:return BOOL;
        case EXP_ADD: 
            t1 = check_exp(e->left); //递归调用子表达式1
            t2 = check_exp(e->right);  //递归调用子表达式2
            if(t1 != INT || t2 != INT){
                error("type mismatch");
            }else{
                return INT;
            }
    }
}
```

![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205161409958.png)

* **树的后序遍历**
  * 同样通过后续遍历对整个子树的类型进行检查操作
* **将两个子树的和进行合并处理**

#### 对变量声明的处理

```cpp
P ->  D E  //Declear Exp
D ->  T id; D  //自循环
    |
T ->  int
    | bool
    ///////////添加变量声明部分
E ->  n
    | id
    | true
    | false
    | E + E
    | E && E
```

* 符号表类型检查算法
  * 同样是进行递归下降，处理符号表来进行类型检查
  * key -> value
  * **table——符号表**
  * _**check P**_
  * _**check D**_
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205161419434.png)
* 在检查的过程中，即树的遍历过程中填符号表
* 在此基础上进行表达式的检查操作
  * _**check E**_
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205161424371.png)
  * 左边的声明区域作为一张表
  * 在右侧进行递归调用的时候通过查表
  * 也就是将上下文相关信息存储在这张表中

#### 对语句的处理

进一步扩展

```cpp
P ->  D E
D ->  T id; D
    |
T ->  int
    | bool

S ->  id = E
    | printi (E)
    | printb (E)

E ->  n
    | id
    | true
    | false
    | E + E
    | E && E
```

![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205161427265.png)

* 上下文相关文法最重要的就是一张符号表
* 将全部的变量和类别存入符号表中
* _**类型检查通过手工实现的类似递归下降算法的检查操作来进行。**_

## 符号表

* 是上下文相关分析
* 所以符号表在其中起到了非常重要的作用
* 符号表
  * 用来存储程序中的变量相关信息
    * 类型
    * 作用域
    * 访问控制信息
    * ...
  * 必须非常高效
    * 程序中的变量规模会很大
* 符号表的接口

```cpp
ifndef Table_H
define TABLE_H

typedef ... Table_t; //数据结构

//新建一个符号表
Table_t Table_new();
//符号表的插入
void Table_enter(Table_t,Key_t,Value_t);
//符号表的查找
Value_t Table_lookuop(Table_t,Key_t);

endif
```

### 典型数据结构

* 符号表是典型的字典结构 key->value
* 一种数据结构定义
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205161435687.png)
* 符号表的高效实现
  * 为了高效，可以使用**哈希表**等数据结构来实现符号表
    * 查找是 O（1）时间
  * 为了节约空间，也可以使用**红黑树**等平衡树
    * 查找是 O(logn)时间
* **符号表处理作用域的方法**
  * 方法一：一张大表（哈希表）
    * 进入作用域时，插入元素
    * 退出作用域时，删除元素
    * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205161443065.png)
  * 方法二：采用符号表构成的栈
    * 进入作用域时，插入新符号表
    * 退出作用域时，删除栈顶符号表
    * 一路沿着栈从顶到底进行寻找，如果到底仍未发现则直接报错
    * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205302119889.png)

### 名字空间

* 用符号表处理名字空间
  * **每个名字空间用一个表来处理**
  * **语义分析阶段会有好几个不同类型，不同名字空间的符号表**。根据变量类别的不同，到不同的表中进行查找
  * 以 C 语言为例
    * 有不同的名字空间：
      * 变量
      * 标签
      * 标号
      * ...
    * 可以每类定义一张符号表 每一个名字空间用一个符号表处理
    * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205302123301.png)

## 其他问题

* 语义分析中要考虑的其他问题：
  * 类型相容性？
  * 错误诊断？
  * 代码翻译？

### 类型相等（相容性）

* 类型检查问题往往归结为判断两个类型是否相等 `t1==t2?`
  * 在实际的语言中，这往往是个需要谨慎处理的复杂问题
* 示例 1:名字相等 vs 结构相等
  * 对采用名字相等的语言，可直接比较
  * 对采用结构相等的语言，需要递归比较各个域
  * 取决于具体语言的语义要求
* 示例 2:面向对象的继承
  * 需要维护类型间的继承关系

### 错误诊断

* 要给出尽可能准确的错误信息
* 要给出尽可能多的错误信息
  * 从错误中进行恢复
* 要给出尽可能准确的出错位置
  * 程序代码的位置要从前端保留并传递过来
* 编译器按照流水线模式工作 只依赖于前面一个模块的传递结果
  * 只有语义分析做完后，这些错误位置信息才有可能被抛弃

### 代码翻译

* 现代编译器中的语义分析模块，**除了做语义分析外，还要负责生成中间代码或目标代码**
  * 代码生成的过程也同样是**对树的某种遍历**
* _因此，语义分析模块往往是编译器中最庞大也是最复杂的模块。_
  * 对源语言语义的处理都需要在这里完成
  * 语义分析模块必须要完全实现某种语义
