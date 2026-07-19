# PRD v1：工程热读工作台

日期：2026-07-19

## 1. 背景

裸模型已经可以回答代码问题，但用户仍然需要自己整理上下文、判断文件重要性、组织问题、核验回答是否可靠。

本项目要做的不是一个新的聊天框，而是一个面向代码库理解的工程热读工作台：

```text
低门槛输入
-> 自动扫描项目
-> 选择关键上下文
-> 生成固定结构输出
-> 带文件证据
-> 支持继续深挖
```

## 2. 目标

### 产品目标

让用户用最低成本理解一个陌生工程：

- 它是干什么的
- 怎么跑起来
- 入口在哪里
- 核心链路怎么走
- 最先应该看哪 5 个文件
- 如果继续建设，下一步该做什么

### 学习目标

用一个真实项目吃透 AI 工程应用能力：

- Spring AI `ChatClient`
- structured output
- tool calling
- 本地项目扫描
- 上下文裁剪
- 文件证据绑定
- 缓存和分阶段反馈

### 作品集目标

形成一个可展示项目：

- GitHub 仓库清晰
- README 有架构、运行方式、截图和样例
- 有产品文档、排期、风险表
- 有真实项目分析 demo
- 能讲清楚“为什么比裸模型有价值”

## 3. 用户

### 核心用户

1. 接手陌生工程的后端工程师
2. 想快速跑 demo 的学习者
3. 容易被细节淹没、需要抓主线的人
4. 面试前需要快速理解项目并讲清楚的人

### 非目标用户

第一阶段暂不服务：

- 大型企业级代码治理团队
- 多语言超大 monorepo
- 安全审计场景
- 算法模型训练用户

## 4. 输入

第一阶段只做本地项目路径。

```text
projectPath: 本地项目路径
goal: 用户当前目标，例如“先看懂主链路”或“先跑 demo”
```

第二阶段支持 GitHub URL。

```text
repoUrl: GitHub 仓库地址
goal: 用户当前目标
```

正式产品不要求用户手动输入 README、目录树、构建文件。那些只保留为调试接口。

## 5. 输出

输出固定为四件套。

### 5.1 项目地图

字段建议：

- `projectName`
- `oneSentenceSummary`
- `problemSolved`
- `targetUsers`
- `techStack`
- `runAndDeployHints`
- `keyDependencies`
- `detectedResources`
- `missingContext`
- `evidenceFiles`

### 5.2 核心链路

字段建议：

- `entryPoint`
- `flowSummary`
- `steps`
- `relatedFiles`
- `confirmedFacts`
- `assumptions`
- `missingContext`

### 5.3 Top 5 文件

字段建议：

- `path`
- `role`
- `whyImportant`
- `readOrder`
- `whatToLookFor`
- `evidence`

### 5.4 阅读报告

字段建议：

- `mainReadingPath`
- `demoPath`
- `nextActions`
- `risks`
- `learningTopics`
- `markdownSummary`

## 6. 核心用户流程

### 本地项目流程

```text
用户输入本地路径
-> 系统扫描项目
-> 快速返回项目地图草稿
-> 生成 Top 5 文件
-> 生成核心链路
-> 生成完整阅读报告
-> 用户继续追问某个细节
-> 系统按需读取更多文件
-> 回答并给出证据和缺失上下文
```

### GitHub URL 流程

```text
用户输入 GitHub URL
-> 系统拉取仓库元信息
-> 扫描 README、构建文件、目录树和关键源码
-> 复用本地项目分析流程
```

GitHub URL 流程放到第二阶段。

## 7. 速度要求

不能等完整分析结束才给结果。

目标反馈节奏：

- 1-2 秒：展示扫描中、项目名、可能技术栈
- 3-5 秒：返回项目地图草稿
- 5-10 秒：返回 Top 5 文件
- 10-20 秒：返回核心链路
- 20 秒后：补完整阅读报告

第一阶段可以先用同步接口模拟分阶段结果；第二阶段再做异步任务和前端状态更新。

## 8. 缓存要求

第一阶段先做内存缓存或文件缓存。

缓存对象：

- `ProjectContext`
- 项目地图
- 核心链路
- Top 5 文件
- 阅读报告
- 用户问答历史

缓存 key：

```text
projectPath + lastModifiedSnapshot + goal
```

后续再升级为数据库或本地索引。

## 9. 非功能要求

- 默认 mock 模式可运行，不依赖 API Key
- Spring AI 模式通过 profile 显式启用
- 不把密钥写入代码和文档
- 输出必须包含文件证据或缺失上下文
- 大项目第一阶段只扫描有限文件，避免卡死
- 所有关键能力要有样例和测试

## 10. 第一阶段不做

- 不做完整 GitHub OAuth
- 不做多用户系统
- 不做权限系统
- 不做复杂 RAG
- 不做完整 UI 工程化
- 不做所有语言支持
- 不做大型 monorepo 深度分析

## 11. 成功标准

两周内满足：

- 输入本地项目路径
- 自动扫描当前项目
- 生成四件套输出
- 输出带文件证据
- 支持一次细节追问
- Spring AI gateway 已接入并可切换
- README 能展示项目价值和运行方式

一个月内满足：

- 有可展示页面
- 有一个真实项目 demo
- 有缓存
- 有 tool calling 第一版
- 有作品集说明和面试讲述稿
