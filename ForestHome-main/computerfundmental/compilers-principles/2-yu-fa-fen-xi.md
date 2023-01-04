# 2 语法分析



> 编译器是一个具有流水线结构的系统。 目前处于前端研究阶段

目前的语法分析只负责语法相关的内容。是编译器前端部分最核心的内容。

## Outline——语法分析器的任务

记号流(token)----->**语法分析器**----->\[\[5 中间表示|中间表示]]：抽象语法树

| 输入        | 语法分析器         | 输出               |
| --------- | ------------- | ---------------- |
| 记号流 token | 语言的语法规则（输入 2） | 抽象语法树 AST (中间表示) |

_目的：分析判断当前程序是否合法？_ 找出语法错误 需要一个判别的标准，即**第二个输入——特定语言的语法规则**

* **语法树构建**
  * 语法分析阶段会将代码放入内存中，构造辅助的辅助结构进行存储，即：语法树。
    * 语法树要包含原始代码中全部信息。

_**语法分析是编译器中第一个做切分的阶段：要把源程序中所有的信息传递到语法树当中。**_

**Outline**

* 数学理论：上下文无关文法（CFG）
  * 描述语言语法规则的数学工具-是描述程序语言的语法
* 自顶向下分析
  * 递归下降分析算法（预测分析算法）
  * LL 分析算法
* 自底向上分析
  * LR 分析算法
* 语法制导翻译
* 生成抽象语法树

| 源程序 | 编译器前端                        | 中间表示 |
| --- | ---------------------------- | ---- |
| 源程序 | 词法分析器（记号） 语法分析器（抽象语法树） 语义分析器 | 中间表示 |

