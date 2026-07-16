# v0.2：本地项目扫描和问答

v0.1 需要手动输入 README、目录和配置。

v0.2 增加第一版“自动读码”能力：

```text
本地项目路径
-> LocalProjectScanner
-> ProjectContext
-> PromptBuilder
-> AiGateway
-> ProjectQuestionAnswer
```

## 新增接口

### 扫描本地项目

```http
POST /api/project-reading/scan
```

输入：

```json
{
  "projectPath": "/path/to/project",
  "learningGoal": "I want to understand the main chain."
}
```

输出：`ProjectContext`

它包含：

- 项目名
- 根路径
- README 预览
- build file 预览
- 目录树
- 关键源码文件

### 分析本地项目

```http
POST /api/project-reading/analyze-local
```

它会先扫描项目，再复用 v0.1 的结构化阅读报告。

### 对本地项目提问

```http
POST /api/project-reading/ask
```

输入：

```json
{
  "projectPath": "/path/to/project",
  "question": "用户请求进来后，代码怎么生成报告？"
}
```

输出：`ProjectQuestionAnswer`

它包含：

- 短答案
- 相关文件
- 逻辑链路
- 缺失上下文
- 下一步阅读动作

## 现在还不是什么

现在还不是完整 agent。

它还不会自己决定无限往下读，也不会做复杂 RAG。

但它已经有 agent 的第一块基础：

> 它可以用一个工具读取本地项目上下文，再基于上下文回答问题。

## 为什么这一步重要

问答准不准，核心不是 prompt 写得花，而是上下文给得准。

所以 v0.2 的关键不是“更会聊天”，而是让系统先能把本地项目资料整理成 `ProjectContext`。
