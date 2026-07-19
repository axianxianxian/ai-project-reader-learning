# 未来效果 Demo：用户看到的产品形态

日期：2026-07-19

## Demo 目标

这个 Demo 用来定义未来效果，不代表今天已经全部实现。

目标是让用户一眼明白：

> 我只要给一个项目入口，它就能帮我生成项目地图、核心链路、Top 5 文件和阅读报告，还能继续追问细节。

## 场景 A：本地项目热读

### 用户输入

```text
本地项目路径：
/Users/luckyxian/Documents/Codex/2026-07-05/gu/ai-project-reader-learning

当前目标：
我想快速理解这个项目，知道它怎么接入 Spring AI，以及下一步怎么建设。
```

### 页面第一屏

```text
工程热读工作台

[输入区]
项目来源：本地项目
项目路径：/Users/.../ai-project-reader-learning
目标：快速理解项目并继续建设
[开始分析]

[状态区]
正在扫描 README、pom.xml、src/main/java...
```

### 快速反馈：项目地图草稿

```text
项目地图

一句话：
这是一个基于 Spring AI 的工程热读工作台，用来自动扫描项目并生成可执行阅读报告。

技术栈：
- Java 21
- Spring Boot 3.3.7
- Spring AI 1.0.9

已确认文件：
- README.md
- pom.xml
- ProjectReaderApplication.java
- ProjectReadingController.java
- ProjectReadingService.java
- AiGateway.java
- SpringAiGateway.java

缺失上下文：
- 真实 Spring AI 调用样例
- 前端页面与后端接口联动代码
```

### 第二阶段反馈：Top 5 文件

```text
Top 5 文件

1. README.md
   角色：项目入口说明
   看什么：产品目标、运行方式、当前能力

2. ProjectReadingController.java
   角色：HTTP 入口
   看什么：用户请求从哪些接口进来

3. ProjectReadingService.java
   角色：业务编排
   看什么：扫描、prompt、AI 调用如何串起来

4. AiGateway.java
   角色：AI 调用边界
   看什么：业务层期待 AI 提供什么能力

5. SpringAiGateway.java
   角色：真实 Spring AI 实现
   看什么：ChatClient 和结构化输出如何接入
```

### 第三阶段反馈：核心链路

```text
核心链路

用户请求：
POST /api/project-reading/analyze-local

链路：
1. ProjectReadingController 接收 LocalProjectRequest
2. ProjectReadingService 调用 LocalProjectScanner 扫描项目
3. ProjectReadingService 组装 ProjectReadingRequest
4. ProjectReadingPromptBuilder 生成 prompt
5. AiGateway 执行分析
6. MockAiGateway 或 SpringAiGateway 返回 ProjectReadingReport

证据文件：
- ProjectReadingController.java
- ProjectReadingService.java
- LocalProjectScanner.java
- ProjectReadingPromptBuilder.java
- AiGateway.java
```

### 完整阅读报告

```text
阅读报告

今天先看：
1. README.md
2. ProjectReadingService.java
3. AiGateway.java
4. SpringAiGateway.java
5. application-spring-ai.properties

最短 demo：
1. 先用 mock 模式启动项目
2. 调用 /scan 验证本地扫描
3. 调用 /analyze-local 验证阅读报告
4. 切 spring-ai profile 验证真实模型调用

下一步建设：
- 把项目地图、核心链路、Top 5 文件拆成独立模型
- 加缓存
- 加 tool calling
- 页面展示分阶段结果
```

### 用户继续追问

用户问：

```text
为什么要有 AiGateway？我能不能直接在 Service 里用 ChatClient？
```

系统回答：

```text
短答：
可以直接用，但不建议。AiGateway 隔离了业务编排和模型调用细节。

证据：
- ProjectReadingService.java 只依赖 AiGateway，不依赖 ChatClient
- SpringAiGateway.java 内部才使用 ChatClient
- MockAiGateway.java 可以在无 key 时替换真实模型

下一步建议：
打开 ProjectReadingService.java 和 SpringAiGateway.java 对照看。
```

## 场景 B：GitHub URL 热读

### 用户输入

```text
GitHub URL：
https://github.com/axianxianxian/ai-project-reader-learning

当前目标：
我想判断这个项目是否值得学习，并快速知道从哪里读起。
```

### 输出

和本地项目一致，但项目来源变成 GitHub。

第一阶段不实现完整 GitHub URL，只定义未来效果。

## 页面结构建议

```text
左侧：项目来源和文件树
中间：项目地图 / 核心链路 / Top 5 文件 / 阅读报告 tab
右侧：追问区和证据区
底部：分析状态和缓存命中提示
```

## 这个 Demo 的价值

它展示的不是“AI 会聊天”，而是：

- 用户输入低门槛
- 输出结构固定
- 分阶段反馈
- 每个结论带文件证据
- 支持继续深挖
- 能导出成 Markdown
