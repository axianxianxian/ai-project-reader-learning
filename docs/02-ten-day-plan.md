# 10 天计划

每天 1 到 2 小时，目标是有一个能运行、能讲清楚、能放到 GitHub 的学习项目。

## Day 1：项目骨架

- 建 GitHub 仓库。
- 建 Spring Boot 项目。
- 跑通 mock AI 链路。
- 写 README 和项目目标。

产出：v0.1 初始提交。

## Day 2：讲清楚工程链路

- 画出 Controller -> Service -> Prompt -> AiGateway -> Report。
- 给每个类补一段“为什么存在”。
- 写第一篇学习笔记。

产出：`docs/03-ai-chain.md` 完整版。

## Day 3：结构化输出

- 设计 `ProjectReadingReport`。
- 增加更细的字段：概念、入口文件、运行命令、提问清单。
- 写测试验证结构。

产出：结构化输出 v0.2。

## Day 4：Spring AI 接入

- 增加 `SpringAiGateway`。
- 学 ChatClient 的位置和职责。
- 保留 mock 模式，避免被 key 卡死。

产出：Spring AI 接入说明和代码开关。

## Day 5：Tool Calling 入门

- 增加一个本地工具：框架知识卡查询。
- 让 AI 报告引用工具结果。

产出：tool calling demo。

## Day 6：RAG 最小版

- 准备几段项目文档。
- 做最小检索流程。
- 先不追求复杂向量库，重点理解“检索增强”。

产出：RAG 学习笔记和最小 demo。

## Day 7：测试方法

- 给 service 写单测。
- 给 controller 写接口测试。
- 总结 AI 应用测试边界。

产出：测试文档和测试用例。

## Day 8：做一个简单前端或页面

- 页面输入项目资料。
- 页面展示阅读报告。

产出：可截图的作品集页面。

## Day 9：面试讲述版

- 整理项目架构图。
- 写“我做了什么、难点是什么、怎么扩展”。

产出：作品集讲述稿。

## Day 10：收口

- 清理 README。
- 打 tag。
- 确认 GitHub 展示效果。

产出：v1.0 学习版。
