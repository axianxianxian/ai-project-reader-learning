# 本周产品样板：工程热读助手 v0

日期：2026-07-19

## 一句话

做一个页面式的工程阅读器：用户输入项目，系统生成一份可交互的项目说明书，再允许用户基于文件上下文继续追问。

这个产品对外不是“学习工具”，而是“工程阅读器”。学习只是用户使用它之后自然得到的结果。

## 本周要做出来的效果

用户打开页面后，能完成一个完整动作：

1. 输入本地项目路径或项目资料。
2. 填一句自己的目标，例如“我想先跑通 demo”。
3. 点击生成热读报告。
4. 页面展示：
   - 这个项目是做什么的
   - 怎么最快跑起来
   - 主链路是什么
   - 先读哪些文件
   - 哪些内容今天先不要看
   - 推荐继续问什么
5. 用户选择 1-5 个文件作为上下文。
6. 用户问一个问题。
7. 系统给出短回答，并说明依据来自哪些文件。

## 页面结构

左侧：阅读目录和证据文件

- 阅读章节目录
- 每章预计阅读时间
- 证据文件列表
- 文件为什么被引用

中间：项目说明书

- 项目一句话
- 技术栈和复杂度
- 最短跑通路径
- 端到端链路
- 第一次应该读哪些文件
- 今天先不要深挖什么

右侧：轻量问答

- 当前回答依据
- 推荐问题
- 用户输入问题
- AI 回答
- 下一步建议

## 这周的边界

本周要做：

- 页面能打开
- mock 数据能展示
- 后端已有接口能返回报告
- 用户问答能跑通一个固定链路
- 回答里带依据文件

本周不做：

- 不做登录
- 不做积分
- 不做复杂文件树
- 不做支持任意 GitHub 仓库
- 不做完整 UI 打磨
- 不做复杂 agent

## 最小数据结构

`HotReadReport`

- projectName
- oneSentence
- problemSolved
- techStack
- runSteps
- mainFlow
- topFiles
- skipForNow
- suggestedQuestions
- uncertainty

`TopFile`

- path
- role
- reason
- readOrder

`ProjectQuestionAnswer`

- question
- shortAnswer
- evidenceFiles
- nextAction
- uncertainty

## 本周验收标准

用户能看着页面说清楚：

- 这个工具给谁用
- 用户输入什么
- 系统输出什么
- 为什么不是普通聊天机器人
- 这个项目的 AI 工程链路在哪里

技术上至少跑通：

- Controller 接请求
- Service 编排
- Scanner 读取项目资料
- PromptBuilder 组织提示
- AiGateway 先用 mock 返回结构化结果
- 页面展示结果

## 原型页

旧版学习台原型：

`prototypes/hot-read-assistant-v0.html`

新版工程阅读器原型：

`prototypes/project-reader-v0.html`

当前标记：

**本周目标不是做完整产品，而是做出“用户一看就知道怎么读一个工程”的第一版效果。**
