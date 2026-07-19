# AI 工程转型参考地图

来源：观潮台最近一次资料整理。  
用途：保留有价值资料，但不让当前阶段被资料淹没。

## 先看这一屏

你当前不是要成为算法工程师，也不是要把所有 AI 框架都学完。

你当前要补的是这个身份：

> 一个熟后端的人，能把 AI 能力接进真实后端系统，并做成可演示、可解释、可求职展示的 AI 应用工程作品。

对应到当前项目，就是：

> 用 Spring AI 把工程阅读器从 mock AI 变成真实 AI 工程。第一阶段证明结构化输出，第二阶段证明 tool calling，第三阶段证明基于项目上下文问答。

## 当前阶段只做什么

只做一条主线：

1. 主框架押 `Spring AI`。
2. 当前作品押 `工程阅读器`。
3. 当前能力押 `结构化输出 + 本地项目扫描 + 带证据问答`。
4. 当前展示押 `打开 localhost 页面就能演示`。

你现在不要用“我有没有懂所有 AI 趋势”判断自己。

你要用这几个问题判断：

1. 我能不能讲清楚 Controller 收什么输入？
2. 我能不能讲清楚 Service 怎么编排扫描、prompt、AI 调用？
3. 我能不能讲清楚为什么要抽 `AiGateway`？
4. 我能不能讲清楚 Spring AI 比裸 HTTP 调模型多解决了什么？
5. 我能不能打开页面，让别人看到一个能用的 demo？

## 为什么观潮台那条很有用

它真正有用的不是给了一堆 repo，而是把你的目标校准成了：

> 建立“别人一看就知道你会做 AI 工程”的证据。

这个证据不是：

- 我收藏了很多资料
- 我知道很多框架名
- 我刷到了很多趋势
- 我看过很多文章

而是：

- 我做了一个能运行的产品
- 里面真的接入了 AI 工程链路
- 输入输出是结构化的
- AI 能读项目上下文
- 回答能指向证据文件
- 我能讲清楚为什么这样设计

## 必须保留的四个能力

这个项目要证明四件事：

1. 会把模型接进 Java 后端。
2. 会做结构化输出、tool calling、RAG/上下文这些核心模式。
3. 会把 AI 做成一个能演示的产品，不只是接口 demo。
4. 能讲清楚为什么这样设计，而不是只会调 API。

## 学习资料分层

### A. 当前主线：Spring AI

