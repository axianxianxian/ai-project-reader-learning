# Spring AI 下一步

v0.1 先不强行接真实模型。

Spring AI 应该放在 `AiGateway` 的一个实现里：

```text
ProjectReadingService
-> AiGateway
   -> MockAiGateway
   -> SpringAiGateway
```

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

## 第一版 SpringAiGateway 应该做什么

1. 接收 `ProjectReadingRequest` 和 prompt。
2. 调用 `ChatClient`。
3. 让模型返回 JSON。
4. 转成 `ProjectReadingReport`。
5. 如果模型返回格式不稳定，要有兜底处理。