![](https://img2020.cnblogs.com/i-beta/1520604/202003/1520604-20200304220845909-1802515227.png)

* **编译器中常用的方法可以分为自顶向下的和自底向上的。顾名思义，自顶向下的方法从语法分析树的顶部（根结点）开始向底部（叶子结点）构造语法分析树，而自底向上的方法则从叶子结点开始，逐渐向根结点方向构造。这两种分析方法中，语法分析器的输入总是按照从左向右的方式被扫描，每次扫描一个符号。**
* **错误处理**
  * 词法错误，包括标识符、关键字或运算符拼写错误和没有在字符串文本上正确地加上引号。
  * 语法错误，包括分号放错地方、花括号，即“{”或“}”，多余或缺失。
  * 语义错误，包括运算符和运算分量之间的类型不匹配。例如，返回类型为 void 的某个 Java 方法中出现了一个返回某个值的 return 语句。
  * 逻辑错误

## 上下文无关文法 CFG

### 乔姆斯基文法体系

* 历史背景：**乔姆斯基文法体系**
  * 为研究自然语言构造的一系列数学工具
  * 用数学建模这种形式化的方法来研究人类的自然语言 用更加严谨的数学思维研究自然语言
  * [乔姆斯基谱系 - 维基百科，自由的百科全书](https://zh.wikipedia.org/wiki/%E4%B9%94%E5%A7%86%E6%96%AF%E5%9F%BA%E8%B0%B1%E7%B3%BB)
  * 文法表示：
    * 0-型文法：任意文法（无限制文法或短语结构文法）
    * 1-型文法：上下文有关文法
    * 2-型文法：上下文无关文法——语法结构
    * 3-型文法：正规文法——词法结构 正则表达式
  * ![](https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Chomsky-hierarchy.svg/380px-Chomsky-hierarchy.svg.png)

### 文法体系的形式化

* 自然语言中的典型句子结构
  * 主语 谓语 宾语
  * 名词 动词 名词
* **形式化**
  * **非终结符**：大写字母 {S,N,V}
  * **终结符**：小写字母 {s,t,g,w,e,d}
  * **开始符号**：S
  * **产生式规则集合**：P

```
S -> N V N
N ->  s 羊
    | t 老虎
    | g 草
    | w 水
V ->  e 吃
    | d 喝
```

### CFG 的数学定义

这些形式化的目的是产生一个规则的集合-形式化表达：G {N,T,S,P}

* **上下文无关文法是一个四元组：G = (T, N, P, S)**
  * T 终结符集合
  * N 非终结符集合
  * P 一组产生式规则 (每条规则由一个非终结符推出)
  * S 唯一的开始符号 (非终结符)

**实例** 算数表达式

_如何区分终结符和非终结符？_ 非终结符都是大写字母；终结符都是小写字母 往往可以通过产生式规则的式子看出四元组的主要内容

## 推导

* 给定文法 G，从 G 的开始符号 S 开始，用产生式的右部（推导式）替换左侧的**非终结符**。
* **此过程不断重复，直到不出现非终结符为止**
* 最终的串称为**句子**

类似于代数的多项式拖式计算形式

### 最左和最右推导

**最左推导：每次总是选择最左侧的符号（最左侧的非终结符）进行替换** **最右推导：每次总是选择最右侧的符号（最右侧的非终结符）进行替换**

### 语法分析

_Q：什么是语法分析_

* **语法分析概念**
  * 给定文法 G 和句子 S，语法分析要回答的问题：_在给定的文法规则中是否存在对句子 s 的推导？_
  * 上文的产生式规则可视为一套文法 G
* **语法分析器的任务**
  * 判断语言的语法规则 G 中是否存在对句子 S 的推导——输出 Boolean
  * 回答 Yes/No
  * _**语法分析器的第一步任务就是根据推导，来判断文法是否满足约定的语法规则**_

| 输入                                          | 语法分析器         | 输出                       |
| ------------------------------------------- | ------------- | ------------------------ |
| 记号流                                         | 语言的语法规则（输入 2） | 抽象语法树(中间表示)              |
| 句子 S                                        | 上下文无关文法 G     | boolean：在 G 中是否存在对 S 的推导 |
| 所以当前到这里学习的内容是关于返回输出 bool 结构信息的，而非最终结果抽象语法树。 |               |                          |

## 分析树

* _Q：分析树如何生成？_
  * 由推导的过程生成，开始符号作为根节点。
  * 即将之前的推导过程变为一棵语法分析树。
* **推导**
  * 在任何一个推导进行的过程当中，**每一个剖面都是一步推导的中间过程。**
  * 将每一个**剖面**称为一个**边界（Boundary）**。_**——类似于 BFS 的推导方式**_
  * 最终的边界即整棵树的叶子结点，所有的叶子结点构成了一个句子。
  * 至此，一个给定的 CFG 就成功推导成了一棵分析树（利用自顶向下方法）
* **语法分析树**
  * **已经看到了最终输出的抽象语法树 AST 的雏形了，其本质就是来源于 CFG 的格式。**
  * **推导可以表达为树形结构**
    * 推导顺序和生成树形态无关
    * 即：和推导所用的顺序无关（最左，最右，其他）——最终都可以推出相同的分析树
    * 不管使用何种推导顺序，最终都能获得相同的分析树（此时只 mention 左/右推导 与优先级问题无关 这是两个独立的问题）
  * **特点：**
    * 树中的每个内部节点代表非终结符
    * 每个叶子节点代表终结符（类似 B 树）
    * 每一步推导代表如何从双亲结点生成它的直接孩子节点。（替换与边界扩展的过程）
    * 与词法分析器中的\[\[1 词法分析#NFA to DFA：子集构造算法|子集构造算法思想]]高度一致
* _Q：如何进行树的遍历?_
  * **分析树最终的叶子结点会构成一个句子串**
  * **句子相同的情况很可能会源自于两棵不同的分析树**——存在二义性问题
  * _**分析树的含义取决于树的后序遍历**_——类比表达式求值
  * _**后序遍历的顺序决定了文法的意义。**_

### 二义性文法

* _Q：什么是二义性文法？_
  * 给定文法 G，如果存在句子 s,它有两棵不同的分析树，那么 G 是二义性文法。
  * 从编译器角度，二义性文法存在问题：
    * 同一个程序有不同的含义（如乘法和加法的优先级问题）
    * 因此程序的运行结果不唯一
* \==**Q：如何消除二义性文法？**==
  * **对文法结构进行改进，而不是源程序**
  * _Q：什么是文法的结构？_
    * 即同一含义的文法 不同的 CFG 表示形式
  * **解决方法：文法的重写**
    * 要具体问题具体分析
    * 对文法结构进行改造 保证文法不变的情况下不会产生二义性

#### 表达式文法的重写

要具体问题具体分析 不是所有的情况都能一定遇到解决方法。

* **算数表达式运算二义性消除**
  * 本质：文法要能够区分出运算的优先级
  * _**写成有复合结构的多层次文法的算式表达**_
  * 写出这种表达式的前提即已经规定好了整个运算的优先级和策略
  * 如果没有规定好优先级是无法决定改写后的文法的次序的
    * 位置越高的文法越在后面计算
    * 位置越底层的文法则优先级越高
    * **本质上是根据树的后序遍历来规定的优先级次序**
  * 优先级更高的运算往往会处在分析树更下层的位置，从而实现先运算。
* **左递归-每次选择最左符号进行替换**——对应最左推导
  * [左递归 - 维基百科，自由的百科全书](https://zh.wikipedia.org/zh-sg/%E5%B7%A6%E9%81%9E%E6%AD%B8)
  * 左递归情况分析
  * _Q：为什么左递归的尽头是无穷？_
* **右递归-每次选择最右符号进行替换**——对应最右推导

```cpp
//左递归文法 陷入无穷循环
E -> E + T
   | T

T -> T * F
   | F

F -> num
   | id
```

* 文法的重写结果：_**构造带有复合结构的多层次文法**_
  * T - Term 项
  * F - Factor 因子
  * O - Atom 终结符原子
* 最左推导
  * 左结合对应左递归
  * 在纯加法运算中：此时保证了加法的左结合性
  * **递归的先后（左右）决定了运算的左结合性还是右结合性**
* **相当于规定好第一步推导的优先级 后面每一步都会遵循这个优先级前进**
* 且这样生成的分析树也保持了加法运算的左结合性

## Top-Down 分析思想

### 自顶向下分析算法——算法 1

整个前面一节语法分析树的**推导**，其实就是自顶向下的运算替换过程。

* _**Q：自顶向下语法分析的本质：给定文法 G 和句子 s，回答 s 是否能够从 G 推导出来？**_

_实现语法分析器内部功能所需要的算法和数据结构_

* 基本算法思想：从 G 的开始符号出发，随意推导出某个句子 t，比较 t 和 s——**回溯**
  * 若 t = s，则成功
  * 若 t != s，仅说明**本次**的推导不成功 仅说明本次随意的猜测思路错误
    * 则回溯，从新计算一个 t1 ，再比较
  * 对每一个非终结符进行回溯处理
  * **回溯过程：**
    * 1 栈内容回滚
    * 2 游标回溯
* 因为这是从开始符号出发推出的句子，因此称为自顶向下分析。
  * 对应于**语法分析树**自顶向下的构造顺序：从根节点，到中间节点最后到叶子结点
* 关键内容：游标
  * 游标的前进和后退对应着匹配的成功前进以及失败而回溯
* 关键步骤：将右部**逆序压栈**——_C 语言中的函数参数，都是逆序压栈处理方法_
  * [C 语言中函数参数的入栈顺序](https://blog.csdn.net/x\_iya/article/details/52088838)
  * 即从右向左压栈
  * 以满足最左推导的条件
  * 相当于用栈显示实现树的后续遍历过程

整个算法就是一个在栈的基础上实现的的一个简单 DFS 过程

```cpp
tokens[g,d,w];//存放词法分析器生成的记号流
i = 0;  //游标 指向下一个要匹配的字符
stack = [S]; //S 是开始符号 stack 存放所有终结符和非终结符
while(stack!=[]){
    if(stack[top] is a terminal t){  //栈顶元素是终结符
        if(t == tokens[i++]){  //token与当前数组游标处是否相等
            pop();
        }else{
            backtrack();  //栈和游标 i 同时回滚到之前一个状态
        }
    }else if(stack[top] is a nonterminal T){  //栈顶元素是非终结符
        pop();
        push(the next right hand side of T); //将其右部压入栈（最左推导要逆序压栈）
    }
}
```

* 一直要到栈顶元素为终结符时才进行相应的比较
* 在比较的过程采取回溯
* 分析失败的情况
  * tokens 被吃空了，但栈不为空
  * 某一个元素的产生式右部都被压入与检查过了 结果没有匹配的 才会报错 语法有误——体现出效率很低

**算法分析**

* 算法需要用到回溯
  * 给分析效率带来问题——不可能扫描串一遍就完成匹配
* 就这部分而言，编译器必须高效
  * 需要编译上千万行的内核程序
* 因此，需要线性时间的算法
  * **避免回溯**
  * 引出递归下降分析算法和 LL(1) 分析算法

**重新思考-改进的思考方案🤔**

* 用_**前看符号**_避免回溯，在回溯前指导该过程，尽量避免浪费
* 可以做到_**线性**_时间规模（与输入串规模相同）

### 递归下降分析算法——算法 2

**也被叫做预测分析算法** 是一种自顶向下分析算法的改进-改进效率（线性时间） _**本质：一种典型的纯手工实现的语法分析器**_

* 利用**前看符号**避免回溯
* 也称为预测分析
  * 分析高效（线性时间）
  * 容易实现（方便手工编码）
  * 错误定位和诊断信息准确
  * 被很多开源和商业编译器采用：GCC4.0 LLVM
* 算法基本思想：
  * **每个非终结符构造一个分析函数**
  * **用前看符号知道产生式规则的选择**
  * 所谓前看符号就是“上帝视角”，程序员手工列出所有的 `if` 条件路径
  * _这也就是为什么这种编译器能够非常精确地报错-类型+位置+原因_
* **采用了分治（Divide & Conquer）的思想**
  * 将问题分解为多个子问题
  * 经过几次递归操作，下沉到解决很多个相同的平凡子问题
  * 将一个句子的判断问题分解为多个小句子的判断问题
  * 则只需手动写出几个**分析函数**，再通过每次的调用来判断是否能表示其中的每个字符

```cpp
parse_S(){
  parse_N()  //分而治之
  parse_V()
  parse_N()
}  //每一个非终结符构造一个分析函数

//对于每个非终结符构造一个分析函数

parse_N(){
     token = token[i++]  //相当于光标后移
     //与所有终结符进行对比 看能否匹配上
     if(token==s || token == t || token==g || token==w){
         return;
     }else{
         error("...")
     }
}
```

***

**一般的算法框架：**

```cpp
x -> b11 b12 ... b1i
    |b21 b22 ... b2i
    |b31 b32 ... b3i
    |...
```

* 最一般的表示形式
  * 单个字母既可以是非终结符，也可以是终结符
  * 如果一个符号内部包含的是终结符：则直接比较其是否匹配
  * 如果一个符号内包含的是非终结符：则递归调用非终结符的 parse 处理函数，直到终结符为止。

```cpp
parse_X()
    token = nextToken()
    switch(token){  //前看符号选择效应 分治的开始
        case ... : beta 11 ... beta 1i
        case ... : beta 21 ... beta 2j
        case ... : beta 31 ... beta 3k
        ...
        default:error("...")
    }
```

* $\beta ij$可以是终结符，也可以是非终结符
  * 如果是终结符，则需要和 token 比较
  * 如果是非终结符，则需要递归调用 `parse(Y)` 进行解决

```cpp
case a:
    if(token == a){
        return;
    }else if(token 是非终结){
         parse(NEXT)
    }else{
        error("...")
    }
```

调用的最终结果是将所有的非终结符运算到终结符即可完成

```cpp
void A(){
    选择一个产生式，A->X1,X2,X3...Xk
    for(i=1 to K){
        if(Xi 是非终结符){
            调用过程 Xi()
            return
        }else if(token == Xi){
            return
        }else{
            error("...")
        }
    }
    
}
```

***

**对算数表达式的递归下降算法分析**

* **通过对文法的分析来解决前看符号会遇到的多分支选择的问题**——本质还是之前的手动消除二义性的处理方法。
* 出现问题
  * 出现了都可以实现的路径分支
  * 则意味着又会出现回溯 与开始思想相矛盾

```cpp
parse_E(){
    parse_T();
    token = tokens[i++]
    if(token == num){
        ?//E+T or T
        //消除左递归的方法 发现使用上的问题 再次出现回溯
        while(token == +){  //看是否都是+T+T+T的形式
            parse()
            token = tokens[i++]  //利用while循环连续读入处理重写的文法表达式
        }
    }else{
        error("...")
    }
}

parese_T(){
    //完全相同 看是否符号*F*F*F的形式
}
```

***

语法分析器自动生成-针对某一个语法生成-得到一个可以运行的代码 _如何生成这一个可以自动完成语法分析的工具？_

### LL(1)

_**本质：典型的一种自顶向下的自动生成的语法分析器**_ **一种自左向右分析，自顶向下分析模式**

**自动生成：** 类比词法分析器

| 声明式的规范          | 自动结构 | 语法分析器   |
| --------------- | ---- | ------- |
| CFG（对语法的严格数学描述） | 分析工具 | 生成语法分析器 |

* 大大减轻程序员的负担和出错的概率
* 一旦有了严格意义上的数学规则表示，则能够进行自动处理

> LL 分析器是一种处理某些上下文无关文法的自顶向下分析器。因为它从左（Left）到右处理输入，再对句型执行最左推导出语法树（Left derivation，相对于 LR 分析器）。能以此方法分析的文法称为 LL 文法。 本文中将讨论表格驱动的分析器，而非通常由手工打造（非绝对，参看如 ANTLR 等的 LL(x) 递归下降分析器生成器）的递归下降分析器。 一个 LL 分析器若被称为 LL(k) 分析器，表示它使用 k 个词法单元作向前探查。对于某个文法，若存在一个分析器可以在不用回溯法进行回溯的情况下处理该文法，则称该文法为 LL(k) 文法。这些文法中，较严格的 LL(1) 文法相当受欢迎，因为它的分析器只需多看一个词法单元就可以产生分析结果。那些需要很大的 k 才能产生分析结果的编程语言，在分析时的要求也比较高。

* ANTLR：LL(1)
* YACC：
* bison
* SMLYACC

语法分析器的输入：记号流 & 语言的语法规则 **目前学习到的知识还只能够满足语法分析器输出 Y/N,还远不能生成一棵语法树**

利用递归下降后还存在一定的性能问题，还需要更好的算法来解决。

* _**从左(L)向右读入程序，最左(L)推导，采用一个(1)前看符号**_。 ——从左至右，自顶向下
  * 分析高效（线性时间）
  * 错误定位和诊断信息准确
  * 有很多开源或商业的生成工具
    * ANTLR...
* 算法基本思想：
  * \==表格驱动的分析算法==
* 此处的前看符号仍然是当前读取的那一个 token 字符
* _Q：何为最左推导？_
  * 在推导的过程当中，每次总是选择当前串中最左的非终结符进行替换
* _Q：前看符号的作用？_
  * 辅助判断用符号，避免回溯
* _Q：什么是表驱动分析算法_
  * 下图极其重要，其是作为整个 LL(1)分析算法的主要架构：主要区别即分析表
  * 分析表能够指导分析栈的工作，避免回溯，高效处理
  * _Q：分析表如何指导分析栈的工作？_
  * _Q：如何生成分析表？_

![](https://img-blog.csdnimg.cn/img\_convert/5c8bf64f0eefe16822c7efe2dc838943.png)

* 分析栈：自顶向下语法分析回溯时所使用。面对的困难就是回溯的出现。
* 分析表：给了回溯过程中或分析栈整个的工作情况。**语法分析器可以通过查询分析表来查询到下一步的工作。**

#### 算法主干部分

* 将自顶向下分析算法修改
* 使用一张分析表来指导 next 的工作内容 避免回溯 时间效率极大提高
* 本质上是使用栈实现了一个非递归版本的递归下降分析遍历过程
* **实现了一个非递归版本的树的遍历过程——即一种深度优先搜索的非递归实现**

```cpp
tokens[g,d,w];//存放词法分析器生成的记号流 不必显示出现
i = 0;
stack = [S]; //即分析栈
while(stack!=[]){
    if(stack[top] is a terminal t){  //栈顶元素是终结符
        if(t == tokens[i++]){
            pop();  //意味着栈顶元素的分析已经完成了
        }else{
            error();
            //backtrack();  //栈和游标 i 同时回滚到之前一个状态
            //不需要回溯 直接报错 error 即可
        }
    }else if(stack[top] is a nonterminal T){  //栈顶元素是非终结符
        pop();
        //push(the next right hand side of T); 之前的next是一种盲目的方式
        push(the next **CORRECT** table[N][T]);  //匹配成功 回溯就不需要
    }
}
```

* _Q：为什么会有回溯？_——next
  * `push(the next right hand side of T)` 盲目地将下一个放入算法中，而不顾其内涵
  * 盲目地 `next` 就是整个算法低效率回溯的重点——从此处解决
  * **解决方法**
    * 利用分析表将 correct 带入分析环节，要么一次分析成功，要么不会成功直接报错。
    * 每次压入栈的都是 correct 项，根本没有回溯部分；若没有 correct 则直接原地报错。
    * 焦点转移——如何控制 correct 选择

_Q：问题转移——什么是 correct 选择，我们怎么选择 correct 选择？_

#### LL(1)分析表

**表驱动的 LL 分析器架构** 核心：分析表 为了消除回溯

| N\T | s | t | g | w | e | d |
| --- | - | - | - | - | - | - |
| S   | 0 | 0 | 0 | 0 |   |   |
| N   | 1 | 2 | 3 | 4 |   |   |
| V   |   |   |   |   | 5 | 6 |

* 行：代表非终结符（句子，名词，动词）
* 列：代表终结符的所有字符——**其实就是 LL(1)此时的前看符号**
* 中间数值：代表下一个可以接收的产生式规则字符的编号
* 取值方式：类似于行列式和矩阵的方式 当前所在的非终结符+当前输入的终结符确定一个表中元素
  * 行取值：就是取当前分析的终结符类别
  * 列取值：取得是当前分析的句子串的首字母
  * 前看符号：就是当前分析的句子串首字母，该游标就充当前看符号
* 结果：
  * 要么分析成功
  * 要么分析失败 很直接 不会有回溯

#### 基本近似情况

**FIRST 集**

* _**开始集，即每个句子开始的字符。什么都不用管，只看此时句子的第一个；只算每个非终结符**_
  * _每个串可能的开始字符是什么_
  * _每个产生式产生的句子的第一个字母是什么_
* **定义：**
  * FIRST(N) = 从非终结符 N 开始推导得出的句子开头的所有可能终结符集合。
  * 对所有情况做枚举 进行推导
* **FIRST 集的意义**
  * FIRST(α)被定义为可从推导得到的串的首符号的集合，其中α是任意的文法符号串。
  * 在对产生式右部的查看过程中，只需要看到右部中下一个输入符号是否包含在其中某个之一，就能够成功消除递归
  * **FIRST(α)被定义为可从推导得到的串的首符号的集合，其中α是任意的文法符号串。**
* **第一个版本计算公式（近似）**
  * 对 N -> a...（以一个终结符开头), FIRST(N) U= {a}
  * 对 N -> M...(以一个非终结符开头), FIRST(N) U= First(M) （相当于递归解决问题即减而治之思想）
  * 当一个句子比如 gdw，但此时的 FIRST 集中没有 g ，则直接报错，分析失败。
  * **即：FIRST 集不动点算法代码**
    * 轮询算法
    * 对于每条产生式规则，进行一次首字符判断。

```cpp
foreach(nonterminal N){
    FIRST(N) = {}  //对非终结符的初始化开始符号都是空集
}

while(some set is chaning){  //如果有集合发生了变化，则需要进行下一轮
    foreach(production p:N->b1,b2....bn){
        if(b1 == a...){  //两个重要计算公式
            FIRST(N) U= {a}
        }
        if(b1 == M){
            FIRST(N) U= FIRST(M) //递归 减而治之
        }
    }
}
```

* 判断条件：只要集合发生了变化，则循环继续前进
* 注意❗️：永远并入的是 FIRST 元素，即句子的第一个字符，也就是 FIRST 的名字来由

| N\FIRST(循环轮数) | 0  | 1          | 2          | 3         | 4    | 5    |
| ------------- | -- | ---------- | ---------- | --------- | ---- | ---- |
| S             | {} | {}         | {s,t,g,w}变 | {s,t,g,w} | same | same |
| N             | {} | {s,t,g,w}变 | {s,t,g,w}  | {s,t,g,w} | same | same |
| V             | {} | {e,d}变     | {e,d}      | {e,d}     | same | same |

* 以上这种最为基础的 FIRST 算法只针对于对于每个非终结符 N。也可以被称为 `FIRST_N()` 集

**FIRST\_S 集**

**把 FIRST 集推广到任意串上——`FIRST_S`** _S stands for Sentence or String_

```cpp
FIRST_S(b1...bn){
    FIRST(N), if b1 == N
    {a},      if b1 == a
}
```

* 计算的是每个产生式右部的集合代表着什么
* \==**最终目标结果：每个产生式右部产生一个 FIRST 集**==
* 构造出了 FIRST\_S 集合，就可以根据这些产生式的右部写出分析表。
  * 填表
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205042138158.png)

_为什么要推广到任意串上？_ First\_S

* 最基本的 LL(1)分析算法实现过程
  * **FIRST（每一个非终结符的集合）--->FIRST\_S（每条产生式的集合）--->LL(1)分析表**

***

#### 一般条件下的 LL(1)分析表构造

* **出现冲突**
* **出现空句**
* **出现嵌套**

**分析表冲突**

一个空里填了两个数字，涉及两个数字都能到达目标，需进行两个数字的回溯。

冲突表：

| N\T | s | t | g | w   | e | d |
| --- | - | - | - | --- | - | - |
| S   | 0 | 0 | 0 | 0   |   |   |
| N   | 1 | 2 | 3 | 4,5 |   |   |
| V   |   |   |   |     | 5 | 6 |

* 导致分析规则的不确定性：即会产生回溯
* **冲突检测**
  * 对 N 的两条产生式规则 N->$\beta$和 N->$\gamma$ ，要求$FIRST\_S(\beta)\cap FIRST\_S(\gamma)$ = { }（空集）

***

* **一般意义下的分析表构造**
* 首先研究右侧例子：
  * FIRST\_S(X Y Z)?
    * **一般情况下需要知道某个非终结符是否可以推出空串**——_**解决 空 不可以的问题**_
    * NULLABLE
  * **并且一般需要知道在某个非终结符后面跟着什么符号**——_**解决嵌套问题**_
    * 跟随集 FOLLOW

**NULLABLE 集**

_**——看哪些非终结符会推出一个空串**_

* NULLABLE 的推出是一种归纳集合
* **非终结符 X 属于集合 NULLABLE，当且仅当**：
  * 基本情况： X-> （直接推出空串）
  * 归纳情况： X->Y1 ... Yn (Y1,...,Yn 是 n 个非终结符，且都属于 NULLABLE 集)
  * _**即：只要这个产生式规则推出的式子中存在一个非空终结符，则其不属于 NULLABLE 集**_
  * _**即：属于 NULLABLE 的条件是所有产生式规则的式子都属于 NULLABLE 空集**_

即：只要这个产生式中存在着空集，包括递归条件下也产生空集的情况都属于 NULLABLE 集

**NULLABLE 集合不动点算法**

```cpp
NULLABLE = []   //初始空集 不知道哪个属于

while(NULLABLE is still changing){  //集合在进一步变化
    foreach(production p: X->b){  //从上到下逐个遍历每一条产生式p
        if(b == e){   //属于空集 1
            NULLABLE U= {X}
        }
        if(b == Y1...Yn){   //必须是n个都是NULLABLE
            if(Y1 属于 NULLABLE && ... && Yn 属于 NULLABLE){
                NULLABLE U= [X]   //将这条产生式加入NULLABLE集合
            }
        }
    }
}
```

| NULLABLE | 0  | 1     | 2     | 3 |
| -------- | -- | ----- | ----- | - |
|          | {} | {Y,X} | {Y,X} |   |

* _Q：什么是不动点算法？_
  * 集合不停地变化直到不动为止
  * 与上一层的算法相同：只要两轮之间集合发生了变化，则需要继续进行迭代

**FIRST 集合的完整计算公式**

* 加入如果属于空集的情况
  * NULLABLE 集合的目的是为了求解 FIRST 集合
* _**考虑透视效果——如果不能看到当前的第一个元素，即 first 元素，则“透视”到下一个产生式中。**_
* **基于归纳的计算规则：**
  * 基本情况：
    * `X->a`
      * `FIRST(X) U= {a}`
  * 归纳情况
    * `X->Y1 Y2 ... Yn`
      * `FIRST(X) U= FIRST(Y1)`
      * `if Y1 属于 NULLABLE,FIRST(X) U= FIRST(Y2)`
      * `if Y1,Y2 属于 NULLABLE,FIRST(X) U= FIRST(Y3)`
      * `...一直到某一个 Y 不为空，则计算在此时终止`

**完整的 FIRST 集不动点算法**

```cpp
foreach(nonterminal N){
    FIRST(N) = {}
}

while(some set is changing){
    foreach(production p:N->b1...bn){  //逐步分析每条产生式规则
        foreach(bi from b1 to bn){ 
            if(bi == a...){  //是终结符的情况
                FIRST(N) U= {a}  //把这个终结符加到当前FIRST中来
                break  //这一条产生式规则计算结束
            }
            if(bi == M...){
                FIRST(N) U= FIRST(M)  //如果是空集 则进行透视 分析下一个符号
                if(M is not nullable){  //如果不是空集
                    break  //直接跳出循环 进行计算
                }
            }
        }
        
    }
}
```

[FIRST 集计算示例](https://www.aliyundrive.com/drive/folder/62346be67dd1ccc6982c4bfe8c31eb6eaac1da87)

**FOLLOW 集**

_**——即跟随集。看每一个非终结符的后面可以跟着哪些符号，看有哪些终结符可以跟在它后面**_

* **FOLLOW 集意义**
  * 对于非终结符号 A,FOLLOW(A)被定义为可能在某些句型中紧跟在 A 右边的终结符号的集合。
* 只有遇到非终结符的情况下才会将 temp 集赋值到真正的 FOLLOW 集中

**FOLLOW 集不动点算法**

```cpp
foreach (nonterminal N){  //给每一个非终结符初始化
    FOLLOW(N) = {}
}

while(some set is changing){
    foreach(production p:N->b1 ... bn){
        temp = FOLLOW(N)  //临时集合
        foreach(bi from bn downto b1){  //逆序考虑 从最后一个符号开始考虑
            if(b1 == a){  //当前符号是终结符 则直接加入temp
                temp = {a}
            }
            if(bi == M...){  //当前符号是非终结符 则把temp加到M的跟随集中
                FOLLOW(N) U= temp
                if(M is not NULLABLE){  //如果非终结符M不是空 新的temp就是其开始集
                    temp = FIRST(M)
                }else{
                    temp U= FIRST(M)  //若M为空 则新的temp并上它的开始集 累计增大
                }
            }
        }
    }
}
```

**完整的 FIRST\_S 集**

* 针对每个产生式来计算
* 将 FIRST 集的计算推广到整个串上

**完整的 FIRST\_S 集合算法**

```cpp
foreach(production p){
    FIRST_S(p) = {}
}

calculte_FIRST_S(production p:N->b1...bn){
    foreach(bi form b1 to bn){  //1 to n递增
        if(bi == a...){  //终结符情况
            FIRST_S U= {a}
            return;
        }
        if(bi == M...){  //非终结符情况
            FIRST_S(p) U= FIRST(M)  //首先将这个非终结符的开始符号加入
            if(M is not NULLABLE){
                return
            }else{
                continue  //如果为空则继续向后透视判断
            }
        }
    }
    FIRST_S(p) U= FOLLOW(N)  //若前面所有的都没有返回 即前面都为空 则把其跟随集加入
}
```

![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205052124867.png)

**构造**

**构造 LL(1)分析表** ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205052125359.png)

#### LL(1)分析器

* **工具读入文法，并最终输出一个 LL(1)分析表**
* **语法分析驱动代码在 LL(1)分析表的指导下做语法分析。输出 yes/no**
  * 仅仅是对自顶向下算法稍作修改

```cpp
tokens = []
i = 0
stack = [S]
while(stack != []){
    if(stack[top] is a terminal t){
        if(t==tokens[i++]){
            pop()
        }else{
            error
        }
    }else if(stack[top] is a nonterminal T){
        pop()
        push(table[T,tokens[i]])  //通过分析表，不会再涉及到回溯 作为表驱动算法的核心数据结构
    }
}
```

#### LL(1)分析算法的冲突处理

* LL(1)分析表的某些表项包含两个或更多的元素
* 当使用这样的分析表分析时还是会产生回溯，会带来效率的问题

**可以通过适当的变换，把这些冲突去除掉，有效扩大能够使用 LL(1)分析算法的范围**

* **消除左递归**——文法的重写
  * 根本问题是重复部分由于左递归产生
  * 新产生的文法属于右递归
  * 在一部分情况下，使用 LL(1)时第一步就是消除左递归
  * 任何有左递归存在的文法都不是 LL(1)文法 存在两个情况相交 出现冲突
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205052134474.png)
* **提取左公因子**——文法的重写
  * 提取两个产生式的相同公共部分

## Bottom-Up 分析算法

* _**Q：自底向上语法分析的本质：给定文法 G 和句子 s，回答 G 是否能够从 s 推导出来？**_
* **语法分析器的实现方法**
  * 给出上下文无关文法 CFG 作为声明式规范，通过自动生成器生成语法分析器
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205062101230.png)
* **回顾 LL(1)**
  * 前看符号（即当前读取的 token）的作用是查 LL(1)分析表
  * 缺点：
    * **分析的文法类型有限**
    * **往往需要文法的改写——分析表中不能出现冲突**
* **自底向上分析算法**
  * 研究其中也是最广泛应用的一类
  * **LR 分析算法（移进-规约算法）**
    * 算法运行高效
    * 有现成工具可用
  * 这也是目前最广泛的一类语法分析器自动生成器中采用的算法
    * YACC,bison,CUP,C#yacc, etc.
  * 能分析更多的文法
  * 也不需要文法更多的改写和处理

### 自底向上分析的基本思想

* 从左至右读入
* **每读入一个字符，看这个串是否能够规约**
  * 从产生式的左侧到右侧是推导（替换）
  * 从产生式的右侧到左侧是规约 Reduce
* 利用输入串，对其部分不断进行规约，即不断进行收缩
  * 如果成功则会最终收缩到开始符号，则说明语法分析成功；否则说明一定包含语法错误
  * **是一种最右推导的逆过程**
* 我们可以将自底向上语法分析过程看成将一个串 w“归约”为文法开始符号的过程。在每个归约(reduction)步骤中，一个与某产生式体相匹配的特定子串被替换为该产生式头部的非终结符号。
* 根据定义，一次归约是一个推导步骤的反向操作（回顾一下，一次推导步骤将句型中的一个非终结符号替换为该符号的某个产生式的体)。因此，自底向上语法分析的目标是反向构造一个推导过程。
* **所以整个推导实际上是一个最右推导**
  * 对输入进行从左到右的扫描，并在扫描过程中进行自底向上语法分析，就可以反向构造出一个最右推导。
* LR 文法
  * 直观地讲，只要存在这样一个从左到右扫描的移人-归约语法分析器，它总是能够在某文法的最右句型的句柄出现在栈顶时识别出这个句柄，那么这个文法就是 LR 的。
* _Q：为什么使用 LR 语法分析器？_
  * **通用**
    * 对于几乎所有的程序设计语言构造，只要能够写出该构造的上下文无关文法，就能够构造出识别该构造的 LR 语法分析器。确实存在非 LR 的上下文无关文法，但一般来说，常见的程序设计语言构造都可以避免使用这样的文法。
  * **高效**
    * LR 语法分析方法是已知的最通用的无回溯移人一归约分析技术，并且它的实现可以和其他更原始的移入-归约方法一样高效。
  * **错误发现**
    * 一个 LR 语法分析器可以在对输人进行从左到右扫描时尽可能早地检测到错误。
  * **适用性强**
    * 可以使用 LR 方法进行语法分析的文法类是可以使用预测方法或 LL 方法进行语法分析的文法类的真超集。一个文法是 LR(k)的条件是当我们在一个最右句型中看到某个产生式的右部时，我们再向前看个符号就可以决定是否使用这个产生式进行归约。这个要求比 LL()文法的要求宽松很多。

### 点记号

* **点记号**
  * 为了方便标记语法分析器已经读入了多少输入，我们可以引入一个点记号
  * **点号的左边是已经读入的**
  * **右边是剩余的输入。不一定合法**
  * 点记号能够明确地表示出处理是否成立以及处理的进度
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205062108376.png)
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205062110558.png)
  *