链接：[spring-projects/spring-ai](https://github.com/spring-projects/spring-ai)

为什么适合你：

- 你已有 Java / Spring 后端基础。
- 它能自然接到你现在的工程阅读器。
- 面试时比泛泛说“我会 AI”更具体。
- 它覆盖 structured output、tool calling、RAG、chat memory、MCP、observability 等 AI 应用工程常见能力。

当前只看：

- ChatClient 怎么调用模型
- `.entity(ProjectReadingReport.class)` 这种结构化输出
- Tool Calling 怎么把本地扫描工具交给模型用
- Advisors / Memory / RAG 先知道存在，不急着全做

暂时不要看：

- 全部 example
- 复杂 agent graph
- MCP 深水区
- 多模型兼容细节

### B. 底层参照：openai-java

链接：[openai/openai-java](https://github.com/openai/openai-java)

为什么保留：

- 它让你知道框架底下本质还是模型请求。
- 它能帮你理解 Spring AI 到底封装了什么。
- 它能帮你避免把框架神化。

当前只看：

- 最基础的模型调用
- function calling example
- 返回结构如何被 Java 代码消费

你要得到的结论：

> 裸模型调用是底层能力，AI 框架是把模型调用、结构化输出、工具调用、上下文管理纳入工程系统。

### C. Java AI 对照组：LangChain4j examples

链接：[langchain4j/langchain4j-examples](https://github.com/langchain4j/langchain4j-examples)

为什么保留：

- 它不是你的主线，但可以让你看到 Java 生态里别人怎么做 AI 应用例子。
- 它适合当“参考答案”，帮你校准自己的工程结构。

只看这些目录：

- `spring-boot-example`
- `agentic-tutorial`
- `rag-examples`
- `mcp-example`
- `customer-support-agent-example`

不要做：

- 不要从 Spring AI 主线跳去 LangChain4j 重做一遍。
- 不要因为它例子多就认为自己都要学。

### D. 产品输出参考：Codebase Explainer

链接：[Codebase Explainer](https://www.ashishmehrotra.com/projects/codebase-explainer)

为什么最贴当前项目：

它把“读代码库”这件事拆成了很清晰的输出：

- New Developer Guide
- Data Flow Explanation
- Top 5 Files to Read First

这几乎就是你当前项目的产品骨架。

对应到你的版本：

- `项目地图` 对应 New Developer Guide
- `核心链路` 对应 Data Flow Explanation
- `关键文件` 对应 Top 5 Files to Read First
- `阅读报告` 对应可以交付、可以导出的解释文档

当前最该偷的是：

- 输出结构
- 对新手友好的表达
- 先全局、再链路、再文件的顺序

暂时不偷：

- 多 agent 架构
- 复杂流程编排
- 炫技 UI

### E. 后期产品想象力：Understand Anything

链接：[Egonex-AI / Understand-Anything](https://github.com/Egonex-AI/Understand-Anything)

为什么保留：

- 它提醒你代码理解不只是问答，还可以变成图谱、导航、解释、搜索。

现在不看细节。

后面当你已经做出 MVP，再回来思考：

- 项目地图能不能变成可交互图？
- 文件之间的关系能不能可视化？
- 阅读路径能不能根据用户问题动态调整？

### F. 后期上下文地图：codebase-context

链接：[PatrickSys/codebase-context](https://github.com/PatrickSys/codebase-context)

为什么保留：

- 它不是一上来做花哨 UI，而是先做代码库上下文地图。
- 这对你后面减少 token 浪费、减少 AI 乱读文件很有帮助。

现在只记一个观点：

> 工程阅读器不应该每次都让模型重新乱猜，它应该先建立项目上下文地图。

### G. 国内生态备选：spring-ai-alibaba/examples

链接：[spring-ai-alibaba/examples](https://github.com/spring-ai-alibaba/examples)

为什么保留：

- 如果你后面要贴国内招聘生态，它会比纯海外资料更容易讲。
- 它有 agent、chat memory、graph、MCP 等例子。

现在不要深入。

等 Spring AI 主线跑通后，再用它补国内生态对照。

## 当前项目应该怎么长

### 第一步：真实 demo

目标：

> 用户打开 `localhost:8080`，输入本地项目路径，能看到一份结构化阅读报告。

已经在做。

### 第二步：结构化输出

目标：

> 不让 AI 返回一坨文字，而是返回固定对象。

例如：

- projectName
- summary
- readingPath
- demoPlan
- risks
- nextAction

这一步对应求职讲法：

> 我不是简单调模型，我把模型输出纳入后端类型系统，让业务代码可以稳定消费。

### 第三步：Tool Calling

目标：

> 让模型不是凭空回答，而是通过工具读取本地项目资料。

你的工具可以先是：

- scan README
- scan build file
- scan project tree
- scan key source files

这一步对应求职讲法：

> 我把本地项目扫描封装成工具，让 AI 在受控上下文里分析工程，而不是靠裸 prompt 猜。

### 第四步：带证据问答

目标：

> 用户问一个项目问题，回答必须带 relatedFiles 和 logicTrace。

这一步对应求职讲法：

> 我关注 AI 输出可信度。回答不只是自然语言，还要回指证据文件和链路。

### 第五步：缓存和项目记忆

目标：

> 同一个项目不要每次重新扫描和重新分析。

后面可以沉淀：

- project context cache
- reading report cache
- question history
- file evidence map

这一步对应求职讲法：

> 我考虑了 AI 应用的成本、延迟和可持续使用体验。

## 你和资料之间的平衡

你的个性里有一个强点，也有一个风险。

强点：

> 你会认真追问“这到底有没有价值”，不会满足于表层 demo。

风险：

> 你会担心错过重要信息，于是同时打开太多材料，最后主线被淹没。

所以这份资料以后按三层使用：

1. 今天只看“当前阶段只做什么”。
2. 卡住时看对应的资料层。
3. 做完一个小版本后，再回来打开后期参考。

不要一次读完。

## 现在最该执行的 3 件事

1. 把当前 H5 demo 用顺。
2. 把 `ProjectReadingReport` 调整成更贴产品的四段输出：
   - 项目地图
   - 核心链路
   - 关键文件
   - 阅读报告
3. 把 `MockAiGateway` 替换路径讲清楚：
   - mock 版用于学习和测试
   - Spring AI 版用于真实模型
   - 二者通过 `AiGateway` 接口隔离

## 一句话版本

> 你不是要学完 AI，你是要用 Spring AI 做出一个能打开、能分析、能问答、能讲清楚设计价值的工程阅读器。资料全部保留，但当前只按这一条主线往前走。
