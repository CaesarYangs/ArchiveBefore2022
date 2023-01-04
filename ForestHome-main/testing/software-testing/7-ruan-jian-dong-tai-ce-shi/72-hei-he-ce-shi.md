# 7-2黑盒测试



## 黑盒测试

### 黑盒测试概念

* _Q：什么是黑盒测试？_
* _Q：黑盒测试常用技术是什么？_
* _Q：什么是黑盒测试？_
  * **测试者仅依据需求规约考虑确定测试用例和推断测试结果的正确性。** 又称为功能测试，数据驱动测试
  * 不能替代白盒测试
* **使用黑盒测试发现以下类型错误：**
  * 功能错误或遗漏；
  * 界面错误；
  * 数据结构或外部数据库访问错误；
  * 性能错误；
  * 初始化和终止错误。
* 黑盒测试是从用户观点出发的测试
  * 因此，黑盒测试需要一套产生测试用例的方法。
  * 很多高层测试：确认测试，系统测试，验收测试都采用黑盒测试
* 以有限的测试用例覆盖足够多的“任何情况”
* **关注信息域**
  * 如何测试功能的有效性；
  * 如何测试系统行为和性能；
  * 何种类型的输入会产生好的测试用例；
  * 系统是否对特定的输入值特别敏感；
  * 如何分隔数据类的边界；
  * 系统能够承受何种数据率和数据量
* **测试用例标准**
  * 所设计出的测试用例能够减少为达到合理测试所需要设计的测试用例的总数
  * 所设计出的测试用例能够告诉我们，是否存在某些类型的错误，而不是仅仅指出与特定测试有关的错误是否存在
* 即：设计的测试用例应当覆盖面足够广，能够帮助我们发现尽可能多方面的错误，保证测试的完整性。
  * 黑盒测试只有测试通过和测试失败两种结果。通过测试时其实是表示软件能做什么，而非考虑它的能力如何。
  * 测试人员总是用最简单，最直观的测试用例。

### 黑盒测试常用技术

* 等价类划分方法
* 边界值分析方法
* 因果图方法
* 猜错法

#### 等价类划分方法

* 完全不需要考虑软件的结构，只需要考虑需求规约中的功能要求。
* 把所有可能的输入数据,即程序的输入域划分成若干部分(子集),然后从每一个子集中选取少数具有代表性的数据作为测试用例.
  * 每一类代表性数据在测试中的作用等价于这一类中的其他值
  * 首先要在分析需求规约的基础上划分等价类。列出等价类表
  * e.g.“输入数值的范围是 1-999 ”

**等价类**

* **指某个输入域的子集合**.在该子集合中,各个输入数据对于揭露程序中的错误都是等效的.并合理地假定:测试某等价类的代表值就等于对这一类其它值的测试.
* 因此,可以把全部输入数据合理划分为若干等价类,在每一个等价类中取一个数据作为测试的输入条件,就可以用少量代表性的测试数据.取得较好的测试结果.
* 可划分为有效和无效等价类

**有效等价类**

* 指对于程序的规格说明来说是合理的输入数据构成的集合 .
* 利用有效等价类可检验程序是否实现了规格说明中所规定的功能和性能。

**无效等价类**

* 指对程序的规格说明是不合理的或无意义的输入数据所构成的集合。
* 对于具体的问题，无效等价类至少应有一个，也可能有多个。

**等价类划分方法**

* 完备测试、避免冗余
* **划分等价类重要的是：集合的划分**，划分为互不相交的一组子集，而子集的并是整个集合
  * 并是整个集合：完备性
  * 子集互不相交：保证一种形式的无冗余性
  * 同一类中标识（选择）一个测试用例，同一等价类中，**往往处理相同，相同处理映射到“相同的执行路径”**

**六条具体的确定等价类的原则**

*
  1. 在输入条件规定了取值范围或值的个数的情况下则可以确立一个有效等价类和两个无效等价类。
  2. ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202204281017420.png)
*
  1. 在输入条件规定了输入值的集合或者规定了“必须如何”的条件的情况下可确立一个有效等价类和一个无效等价类。
*
  1. 在输入条件是一个布尔量的情况下可确定一个有效等价类和一个无效等价类。
  2. e.g.有效等价类：布尔量；无效等价类：非布尔量
*
  1. 在规定了输入数据的一组值（假定并且程序要对每一个输入值分别处理的情况下 n 个）可确立价类。
  2. e.g.输入条件说明学历可为: ,专科、本科、硕士、博士四种之一，则分别取这四种这四个值作为四个有效等价类，另外把四种学历之外的任何学历作为无效等价类。