* **非常类似自顶向下的分析栈的逆运算**
  * 自顶向下的情况下，将栈顶元素弹出，压入其右部
  * 自底向上正好相反
    * 将栈上的右部弹出，压入其左部
* _Q：点记号的左部是什么数据结构？_
  * 分析栈
  * 每次都在点记号的左边，即栈顶的某一个产生式的右部做规约（收缩）
* **生成一个逆序的最右推导** 需要两个步骤
  * 移进：移进一个记号到栈顶上
  * 规约：规约栈顶上的 n 个符号（某产生式的右部）到左部的非终结符
    * 对产生式 A-> b1...bn
      * 如果 bn ... b1 在栈顶上，则弹出 bn...b1
      * 压入 A
* \==**Q：核心问题-如何确定移进和规约的时机？**==

### LR(0)

#### 算法思想

* 从左 L 向右 R 读入程序，最右 R 推导，不用前看符号决定产生式的选择（0 个前看符号）
  * 优点：容易实现
  * 缺点：能分析的文法有限
* _Q:为什么总需要一个开始规则 0 号规则？_
  * 为了方便起见 引入最开始的一个开始符号 S‘
  * 保证 S‘不出现在任何一个产生式的右部
  * 将文件描述符显示化 显示表示分析结束的情况
* 点记号的意义
  * 左侧是已经读入的
  * 右侧是没有读入的
    * 即此时期待读入的内容都在点记号的右部（稍微向后看一点）
