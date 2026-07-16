# AI Project Reader Learning

一个学习版 AI 项目阅读助手。

这个项目的目标不是先做一个很大的产品，而是用一个足够小、五脏俱全的应用，把 AI 工程链路吃透：

```text
用户输入
-> Controller / API
-> Prompt 组织
-> AI Gateway / 模型调用
-> Tool / 内部知识调用
-> 结构化输出
-> 业务代码消费结果
```

## 这个项目要解决什么问题

当我拿到一个技术项目时，经常会遇到这些问题：

- README 很长，不知道先看哪里。
- 目录很多，不知道哪些文件是主线。
- 框架概念很多，比如 Spring AI、tool calling、structured output、RAG、agent workflow，看起来都懂，但不能串起来。
- 看资料容易被淹没，最后没有一个可讲、可运行、可复盘的成果。

这个项目把“读项目”这件事变成一个 AI 助手流程：

1. 输入 README、项目目录、配置片段、当前目标。
2. AI 先生成一份项目阅读报告。
3. 报告必须是结构化的，方便后续页面、测试、工作流继续消费。
4. 用户可以按报告一步步读项目、跑 demo、做面试讲述。

## v0.1 范围

v0.1 先跑通完整工程链路，不急着做复杂 agent：

- Spring Boot 后端
- 一个 `/api/project-reading/analyze` 接口
- 手动输入项目资料
- Mock AI Gateway 生成结构化报告
- 明确保留 Spring AI 接入点
- 配套学习文档，帮助我讲清楚每一层

## v0.2 范围

v0.2 开始加入“自动读码”的第一块能力：

- 一个 `/api/project-reading/scan` 接口：输入本地项目路径，输出 `ProjectContext`
- 一个 `/api/project-reading/analyze-local` 接口：扫描本地项目后生成阅读报告
- 一个 `/api/project-reading/ask` 接口：基于扫描到的上下文回答项目问题
- 一个 `LocalProjectScanner` 工具：读取 README、pom/build 文件、目录树和关键 Java 文件

这一步还不是完整 agent，但已经开始具备 agent 的基础形态：

```text
用户问题
-> 工具读取本地项目
-> 组织项目上下文
-> 构造问答 prompt
-> AI Gateway 返回结构化答案
```

## 为什么先用 Mock AI

因为这个项目是学习版。

先用 Mock AI 的好处是：

- 不需要 API Key 就能运行。
- 可以先理解系统分层，而不是卡在模型配置。
- 可以为每一层写测试。
- 后面替换成 Spring AI 时，只需要替换 `AiGateway` 实现。

## 运行方式

```bash
mvn spring-boot:run
```

请求示例：

```bash
curl -X POST http://localhost:8080/api/project-reading/analyze \
  -H "Content-Type: application/json" \
  -d @samples/project-input.json
```

扫描本地项目：

```bash
curl -X POST http://localhost:8080/api/project-reading/scan \
  -H "Content-Type: application/json" \
  -d @samples/local-project-request.json
```

对本地项目提问：

```bash
curl -X POST http://localhost:8080/api/project-reading/ask \
  -H "Content-Type: application/json" \
  -d @samples/project-question-request.json
```

## 目录结构

```text
src/main/java/com/axian/projectreader
├── api        # Controller 和请求响应
├── app        # 应用服务，编排业务流程
├── ai         # AI Gateway，后续接 Spring AI
└── domain     # 结构化输出模型

docs
├── 01-goal.md
├── 02-ten-day-plan.md
├── 03-ai-chain.md
├── 04-spring-ai-next.md
└── 05-local-scan-and-qa.md
```

## 学习验收标准

做完 v0.1，我需要能讲清楚：

- Controller 接收了什么输入。
- Prompt 是怎么组织的。
- 为什么要抽象 `AiGateway`。
- 结构化输出比纯文本回答好在哪里。
- 业务代码拿到结构化结果后能做什么。
- Spring AI 将来放在哪一层。

## 下一步

1. v0.1：Mock AI 跑通项目阅读报告。
2. v0.2：本地项目扫描 + 基于上下文问答。
3. v0.3：接入 Spring AI ChatClient。
4. v0.4：加入本地知识规则和 tool calling。
5. v0.5：加入简单 RAG，支持按项目文档检索。
6. v0.6：沉淀成作品集说明和面试讲述版本。
