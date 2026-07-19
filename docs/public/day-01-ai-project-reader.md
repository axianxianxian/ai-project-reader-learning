# Day 1 发布草稿：我想做一个 AI 工程阅读器

日期：2026-07-19

## 今天要发什么

今天只发一个主题：

> 我想用 30 天做一个 AI 工程阅读器，让 AI 按工程化流程去读陌生项目，而不是裸模型随便问答。

不要展开讲太多框架细节。  
不要把 30 天计划全发出去。  
只讲清楚为什么做、解决什么痛点、今天做到哪里。

## 小红书版本

### 为什么旧版不够适合小红书

旧版信息是对的，但像项目说明书。

小红书更需要：

- 第一眼能看懂我为什么要做
- 有一点真实困境
- 有普通人能代入的痛点
- 技术点不要堆太密
- 最后留一个别人能回答的问题

### 推荐标题

> 我决定用 30 天做一个 AI 项目，逼自己从“看资料”走到“能展示”

备选标题：

- 第 1 天：我不想再只收藏 AI 资料了
- 后端转 AI，我先做一个能用的工程阅读器
- 我想做一个 AI 工具，专治“看项目看不进去”

### 推荐正文

```text
我发现自己学 AI 最大的问题不是没资料。

是资料太多了。

看 Spring AI、看 agent、看 RAG、看别人做的项目，看完当下觉得懂了，但真要让我拿出一个能讲的东西，我又会心虚。

所以我决定做一个 30 天小项目：

AI 工程阅读器。

它想解决的问题很具体：

当我拿到一个陌生项目时，我不想一上来就被 README、目录、依赖、各种配置淹没。

我希望 AI 能按一套固定流程帮我先抓主线：

1. 这个项目是做什么的
2. 怎么最快跑起来
3. 核心链路怎么走
4. 最值得先读的 5 个文件是什么
5. 我继续追问时，它能不能带着证据文件回答

我不想做一个“套壳聊天框”。

我想做一个真正的后端 AI 工程：

- Spring Boot 做后端
- Spring AI 接模型
- 本地扫描器先读项目上下文
- 输出结构化报告
- 回答要带文件证据
- 后面再加缓存和持续问答

这个项目也是给我自己的。

因为我看新东西很容易看深、看散、看焦虑。

我希望它能帮我，也能帮和我一样接手陌生项目会慌的人，先把主线抓住。

今天是第 1 天。

我已经先把本地 demo 页面跑起来了，也把 30 天计划写进项目里。

30 天后，我希望它至少能稳定读懂一个真实工程项目。

今天想问一句：

如果你第一次接手一个陌生项目，你最希望 AI 先告诉你什么？
```

### 更短版本

```text
我决定用 30 天做一个 AI 工程阅读器。

原因很简单：我不想再只收藏 AI 资料了。

我想做一个真正能打开、能演示、能讲清楚的后端 AI 项目。

它要解决的问题是：

当我拿到一个陌生项目时，AI 不要随便总结，而是按固定流程帮我读：

- 项目是做什么的
- 怎么跑起来
- 核心链路怎么走
- 先读哪 5 个文件
- 继续追问时能不能带证据回答

技术上我会用 Spring Boot + Spring AI。

不是为了做聊天框，而是为了练习真正的 AI 工程链路：

扫描项目 -> 组织上下文 -> 调模型 -> 结构化输出 -> 页面展示 -> 继续追问

今天第 1 天，先把目标写清楚，把本地 demo 跑起来。

30 天后，希望它能稳定读懂一个真实工程项目。
```

## 小红书旧版存档

标题：

> 第 1 天：我想做一个 AI 工程阅读器

正文：

我最近发现自己学 AI 很容易陷入一个问题：资料越看越多，但真正能拿出来讲的东西很少。

所以我决定做一个 30 天项目：AI 工程阅读器。

它要解决的问题很简单：

当我拿到一个陌生项目时，我希望 AI 不只是随便总结一下，而是能按固定流程帮我读：

1. 这个项目是做什么的
2. 怎么最快跑起来
3. 核心链路怎么走
4. 最值得先读的 5 个文件是什么
5. 如果我继续追问，它能不能带着证据文件回答

我不想只做一个“调模型接口”的 demo。

我想做的是一个真正的 AI 后端工程：

- Spring Boot 做后端
- Spring AI 接模型
- 本地扫描器读取项目上下文
- 输出结构化报告
- 回答必须带证据文件
- 后续加入缓存和持续问答

我的目标不是成为算法工程师，而是成为能把 AI 能力接进真实业务系统的人。

今天先把目标写清楚，也把 demo 页面跑起来。

30 天后，希望它至少能稳定读懂一个我工作里会遇到的工程项目。

今天的问题：

如果你第一次接手一个陌生项目，你最希望 AI 先告诉你什么？

## X / Twitter 版本

### 为什么旧版不够适合 X

旧版可以，但偏完整说明。

X 更适合：

- 第一条直接讲冲突
- 每条只讲一个点
- 有 build in public 的进行感
- 技术链路简洁，不要像 README

### 推荐单条版

```text
Day 1 of building an AI project reader.

I don’t want another chatbot.

I want a Spring Boot + Spring AI app that helps people understand unfamiliar codebases through a fixed workflow:

scan -> context -> structured report -> file evidence -> follow-up Q&A

Goal for today: make the direction clear and get a local demo running.
```

### 推荐线程版

```text
1/ Day 1 of building an AI project reader.

The goal is not to build another chatbot.

I want to build a Spring Boot + Spring AI app that helps people understand unfamiliar codebases faster.
```

```text
2/ The problem:

When I open a new project, I often get lost in README files, configs, folders, dependencies, and random implementation details.

Bare model calls are too loose. Inputs are incomplete, outputs drift, and the model may miss the main path.
```

```text
3/ The product idea:

Give it a local project path or a GitHub repo.

It should return:

- what the project does
- how to run it
- the core flow
- the top 5 files to read first
- follow-up answers with file evidence
```

```text
4/ The engineering workflow:

Controller
-> Service
-> Local scanner
-> Prompt builder
-> AI Gateway
-> Structured report
-> UI

I want AI to become part of a backend system, not just an API call.
```

```text
5/ Day 1 progress:

- clarified the product direction
- created a local demo page
- added a 30-day build plan
- wrote the first public build draft

Next: make the report model match the actual product output.
```

### X 旧版存档

```text
Day 1 of building an AI project reader.

The goal is not to build another chatbot.

I want to build a Spring Boot / Spring AI app that can read an unfamiliar codebase with a fixed engineering workflow:

- scan the project
- identify the main purpose
- explain how to run it
- trace the core flow
- pick the top 5 files to read first
- answer follow-up questions with file evidence

Bare model calls are too loose for this. Inputs are incomplete, outputs drift, and the model may miss the main path.

So I am turning codebase reading into a structured backend workflow:

Controller -> Service -> Scanner -> Prompt Builder -> AI Gateway -> Structured Report

Day 1 goal: make the product direction clear and get a local demo page running.
```

## 可配图内容

今天不用做复杂图。

可以配一张简单截图：

- 左侧显示 `今天路线` 或 `30天计划`
- 中间显示项目页面
- 重点让别人看到这不是单纯文字计划，而是已经有一个本地 demo

截图文案可以写：

> 不是再收藏资料了，今天开始做一个能打开的 AI 工程项目。

## 发布前检查

- 页面能打开：`http://localhost:8080/`
- 左侧能看到 `今天路线` 和 `30天计划`
- 文案里没有暴露隐私项目、公司内部信息、账号信息
- 发布前由本人确认