* 项目：每个加点记号的句子
* 项目集：一个整体的项目构成的状态

![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202204251450301.png)

* 实际上就是沿着自动机的边，走了一圈再回溯到结束的过程——若能到结束状态则结束
* **利用了一个有记忆能力的自动机来帮助进行构造判断。**
  * 即在每一个项目上看它的后继是什么样的状态
  * 并行地去走所有的情况
* 点记号的出现：左侧表示已经读入和处理（规约）的部分；右侧表示未读入的部分（期待被读到的字符部分）
  * 稍微向后看一小部分
* 箭头👉表示刚好此时向右读了一部分
* 当点记号到达产生式规则的最后部分时，意味着此时可以进行规约操作了
  * 在栈上将右侧弹出去，将左侧压入进来

#### LR(0)分析表

* 分析表意义
  * 将整个的状态机利用分析表的形式存储起来
  * 很类似于词法分析自动生成器中的\[\[1 词法分析#转移表|转移表]]
* 分析表字母表示
  * shift
  * reduce
  * GOTO 函数用于定义一个文法的 LR(0)自动机中的转换。
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202204251456018.png)

#### LR(0)分析算法

```cpp
stack = []  //压入两个平凡符号
push($)  //$:end of file
push(1)  //1:initial state
while(true){
    token t = nextToken()  //读入下一个记号 实际上是正在输入的符号
    state s = stack[top]  //读取栈顶情况
    if(ACTION[s,t] == "si"){  //si是指shift到第i个状态
        push(t);push(i)
    }else if(ACTION[s,t] == "rj"){  //reduce到第j个状态
        pop(the right hand of production "j:X->Beta")  //将第j个产生式的右部弹出
        state s = stack[top]  //栈顶状态就暴露出一个自动机的编号
        push(X);push(GOTO[s,X])  //查找GOTO表代表的符号
    }else{
        error(...)
    }
}
```

#### LR(0)分析表构造算法

* 是一个工作表算法
* 本质上是一个 BFS 搜索框架 然后附加了对每个节点放入 ACTION 和 GOTO 表的判断

```cpp
C0 = closure(S‘ -> ·S$)  //最初始产生式的项目
SET = {C0}  //初始状态放入自动机集合
Q = enQueue(C0)  //入队 队列属于工作表作用 看还有哪些状态没有计算过
while(Q is not empty){  //当Q不为空
    C = deQueue(Q)  //拿出一个C 项目集的某个初始状态
    foreach(x属于(N U T)){ //算法核心
        D = goto(C,x)  //对每一个终结符和非终结符 都要引出一条边导出的新集合
        if(x 属于 N){
            ACTION[C,x] = D  //满足判定要求 放入ACTION表
        }else{
            GOTO[C,x] = D  //满足判定要求 放入GOTO表
        }
        if(D 不属于 SET){  //D为新生成的内容 这个if负责进行图的广度优先搜索BFS
            SET U= {D}  //存储到底一共有多少个状态 属于是一种新生成的状态
            enQueue(D)
        }
    }
}
```

* goto 计算每一条边能导出到什么状态
  * 在同一个当前的初始条件下，查看有多少出边，且这每条出边能够导向状态机的哪个状态
  * _**本质上相当于将 LR（0）状态图看做一张图，计算每个节点的广度优先搜索 BFS**_

**goto & closure**

* closure 的意义是如果当前点记号后面有非终结符，则需要再将这个非终结符的产生式右部的情况加入整个闭包中。

```cpp
goto(C,x){
    temp = {}
    foreach(C‘s item i:A -> b·x y){
        temp U= {A-> bx·y}
    }
    return closure(temp)
}

closure(C){  //加入所有点号后面有非终结符的情况进去
    while(C is still changing){
        foreach(C‘s item i: A->b·B y){
            C U= {B->...}
        }
    }
}
```

### SLR 分析算法 Simple-LR

* 是对于 LR 分析算法的改进
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205091337909.png)
* 通过两个表来决定移进和规约的时机
* ACTION 表
* GOTO 表
* LR（0）分析算法
  * 从左(L)向右读入程序，最右(R)推导，不用前看符号来决定产生式的选择（0 个前看符号）
  * 实际上是逆向的最右推导
  * LR（1）中的前看符号 1 指的不是输入的符号，而是其计算的 FIRST 集
  * 优点：容易实现
  * 缺点：能分析的文法有限