*
  1. 在规定了输入数据必须遵守的规则的情况下可确立一个有效等价类（符合规则）和若干个无效等价类（从不同角度违反规则）。
*
  1. 在确知已划分的等价类中各元素在程序处理中的方式不同的情况下,则应再将该等价类进一步的划分为更小的等价类。

**等价类划分流程**

* 在确立了等价类后,可**建立等价类**表划分出的等价类输入条件
  * 有效等价类无效等价类
* **然后从划分出的等价类中按以下三个原则设计测试用例:**
  * 为每一个等价类规定一个唯一的编号。
  * 覆盖有效等价类：设计一个新的测试用例被覆盖地有效等价类,,使其尽可能多地覆盖尚未重复这一步，直到所有的有效等价类都被覆盖为止。
  * 覆盖无效等价类：设计一个新的测试用例,使其仅覆盖一个尚未被覆盖的无效等价类。重复这一步，直到所有的无效等价类都被覆盖为止。
* **e.g. 等价类划分实例**
  * 输入三个整数 a 、 b 、 c 分别作为三边的边长构成三角形。
  * 用等价类划分方法为该程序进行测试用例设计。

> 输入三个整数 a 、b 、c 分别作为三边的边长构成三角形。通过程序判定所构成的三角形的类型（一般三角形、等腰三角形、等边三角形），并输出判断类型结果。

![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202204281024982.png)

#### 边界值分析方法

边界值分析 ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202204281026107.png)

* 最基础，最常用的方法
* 大量的错误是发生在输入或输出范围的边界上而不是发生在输入输出范围的内部
* 实践证明输入输出数据的边界值最能发现程序的错误
* 边界值分析方法是一种补充等价类划分的方法
  * 边界值分析不是从某等价类中随便挑一个作为代表
  * 而是使这个等价类的每个边界都要作为测试条件。
* **首先应确定边界情况。**
  * 通常输入和输出等价类的边界,就是应着重测试的边界情况
  * 应当选取**正好等于,刚刚大于或刚刚小于边界的值**作为测试数据,而不是选取等价类中的典型值或任意值作为测试数据.
* **与等价划分的区别**
  * 边界值分析不是从某等价类中随便挑一个作为代表，而是**使这个等价类的每个边界都要作为测试条件。**
  * 边界值分析不仅考虑输入条件，还要考虑输出空间产生的测试情况。
* 基于边界值分析方法选择测试用例的原则
  *
    1. 如果输入条件规定了值的范围,则应取**刚达到这个范围的边界的值,以及刚刚超越这个范围边界的值作为测试输入数据。**
    2. ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202204281032240.png)
  *
    1. 如果输入条件规定了值的个数,则用最大个数,最小个数,比最小个数少一,比最大个数多一的数作为测试数据。
    2. ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202204281033366.png)
  *
    1. 将规则 1 2 应用于输出条件，即设计测试用例使测试用例输出值达到边界值及其左右值。
    2. ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202204281035728.png)
  *
    1. 如果程序的规格说明给出的输入域或输出域是有序集合
    2. 应选取集合的第一个元素和最后一个元素作为测试用例。
  *
    1. 分析规格说明,找出其它可能的边界条件。

_**通常是等价类划分与边界值分析两种方法技术同时使用，才能找到更有效的完备测试用例集**_

* **e.g.边界值分析**
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202204281039935.png)

_**边界值分析也可以用在白盒测试当中**_

* **输入有效数据空间**
  * 例子-有效值、无效值
  * 例子-特殊值
  *
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/%E4%BE%8B%E5%AD%90-%E6%9C%89%E6%95%88%E5%80%BC.png)
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/%E4%BE%8B%E5%AD%90-%E6%9C%89%E6%95%88%E5%80%BC2.png)
* **输入特殊值**
  * 除法中输入 0
  * 查找功能输入空白 回车
* **！！！重要例子！！！**
  * 例子
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202204281044860.png)
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202204281044234.png)
  * **测试用例表**
    * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202204281044492.png)

#### 因果图方法

* 充分考虑输入条件之间的组合以及相互制约的关系
* **等价类划分方法和边界值分析方法,都是着重考虑输入条件,但未考虑输入条件之间的联系, 相互组合等。**
* 因果图方法
  * 考虑采用一种适合于描述对于多种条件的组合,相应产生多个动作的形式来考虑设计测试用例
  * 按一定步骤,高效率地选择测试用例,同时还能为我们指出,程序规格说明描述中存在着什么问题。
* **因果图**
  * 因果图介绍
  * 因果图中使用了简单的逻辑符号，以直线联接左右结点。左结点表示输入状态（或称原因），右结点表示输出状态（或称结果）。
  * ci 表示原因，通常位于图的左部；ei 表示结果，通常在图的右部、ci 和 ei 均可取值 0 或 1.0 代表状态不出现，1 表示某状态出现
  * 因果图关系表示
    * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202204281052900.png)
