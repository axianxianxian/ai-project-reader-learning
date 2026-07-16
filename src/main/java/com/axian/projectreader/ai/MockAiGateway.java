package com.axian.projectreader.ai;

import com.axian.projectreader.api.ProjectReadingRequest;
import com.axian.projectreader.domain.FileToRead;
import com.axian.projectreader.domain.ProjectReadingReport;
import com.axian.projectreader.domain.RiskNote;
import com.axian.projectreader.domain.StepAction;
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
}
