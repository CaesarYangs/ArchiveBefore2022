# 7-3测试用例设计



* 什么是测试用例
* 为什么需要测试用例
* 测试用例的质量
* 测试用例的组织和使用

## 测试用例概念

* 测试用例
  * 是可以被独立执行的一个过程，这个过程是一个最小的测试实体，不能再被分解、
  * 测试用例也就是为了某个测试点而设计的测试操作过程序列、条件、期望结果及其相关数据的一个特定的集合
  * 如果实际结果和期望结果不一致，就是问题，也就预示着可能发现了一个缺陷
* **测试用例体现了测试方案方法，技术和策略**
  * 包括了测试环境 测试目标 输入数据 测试步骤 预期结果 测试脚本 要形成文档
  * 测试用例是软件测试中最重要的部分 是测试工作的指导 是软件测试必须遵守的准则 是软件测试质量稳定的根本保障
* 可以设计多少个测试用例?
* 例子：登陆页面的测试用例设计
* 例子：测试用例表设计
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205051004319.png)
* **Q：测试用例应该描述什么？**——5W1H
  * Why——为什么而测？
  * What——测什么？
  * Where——在哪里测？
  * When——什么时候开始测？
  * Which——哪些输入数据？
  * How——如何操作软件？
* 测试用例的元素

| 字段名称    | 注释 |
| ------- | -- |
| 标志符     |    |
| 测试项     |    |
| 测试目标    |    |
| 测试环境要求  |    |
| 前提      |    |
| 输入数据    |    |
| 操作步骤    |    |
| 期望输出    |    |
| 所属模块    |    |
| 优先级     |    |
| 层次      |    |
| 关联的测试用例 |    |
| 执行时间    |    |
| 自动化标识   |    |
| 关联的缺陷   |    |

## Q：为什么需要测试用例？

* 如何以**最少的人力、资源投入，在最短的时间内完成测试**，发现软件系统的缺陷，保证软件的优良品质，则是软件公司探索和追求的目标
* **测试用例是测试工作的指导，是软件测试的必须遵守的准则，更是软件测试质量稳定的根本保障**
* **软件测试是有组织性、步骤性和计划性的，为了能将软件测试的行为转换为可管理的、具体量化的模式，需要创建和维护测试用例**
* **测试用例的作用**
  * 重要参考依据
  * 提高测试质量
  * 有效性
  * 复用性：不断地反复测试 回归测试 复用相同的测试用例
  * 客观性：无任何主观成分
  * 可评估性和可管理性：同行评估 有编号 有效管理
  * 知识传递：传递人 传递项目

## 测试用例的质量

* 测试用例的质量要求
* 测试用例书写标准
* 如何设计出高质量的测试用例
* 测试用例的评审

### 单个用例的质量要求

* 具有可操作性
* 具备所需的各项信息
* 各项信息描述准确、清楚
* 测试目标针对性强
* 验证点完备，而且没有太多的验证点：一个用例涵盖一个测试点
* 没有太多的操作步骤，例如不超过 7 步
* 符合正常业务惯例

### 整体测试用例的质量要求

* 覆盖率
  * 依据特定的测试目标的要求，尽可能覆盖所有的测试范围、功能特性和代码。
* 易用性
  * 测试用例的设计思路清晰、组织结构层次合理，测试用例操作的连贯性好，使单个模块的测试用例执行顺畅。
* 易维护性
  * 应该以很少的时间来完成测试测试用例的维护工作，包括添加、修改和删除测试用例。易用性和易读性，也有助于易维护性。
* 粒度适中
  * 既能覆盖各个特定的场景，保证测试的效率；又能处理好不同数据输入的测试要求，提高测试用例的可维护性。

### 测试用例书写标准

* 测试用例设计书写标准(ANSI/IEEE 8291983)

| 字段名称     | 类型  | 是否必选 | 注释                   |
| -------- | --- | ---- | -------------------- |
| 标识符      | 整形  | 是    | 唯一标识该测试用例的值          |
| 测试项      | 字符型 | 是    | 测试的对象                |
| 测试环境要求   | 字符型 | 否    | 可能在整个模块里面使用相同的测试环境要求 |
| 输入标准     | 字符型 | 是    |                      |
| 输出标准     | 字符型 | 是    |                      |
| 测试用例间的关联 | 字符型 | 是    | 并非所有的测试用例之间都需要关联     |

* 书写规范的测试用例
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205051023244.png)
* \==**良好测试用例的特征**==
  * 可以最大程度地找出软件隐藏的缺陷
  * 可以最高效率的找出软件缺陷
  * 可以最大程度地满足测试覆盖要求
  * 既不过分复杂、也不能过分简单
  * 使软件缺陷的表现可以清楚的判定
  * 测试用例包含期望的正确的结果
  * 待查的输出结果或文件必须尽量简单明了
  * 不包含重复的测试用例
  * 测试用例内容清晰、格式一致、分类组织
* **设计高质量测试用例**
  * 客户需求导向的设计思路
  * 责任到人
  * 灵活的设计方法
  * 尽量避免含糊的、冗长的或复杂的测试用例
  * 尽量将具有相类似功能的测试用例抽象并归类
* **设计测试用例的误区**
  * **1.能发现到目前为止没有发现的缺陷的用例是好的用例**
    * 程序做了它应该做的事情
    * 程序没有做它不该做的事情
    * 作为测试实施依据的测试用例，必须要能完整覆盖测试需求，而不应该针对单个的测试用例去评判好坏
  * **2.测试用例应该详细记录所有的操作信息，使一个没有接触过系统的人员也能进行测试**
    * 测试的目的是尽可能发现程序中存在的缺陷，测试本身就是 Project，需要在给定的资源条件下尽可能达到目标
    * 测试用例的详细程度也需要根据需要确定
  * **3.测试用例设计是一劳永逸的事情**
  * **4.测试用例不应该包含实际的数据**
  * **5.测试用例中不需要明显的验证手段**
    * _一个订货系统，输入订货数据，单击“确定”按钮后，系统提示“订货成功”。查看数据库中相关记录是否更新_

### 测试用例的设计步骤

* 测试用例设计步骤

### 测试用例评审

* 分析其设计思路，是否符合业务逻辑、是否符合技术设计的逻辑、是否可以和系统架构、组件等建立起完全的映射关系？
* 在局部上，应有重有轻，抓住一些测试的难点、系统的关键点，从不同的角度向测试用例的设计者提问。
* 在细节上，检查是否遵守测试用例编写的规范或模板，是否漏掉每一元素、每项元素是否描述清楚
* 检查表，提问

## 测试用例的组织和使用

### 测试用例的创建

* 测试用例的创建
* 建立合适的、可扩展的测试用例框架，从而借助这个框架能有效地组织众多的测试用例，包括对测试用例的分类、清晰的层次结构等
  * 树形结构组织测试用例
  * ![](https://raw.githubusercontent.com/CaesarYangs/MyPictureHotel/main/BasicImg/202205051033608.png)

### 测试用例的维护

* 随着产品版本的不断升级，软件测试用例也需要得到及时维护，有时还需要重构— —对测试用例的结构进行调整，包括用例模块的合并和分解，确保每一个测试用例都是有效的
* 测试用例的维护是一项长期的工作，日积月累，测试用例的质量会得到很大的改善。
* 例子：排队自动叫号管理软件
* 例子：软件销售一年后,客户提出
* 例子：现在有一个档案管理系统