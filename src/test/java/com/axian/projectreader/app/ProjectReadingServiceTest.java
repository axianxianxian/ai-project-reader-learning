package com.axian.projectreader.app;

import com.axian.projectreader.ai.MockAiGateway;
import com.axian.projectreader.ai.ProjectReadingPromptBuilder;
import com.axian.projectreader.api.ProjectReadingRequest;
import com.axian.projectreader.domain.ProjectReadingReport;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectReadingServiceTest {

    @Test
    void analyzesProjectIntoStructuredReport() {
        ProjectReadingService service = new ProjectReadingService(
                new ProjectReadingPromptBuilder(),
                new MockAiGateway()
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
}
