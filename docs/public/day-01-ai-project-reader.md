# Day 1 发布草稿：我想做一个 AI 工程阅读器

日期：2026-07-19

## 今天要发什么

今天只发一个主题：

> 我想用 30 天做一个 AI 工程阅读器，让 AI 按工程化流程去读陌生项目，而不是裸模型随便问答。

不要展开讲太多框架细节。  
不要把 30 天计划全发出去。  
只讲清楚为什么做、解决什么痛点、今天做到哪里。

## 小红书版本

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
