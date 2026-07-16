package com.axian.projectreader.app;

import com.axian.projectreader.ai.MockAiGateway;
import com.axian.projectreader.ai.ProjectReadingPromptBuilder;
import com.axian.projectreader.api.LocalProjectRequest;
import com.axian.projectreader.api.ProjectQuestionRequest;
import com.axian.projectreader.api.ProjectReadingRequest;
import com.axian.projectreader.domain.ProjectContext;
import com.axian.projectreader.domain.ProjectQuestionAnswer;
import com.axian.projectreader.domain.ProjectReadingReport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectReadingServiceTest {

    @TempDir
    Path tempDir;

    @Test
    void analyzesProjectIntoStructuredReport() {
        ProjectReadingService service = new ProjectReadingService(
                new ProjectReadingPromptBuilder(),
                new MockAiGateway(),
                new LocalProjectScanner()
        );

        ProjectReadingReport report = service.analyze(new ProjectReadingRequest(
                "demo",
                "understand the main AI chain",
                "A demo README",
                "src/main/java",
                "server.port=8080"
        ));

        assertThat(report.projectName()).isEqualTo("demo");
        assertThat(report.readingPath()).isNotEmpty();
        assertThat(report.demoPlan()).extracting("name").contains("Run", "Trace", "Replace");
        assertThat(report.promptPreview()).contains("understand the main AI chain");
    }

    @Test
    void scansLocalProjectAndAnswersQuestion() throws IOException {
        Path project = tempDir.resolve("demo-project");
        Files.createDirectories(project.resolve("src/main/java/demo"));
        Files.writeString(project.resolve("README.md"), "# Demo\nA small demo.");
        Files.writeString(project.resolve("pom.xml"), "<project></project>");
        Files.writeString(project.resolve("src/main/java/demo/DemoController.java"), """
                package demo;

                class DemoController {
                    String hello() {
                        return "hello";
                    }
                }
                """);

        ProjectReadingService service = new ProjectReadingService(
                new ProjectReadingPromptBuilder(),
                new MockAiGateway(),
                new LocalProjectScanner()
        );

        ProjectContext context = service.scan(new LocalProjectRequest(project.toString(), "understand demo"));
        ProjectQuestionAnswer answer = service.ask(new ProjectQuestionRequest(project.toString(), "入口在哪里？"));

        assertThat(context.projectName()).isEqualTo("demo-project");
        assertThat(context.readme()).contains("A small demo");
        assertThat(context.projectTree()).contains("README.md", "pom.xml");
        assertThat(context.sourceFiles()).extracting("path").contains("src/main/java/demo/DemoController.java");
        assertThat(answer.relatedFiles()).contains("src/main/java/demo/DemoController.java");
        assertThat(answer.promptPreview()).contains("入口在哪里？");
    }
}
