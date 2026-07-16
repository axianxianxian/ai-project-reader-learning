# AI 工程链路

这个项目先把 AI 应用拆成 6 层。

## 1. 用户输入

用户提供：

- 项目名
- 学习目标
- README
- 目录结构
- 配置片段

这里的关键不是“输入越多越好”，而是输入要能支撑 AI 做判断。

## 2. Controller / API

`ProjectReadingController` 只负责接收请求。

它不负责判断项目怎么读，也不负责拼 prompt。

这样做的好处是：接口层保持薄，业务变化不会把 Controller 变乱。

## 3. Prompt 组织

`ProjectReadingPromptBuilder` 负责把用户输入组织成模型能理解的任务。

prompt 里要说清楚：

- AI 扮演什么角色
- 输出目标是什么
- 约束是什么
- 用户输入是什么

## 4. AI Gateway / 模型调用

`AiGateway` 是模型调用边界。

现在实现是 `MockAiGateway`，后面可以加 `SpringAiGateway`。

这个抽象很重要，因为业务代码不应该关心底层到底是 mock、OpenAI、Spring AI 还是别的模型。

## 5. 结构化输出

`ProjectReadingReport` 是结构化结果。

它比一段自然语言回答更适合工程消费，因为后续可以：

- 展示成页面
- 写测试
- 存数据库
- 继续交给下一个工作流
- 转成面试讲述稿

## 6. 业务代码消费结果

`ProjectReadingService` 拿到结构化报告后，可以继续做业务动作。

v0.1 只是返回给用户。

后续可以扩展：

- 保存报告
- 生成学习任务
- 标记阅读进度
- 生成 demo checklist
- 给面试作品集页面提供数据
