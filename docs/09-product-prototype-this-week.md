# 本周产品样板：工程热读助手 v0

日期：2026-07-19

## 一句话

做一个页面式的工程热读助手：用户输入项目，系统先生成热读报告，再允许用户基于文件上下文继续追问。

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

左侧：输入和上下文

- 项目路径 / GitHub URL
- 用户这次的阅读目标
- 生成热读报告按钮
- 推荐文件列表
- 文件选择框

中间：热读报告

- 项目一句话
- 技术栈和依赖
- 最短跑通路径
- 主链路
- Top 文件
- 不确定点
- 下一步行动

右侧：项目问答

- 推荐问题
- 用户输入问题
- AI 回答
- 回答依据

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

打开这个文件：

`prototypes/hot-read-assistant-v0.html`

当前标记：

**本周目标不是做完整产品，而是做出“用户一看就知道怎么用”的第一版效果。**
