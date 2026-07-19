# Spring AI 接入说明

当前项目已经接入第一版 Spring AI。

但默认仍然走 `MockAiGateway`，因为这样没有 API Key 也能启动、测试和学习。

真正的 Spring AI 实现放在 `AiGateway` 的另一个实现里：

```text
ProjectReadingService
-> AiGateway
   -> MockAiGateway
   -> SpringAiGateway
```

## Spring AI 到底纳入了什么

不用框架时，业务代码通常会自己拼 HTTP 请求、自己调模型接口、自己解析模型返回。

用了 Spring AI 后，模型调用被纳入 Spring Boot 的工作方式：

```text
Controller
-> Service
-> AiGateway
-> SpringAiGateway
-> ChatClient
-> Model Provider
-> structured output
-> Java record
```

这里的关键不是“能不能调通模型”，而是：

- `SpringAiGateway` 可以被 Spring 注入和替换。
- `ChatClient` 统一承接模型调用。
- `entity(ProjectReadingReport.class)` 把模型结果转成业务对象。
- 后续 tool calling 可以把 `LocalProjectScanner` 暴露给模型调用。

## 要学的 Spring AI 概念

- `ChatClient`
- prompt / system message / user message
- structured output
- tool calling
- advisor
- embedding
- vector store

## 接入原则

不要让 Spring AI 代码散落在 Controller 或 Service 里。

原因：

- 方便 mock 测试。
- 方便替换模型。
- 方便比较不同 prompt。
- 方便以后加入 trace Q&A 业务场景。

## 第一版 SpringAiGateway 已经做了什么

1. 接收 `ProjectReadingRequest` 和 prompt。
2. 调用 `ChatClient`。
3. 用 `entity(ProjectReadingReport.class)` 转成结构化报告。
4. 接收 `ProjectQuestionRequest` 和项目上下文。
5. 用 `entity(ProjectQuestionAnswer.class)` 转成结构化问答结果。

## 怎么切换到真实 Spring AI

默认配置：

```properties
project-reader.ai.provider=mock
```

如果要试真实模型：

```properties
spring.profiles.active=spring-ai
```

同时通过环境变量提供 key：

```text
OPENAI_API_KEY=your_key
```

不要把 key 写进 git。

默认排除 OpenAI 自动模型装配，是为了让 mock 模式不依赖 API Key。
否则 Spring AI starter 会自动创建 OpenAI chat、embedding、audio 等模型 bean，没有 key 时应用会启动失败。

`spring-ai` profile 会切到真实 `SpringAiGateway`，并重新放开 OpenAI 自动装配。

## 下一步

当前版本只是把模型调用接入 Spring AI。

下一步更有 AI 工程含量的是 tool calling：

```text
用户提问
-> 模型判断需要项目资料
-> 调用 LocalProjectScanner
-> 拿到 README / 目录 / 源码片段
-> 再生成回答
```