* LR（0）分析算法的缺点
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205091341699.png)
  * **问题 1:错误定位**
    * **导致无谓的分析 导致的错误发现的时机太晚**
    * 解决：化简一些分析表的表项 并显著减小表的规模
    * 分析过程有很多无用的步骤
    * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202206041827379.png)
  * 问题 2:冲突
    * LR（0）分析表中可能包含冲突
    * 可能冲突-SR 冲突即移进规约冲突
    * 移进规约冲突处理不好会发生回溯
    * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202206041830995.png)
* **SLR 分析算法**
  * 和 LR(0)分析算法基本步骤相同
  * **仅区别于对规约的处理**
    * 对于状态 i 上的项目 `X -> a·`
    * 仅对 `y 属于 FOLLOW(X)` 添加 `ACTION[i,y]` rk
    * **仅对属于 FOLLOW 集中的 y 进行规约**
    * 可以把一些表中的项删掉 本质上是对 LR(0)分析算法的优化调整
* SLR 算法的本质
  * 是对 LR(0)算法进行了更加细致和精确的优化工作。
  * 分析的过程会更加精确

### LR(1)分析算法

> “规范 LR”方法，或直接称为“LR”方法。它充分地利用了向前看符号。这个方法使用了一个很大的项集，称为 LR(1)项集。