* **关系**
  * 恒等：若 ci 是 1，则 ei 也是 1；否则 ei 为 0。
  * 非：若 ci 是 1，则 ei 是 0；否则 ei 是 1。
  * 或：若 c1 或 c2 或 c3 是 1，则 ei 是 1；否则 ei 为 0。“或”可有任意个输入。
  * 与：若 c1 和 c2 都是 1，则 ei 为 1；否则 ei 为 0。“与”也可有任意个输入。
* **约束**
  * 输入状态相互之间还可能存在某些依赖关系
    * 例如, 某些输入条件本身不可能同时出现。
  * **输出状态之间也往往存在约束**
  * 在因果图中,用特定的符号标明这些约束
* **特殊约束关系**
  * E约束(异):a和b中至多有一个可能为1,即a和b不能同时为1。
  * I约束(或):a、b和c中至少有一个必须是1,即 a、b 和c不能同时为0。
  * O约束(唯一);a和b必须有一个, 且仅有1个为1。
  * R约束(要求):a是1时,b必须是1,即不可能a是1时b是0。
  * 输出条件的约束类型
    * M约束(强制):若结果a是1,则结果b强制为0。
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202204281055239.png)
* **因果图方法最终生成的是判定表。它适合于检查程序输入条件的各种组合情况。**
* **利用因果图生成测试用例的基本步骤：**
  * 1.分析软件规格说明描述中, 哪些是原因(即输入条件或输入条件的等价类),哪些是结果(即输出条件), 并给每个原因和结果赋予一个标识符。
  * 2.分析软件规格说明描述中的语义.找出原因与结果之间原因与原因之间对应的关系. 根据这些关系 , 画出因果图。
  * 3.由于语法或环境限制, 有些原因与原因之间,原因与结果之间的组合情况不可能出现. 为表明这些特殊情况因果图上用一些记号表明约束或限制条件。
  * 4.把因果图转换为判定表。
  * 5.把判定表的每一列拿出来作为依据, 设计测试用例。
* e.g.因果图方法举例
  * 某软件规格说明书包含这样的要求：第一列字符必须是 A 或 B，第二列字符必须是一个数字，在此情况下进行文件的修改，但如果第一列字符不正确，则给出信息 L；如果第二列字符不是数字，则给出信息 M
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202204281059831.png)
  * **根据因果图建立判定表**
    * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202204281101404.png)
* **因果图小结**
  * **因果图方法是一个非常有效的黑盒测试方法，它能够生成没有重复性的且发现错误能力强的测试用例，而且对输入、输出同时进行了分析。**
    * 从因果图生成的测试用例(局部,组合关系下的)包括了所有输入数据的取TRUE与取FALSE的情况,构成的测试用例数目达到最少,且测试用例数目随输入数据数目的增加而线性地增加。
    * 如果哪个开发项目在设计阶段就采用了判定表，也就不必再画因果图，而是可以直接利用判定表设计测试用例了。

#### 猜错法

* 基于经验和直觉推测程序中所有可能存在的各种错误, 从而有针对性的设计测试用例的方法。
* 列举出程序中所有可能有的错误和容易发生错误的特殊情况◦,根据他们选择测试用例。
  * 例如, 输入数据和输出数据为 0 的情况；输入表格为空格或输入表格只有一行. 这些都是容易发生错误的情况。可选择这些情况下的例子作为测试用例。
  * 已经发现的错误的数目往往和尚未发现的错误数量成正比
  * 要进一步测试已经发生较多错误的程序段
* e.g.测试一个对线性表(比如数组)进行排序
  * 测试一个对线性表（比如数组）进行排序的程序，可推测列出以下几项需要特别测试的情况：
    * 输入的线性表为空表；表中只含有一个元素；输入表中所有元素已排好序；输入表已按逆序排好；输入表中部分或全部元素相同。

## 白盒测试和黑盒测试的比较

* **白盒测试**
  * 只考虑测试软件产品，不保证完整的需求规格是否被满足
  * 发现代码方面的缺陷，指出哪些实现部分是错误的
  * 可以发现每一条路径中的错误，对代码检查彻底
  * 缺点：成本高，无法检测代码中遗落路径和数据敏感性错误
* **黑盒测试**
  * 只考虑测试需求规格，不保证实现的所有部分是否被测试到
  * 发现遗漏的缺陷，指出规格的哪些部分没有被完成
  * 效率高。不需要了解实现细节。有助于暴露规格不一致和有歧义的问题。
  * 缺点：很多路径不能保证测试到