package com.axian.projectreader.ai;

import com.axian.projectreader.dto.ProjectQuestionRequest;
import com.axian.projectreader.dto.ProjectReadingRequest;
import com.axian.projectreader.model.FileToRead;
import com.axian.projectreader.model.ProjectContext;
import com.axian.projectreader.model.ProjectQuestionAnswer;
import com.axian.projectreader.model.ProjectReadingReport;
import com.axian.projectreader.model.RiskNote;
import com.axian.projectreader.model.SourceFileContext;
import com.axian.projectreader.model.StepAction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MockAiGateway implements AiGateway {

    @Override
    public ProjectReadingReport analyzeProject(ProjectReadingRequest request, String prompt) {
        return new ProjectReadingReport(
                request.projectName(),
                "这是一个学习版分析结果：先用 mock 跑通 AI 工程链路，后续替换为 Spring AI ChatClient。",
                List.of(
                        new FileToRead("README.md", "先理解项目目标、运行方式和关键概念。", 1),
                        new FileToRead("pom.xml / build.gradle", "确认框架、依赖、Java 版本和扩展点。", 2),
                        new FileToRead("src/main/java", "沿 Controller -> Service -> Gateway 看主流程。", 3)
                ),
                List.of(
                        new StepAction("Run", "先启动项目，确认接口能返回结构化结果。"),
                        new StepAction("Trace", "从 Controller 开始画出输入到输出的链路。"),
                        new StepAction("Replace", "把 MockAiGateway 替换成 Spring AI 实现。")
                ),
                List.of(
                        new RiskNote("只看资料不跑代码", "会觉得懂了，但讲不清工程链路。"),
                        new RiskNote("一开始就接真实模型", "容易被 key、网络和模型返回格式干扰。")
                ),
                "今天只做一件事：跑通 analyze 接口，并用自己的话解释每个类为什么存在。",
                prompt
        );
    }

    @Override
    public ProjectQuestionAnswer answerProjectQuestion(ProjectQuestionRequest request, ProjectContext context, String prompt) {
        List<String> relatedFiles = context.sourceFiles()
                .stream()
                .map(SourceFileContext::path)
                .limit(5)
                .toList();

        return new ProjectQuestionAnswer(
                "mock 回答：先从入口文件看请求怎么进来，再沿 Service、PromptBuilder、AiGateway 往下追。",
                relatedFiles,
                List.of(
                        "找到 Controller：确认接口路径和请求对象。",
                        "进入 Service：看它如何编排扫描、prompt 和 AI 调用。",
                        "查看 PromptBuilder：确认它给模型的上下文和约束。",
                        "查看 AiGateway：确认真实模型调用应该放在哪里。",
                        "查看返回对象：确认业务代码如何消费结构化结果。"
                ),
                context.sourceFiles().isEmpty()
                        ? List.of("没有扫描到关键 Java 文件，需要补充 src/main/java 下的源码。")
                        : List.of(),
                "下一步先打开第一个相关文件，用自己的话说清它在链路里的作用。",
                prompt
        );
    }
}