* SLR 分析算法的思想
  * 不要盲目地做规约
  * 基于 LR(0)，通过求出 FOLLOW 集，来决定是否执行规约动作
  * 优点：
    * 有可能减少需要规约的情况
    * 有可能去除需要移进-规约冲突
  * 缺点：
    * 仍然有冲突的可能
* LR(1)
  * 再次在 LR(0)的基础上进行改进
  * 对前看符号的判断更加精确

#### LR(1)项目集

* 一个**二元组**：`[X->a·b,a]` 含义：
  * a 在栈顶上
  * 剩余的输入能匹配 ba
  * 有可能此时的 a 要比向前看 FOLLOW 集更小，对于判断更加精确
* 当规约 `X ->ab` 时
  * 把 `reduce by X -> ab` 填入 `ACTION[s,a]`
* LR(1)项目的构造
  * 其他的和 LR(0)相同，仅闭包的计算不同
  * 引入一个开始符号集 FIRST\_S
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205091405618.png)
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205091411065.png)
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205091412317.png)
* 整个的前看符号闭包计算中，最重要的环节是 0 号开始状态。这一个状态的前看符号集计算成功了，后面的只需要不断继承即可。
* 在手算的过程中需要多步推导，而在拟定的闭包计算算法中可以一步到位计算 FIRST\_S
* 但可能会出现如上图所示的句子冗余，其仅仅是前看符号不同。
* 为了统一处理这种冗余，引入 LALR 算法。但会增加一部分的移进规约冲突。
* _Q：前看符号在 LR(1)中指导什么？_
  * 何时进行规约。——即满足条件进行规约，否则移进

#### LALR 分析算法

> “向前看 LR”,或称为“IALR”方法。它基于 LR(0)项集族。和基于 LR(1)项的典型语法分析器相比，它的状态要少很多。通过向 R(0)项中小心地引入向前看符号，我们使用 LALR 方法处理的文法比使用 SLR 方法时处理的文法更多，同时构造得到的语法分析表却不比 SLR 分析表大。在很多情况下，LALR 方法是最合适的选择。

