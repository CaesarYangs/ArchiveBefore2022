# 算法分析



2022.2.4 #第一遍阅读

算法(algorithm)是为求解一个问题需要遵循的、被清楚指定的简单指令的集合。对于一个问题，一旦某种算法给定并且（以某种方式）被确定是正确的，那么重要的一步就是确定该算法将需要多少诸如时间或空间等资源量的问题。如果一个问题的求解算法竟然需要长达一年时间，那么这种算法就很难能有什么用处。同样，一个需要若干个GB(gigabyte)的内存的算法在当前的大多数机器上也是无法使用的。

#### 数学基础

我们比较他们的**相对增长率**。当将相对增长率应用于算法分析时，将会明白为什么它是重要的度量。

大O标记法

典型的增长率：常数，对数，对数平方，线性的（两种），二次，三次，指数。

> 即使是适度大小的输入量，低效的算法依然是多么的无用。

#### 运行时间计算

为了简化分析，我们将采纳如下的约定：不存在特定的时间单位。因此，我们抛弃一些前导的常数。我们还将抛弃低阶项，从而要做的就是计算大O运行时间。由于大O是一个上界， 因此我们必须仔细，绝不要低估程序的运行时间。实际上，分析的结果为程序在一定的时间范围内能够终止运行提供了保障。程序可能提前结束，但绝不可能错后。

**计算法则**

* for循环 一个for循环的运行时间至多是该循环内部语句（包括测试）的运行时间乘以迭代次数
* 嵌套for循环
* 顺序语句
* if/else语句

关于递归等方法的使用

> 这个程序之所以运行缓慢，是因为存在大量多余的工作要做，违反了在1.3节中叙述的递归的第四条主要法则（合成效益法则）。注意，在第3行上的第一次调用即fib(n-1)实际上在某处计算fib(n-2)。这个信息被抛弃而在第3行上的第二次调用时又重新计算了一遍。抛弃的信息量递归地合成起来并导致巨大的运行时间。这或许是格言“计算任何事情不要超过一次”的最好的实例，但它不应使你被吓得远离递归而不敢使用。本书中将随处看到递归的杰出使用。

**e.g.最大子序列和问题** #算法实例 存在多种解法，解法之间差异又很大。O从三次一直下降到线性。

1. 三次
2. 二次-两重for循环 最常规解法 利用第一重循环内的中间变量做临时存储比较。
3. N _logN_-对递归的极好范例 分治策略
4. 线性