* 把类似的项目集进行合并
* 需要修改 ACTION 表和 GOTO 表，以反映合并的效果
* 核心思想：对 LR(1)算法得到的分析表进行尽可能的压缩，在不影响分析效果的前提下尽可能减小占用的空间
* 可能会导致一些移进规约冲突的出现、
* **LALR 思想：我们可以寻找具有相同核心(core)的 LR(1)项集，并将这些项集合并为一个项集。**
  * 一个核心就是当前正处理的文法的 LR(0)项集，一个 LR(1)文法可能产生多个具有相同核心的项集。
* _LALR 分析器(这种分析器可以处理左递归的文法)_

### 对二义性文法的处理

* 二义性文法无法使用 LR 分析算法分析
* 不过有积累二义性温阀很容易理解，因此，在 LR 分析器的生成工具中，可以对它们特殊处理
  * 优先级
  * 结合性
  * 悬空 else

#### 优先级和结合性

* 如果能给分析器一些提示的话，二义性文法也能够进行解决
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205091417195.png)

#### 提取左因子

* 提取左公因子是一种文法转换方法，它可以产生适用于预测分析技术或自顶向下分析技术的文法。
* 当不清楚应该在两个 A 产生式中如何选择时，我们可以通过改写产生式来推后这个决定，等我们读人了足够多的输入，获得足够信息后再做出正确选择。

***

[FIRST Set in Syntax Analysis - GeeksforGeeks](https://www.geeksforgeeks.org/first-set-in-syntax-analysis/)

[Construction of LL(1) Parsing Table - GeeksforGeeks](https://www.geeksforgeeks.org/construction-of-ll1-parsing-table/)

**概念辨析**

* 终结符
* 非终结符
* 产生式规则

### 小结

* 自顶向下和自底向上语法分析器的构造可以使用和文法 G 相关的两个函数 FIRST 和 FOLLOW 来实现。**在自顶向下语法分析过程中，FIRST 和 FOLLOW 使得我们可以根据下一个输入符号来选择应用哪个产生式。** 在恐慌模式的错误恢复中，由 FOLLOW 产生的词法单元集合可以作为同步词法单元。
* 左递归和二义性文法都不是 LL（1）文法
* 所有的 LR 语法分析器都按照这个方式执行，两个 LR 语法分析器之间的唯一区别是它们的语法分析表的 ACTION 表项和 G0TO 表项中包含的信息不同

***

## 语法制导翻译

* **在语法分析过程中穿插上语义动作——语法制导翻译**
* 语法分析器要输出的应该是一棵抽象语法树
* **编译器在做语法分析的过程中，除了回答程序语法是否合法外，还必须完成后续工作**
  * 可能的工作包括（但不限于）
    * 类型检查
    * 目标代码生成
    * 中间代码生成
    * _这是一个多阶段的内容和工作_
  * 这些后续的工作一般可以通过**语法制导的翻译**完成

### LR 分析中的语法制导翻译

* 在 LR 算法的进行过程中进行语法制导翻译
  * **在产生式的右部被识别出来后 准备 reduce 前 执行该条语句对应的语义动作**
  * 按照某一条产生式做规约的时候，会自动生成一个产生式的值或运算式

```cpp
if (action[s,t] == "ri"){  //reduce i
    ai // 在产生式的右部被完全识别出来并销毁前 运行该产生式右部对应的语义动作
    pop(bi)
    state 's' = stack[top]
    push(x)
    push(goto[s‘,X])
}
```

* 实例：计算表达式的值

```cpp
o:E -> E+E {E = E1 + E2}
1:    |n   {E = n}
```

* **对应后续遍历的序**
  * 先计算最递增的然后自底向上构造整个表达式的值
* 加下来看 Yacc 中的实现

### 语法制导翻译的实现原理

* 语法制导翻译的基本思想
  * **给每条产生时规则附加一个语义动作**
    * 一个代码片段
  * **语义动作在产生式“规约”时执行**
    * 即由右部的值计算左部的值（此时递归地向上来计算这个值）
    * 以自底向上的技术为例进行讨论 自顶向下的技术与此类似
* 在分析栈上维护三元组：`<symbol,value,state>`
  * symbol 和 state 是在普通的 LR 分析器中都存在的
* 其中 symbol 是终结符或非终结符，value 是 symbol 所拥有的值，state 是当前的分析状态
* **分析栈上记录了从初始状态出发所有转移和走过的步骤**
* 整个分析栈的变化方式
* 算数符号优先级：左结合按照规约来处理
* 语法制导翻译实例
  * _**在做语法分析的同时，利用三元组在栈上进行操作。进行完成语法分析的同时，根据产生式右部规定的语义代码，生成相应的制导翻译结果。**_
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202206040946369.jpg)
  *

## 抽象语法树

* 整个编译器前端的部分
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205091457809.png)

### 回忆分析树

* 分析树编码了句子的推导过程
* **分析树**
  * 注意：这些节点要占用额外的存储空间
* _Q：本质上，这里的那些信息是重要的？_
  * 指对于理解句子含义没有影响的地方可以被省略
* **对于表达式而言，编译只需要知道运算符和运算数**
  * **优先级、结合性等已经在语法分析部分处理掉了**
* 对于语句、函数等语言其他构造而言也一样

### 抽象-语法树

* 对分析树的浓缩
  * 抽象语法树就像是一杯 espresso
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205091502751.png)
* **具体语法和抽象语法**
  * 具体语法是语法分析器使用的语法
    * 必须适合于语法分析，如各种分隔符、消除左递归、提取左公因子等等
  * 抽象语法是用来表达语法结构的内部表示
    * **线代编译器一般都采用抽象语法作为前端（词法语法分析）和后端（代码生成）的接口**
* 抽象语法树数据结构
  * 在编译器中，为了定义抽象语法树，需要使用**实现语言来定义一组数据结构**
    * 和实现语言密切相关
  * 早期编译器有的不采用抽象语法树数据结构
    * _直接在语法制导翻译中生成代码 加入额外的代码作为动作_
      * 早期情况下语言的复杂性较低
      * 且早起计算机的内存是瓶颈 无法存储过大的抽象语法树
    * 但现代编译器一般采用抽象语法树作为语法分析器的输出
      * 更好的系统支持
      * 简化编译器的设计
* **抽象语法树的整体流程**
  * 首先给定一个抽象文法（可能包含二义性）
  * 为这组抽象文法定义一组数据结构
  * 这组数据结构能够用来编码具体的程序/具体文法

#### 数据结构的定义

* 目的：表达产生式右部的数据结构表示

```c
enum kind{E_INT, E_ADD,E_TIMES};
struct Exp{
    enum kind kind;
};
struct Exp_Int{
    enum kind kind;  //通过这几个kind的类别，能够直接识别出来计算的类型
    int n;
};
struct Exp_Add{
    enum kind kind;
    struct Exp *left;
    struct Exp *right;
};
struct Exp_Times{
    enum kind kind;
    struct Exp *left;
    struct Exp *right;
}
```

**构造函数定义：**

```c
//完成了其中一个树节点的定义与初始化赋值
struct Exp_Int *Exp_Int_new(int n){
    struct Exp_Int *p = malloc(sizeof(*p));
    p->kind = E_INT;
    p->n = n;
    return n;
}

//声明新的内存区块 赋左右孩子的值 再确定根节点的计算符号 返回整棵子树的根节点
struct Exp_Add *Exp_Add_new(struct Exp *left,struct Exp *right){
    struct Exp_Add *p = malloc(sizeof(*p));
    p->kind = E_ADD;
    p->left = left;
    p->right = right;
    return p;
}
```

#### 手工构造法

```c
//3+4*5
int tree1(){
    struct Exp *e1 = Exp_Int_new(2);
    struct Exp *e2 = Exp_Int_new(3);
    struct Exp *e3 = Exp_Int_new(4);
    struct Exp *e4 = Exp_Times_new(e2,e3);
    struct Exp *e5 = Exp_Add_new(e1,e4);
}
```

* **实际上就是在用手工构造的方式，自底向上的构造一棵抽象语法树。**
* 第一个域起到了明确标记类型的意义
* AST 上的操作在某种意义上都可以被翻译为对树的一种遍历的过程。
* **从表达式到栈式计算机 Stack 的编译器**

```cpp
List all;
void compile(E e){
    switch(e->kind){
        case E_INT : emit(push e->n); return;
        case E_ADD:
        case E_TIMES:
        //
        default:
            error("compiler bug");
    }
}
```

#### 抽象语法树的自动生成

* _**将抽象语法树的生成作为语法制导翻译中的一个动作**_
* 如何做到自动生成
* 如何做到在语法分析的过程中自动顺便完成抽象语法树的生成工作
* 将其变为一个语法制导的方案或语义动作
* **在 LR 分析中生成抽象语法树**
  * **在语法动作中，加入生成语法树的代码片段**
    * 原来的语法动作是直接做表达式的求值
    * 片段一般是语法树的“构造函数”
  * **在产生式规约的时候，会自底向上构造整棵树**
    * 从叶子结点到根结点
    * 而且会自动进行抽象，从而保证生成的直接就是抽象语法树 利用 `$1 $3 $$`

```cpp
E -> E + E {$$ = Exp_Add_new($1,$3)}
    |E * E {$$ = Exp_Times_new($1,$3)}
    |n     {$$ = Exp_Int_new($1)}
```

* **源代码信息的保留和传播**
  * 抽象语法树是编译器前端和后端的接口
    * 程序一旦被转换成抽象语法树，则源代码即被丢弃
    * 后续的阶段只处理抽象语法树
  * **所以抽象语法树必须编码足够多的源代码信息**
    * 例如，它必须编码每个语法结构在源代码中的位置（文件、行号、列号等）
      * 这样，后续的检查阶段才能精确的报错
      * 或者获取程序的执行刨面
  * 抽象语法树 AST 必须仔细设计
  * 抽象语法树的设计和表达能力决定了后续编译器的实现和表达能力以及后续任务实现的难易程度
* 示例：位置信息

```cpp
struct postition_t{
    char *file;
    int line;
    int colume;
};

struct Exp_Add{
    enum kind kind;
    Exp *left;
    Exp *right;
    struct position_t from;  //新增的两个结构
    struct position_t to;
};
```

* 不要把抽象语法树这样的概念绝对化
* 设计一个好的抽象语法树是一门艺术而不是一门科学
* 尽管里面有一些指导原则可以使用，但是最根本的原则是要根据你正在设计和实现的编译器的具体目的来合理的选择和设计抽象语法树的具体表示，使得它能够编码你所需要的所有信息，完成所需的目标。

## Q\&As

* **WHY FIRST & FOLLOW?**
  * The construction of a predictive parser is aided by two functions associated with a grammar G. These functions, FIRST and FOLLOW, allow us to fill in the entries of a predictive parsing table for G, whenever possible. Sets of tokens yielded by the FOLLOW function can also be used as synchronizing tokens during panic-mode error recovery.
  * **The conclusions is, we need to find FIRST and FOLLOW sets for a given grammar so that the parser can properly apply the needed rule at the correct position.**

```md
作者：木灵Rin  
链接：https://www.zhihu.com/question/290986894/answer/1700522304  
来源：知乎  
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。  
  

首先我们需要知道，LL(1)分析的核心问题是：**「如何通过只向前看1个词法单元来唯一确定与当前句子匹配的产生式」**，它对于文法的要求是「在1个向前看符号下无推导二义性」，换句话说，就是在LL(1)预测分析表中，（以非终结符号为行标，向前看终结符号为列标）**每一个格子里最多填入一个产生式**。否则，我们在分析过程中就无法通过1个向前看符号来确定需要使用哪一个产生式。

我们找到每个产生式推导得出的句子的最左边的**1个**终结符号来指导我们填入这些产生式。如某产生式的最左侧是终结符号，那么我们直接将其填入对应的格子中。如某产生式的最左侧是非终结符号，我们就计算它的FIRST集合，用这个FIRST集合中的终结符号来**代表这个非终结符号**，从而指导产生式的选择。这样就会产生两个问题：

1.  某些非终结符号有复数个左部相同的产生式，如F→Ab|Ac，这就导致了上述**每一个格子里最多填入一个产生式**的要求不能满足。因此我们提出了**左公因子提取算法。**
2.  不是所有的非终结符号都能够成功以LL(1)方式求出FIRST集合。比如有产生式A→Ac|d，其中的A无法求出FIRST集合，原因是存在左递归。因此我们提出了**左递归消除算法。**

借由这两个算法，我们可以将绝大多数的产生式填入预测分析表，但还有相当一部分**可能推出空串的**非终结符号，它们的FIRST集合中包含ε，而ε不能用来代表非终结符号来与词法单元匹配，毕竟没有任何一个词法单元代表ε。

那么怎么确定“什么时候使用ε产生式”呢？我们可以通过考察这个非终结符号在文法中的后继终结符号来决定是否使用ε产生式。这就是FOLLOW集合起作用的地方：如果我们看到的向前看符号属于目前非终结符号的FOLLOW集合，就表明这个非终结符号可能推出了一个空串。但是，**如果某一个可以推出空串的非终结符号F的FIRST集合中存在某终结符号a，且a同时存在于FOLLOW集合中，那么此时我们就无法确定a到底来源于F本身还是F后继的内容，也就是说，这时的预测分析表中的(F, a)格子中同时存在了ε产生式和另一个F产生式，这就产生了「向前看1的二义性」**。换句话说，在这种情况下，我们不知道是否该选用ε产生式，或者另一个可以若干次推出“以a为开头的句子”的产生式。

所以LL(1)文法不允许**能够推出空串的非终结符号**的FIRST集合与FOLLOW集合有相交。
```

* **WHY FIRST？** We saw the need of backtrack in the [previous article](https://www.geeksforgeeks.org/introduction-to-syntax-analysis-in-compiler-design/) of on Introduction to Syntax Analysis, which is really a complex process to implement. There can be easier way to sort out this problem:

If the compiler would have come to know in advance, that what is the “first character of the string produced when a production rule is applied”, and comparing it to the current character or token in the input string it sees, it can wisely take decision on which production rule to apply.

Let’s take the same grammar from the previous article:

S -> cAd A -> bc|a And the input string is “cad”.

Thus, in the example above, if it knew that after reading character ‘c’ in the input string and applying S->cAd, next character in the input string is ‘a’, then it would have ignored the production rule A->bc (because ‘b’ is the first character of the string produced by this production rule, not ‘a’ ), and directly use the production rule A->a (because ‘a’ is the first character of the string produced by this production rule, and is same as the current character of the input string which is also ‘a’).\
Hence it is validated that if the compiler/parser knows about first character of the string that can be obtained by applying a production rule, then it can wisely apply the correct production rule to get the correct syntax tree for the given input string.

* **WHY FOLLOW？** The parser faces one more problem. Let us consider below grammar to understand this problem.

A -> aBb B -> c | ε And suppose the input string is “ab” to parse.

As the first character in the input is a, the parser applies the rule A->aBb.

```
      A
    / |  \
  a   B   b
```

Now the parser checks for the second character of the input string which is b, and the Non-Terminal to derive is B, but the parser can’t get any string derivable from B that contains b as first character.\
But the Grammar does contain a production rule B -> ε, if that is applied then B will vanish, and the parser gets the input “ab”, as shown below. But the parser can apply it only when it knows that the character that follows B in the production rule is same as the current character in the input.
