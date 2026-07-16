package com.axian.projectreader.ai;

import com.axian.projectreader.api.ProjectReadingRequest;
import org.springframework.stereotype.Component;

@Component
public class ProjectReadingPromptBuilder {

    public String build(ProjectReadingRequest request) {
        return """
                You are an AI project reading assistant.

                Goal:
                Help the learner understand a technical project by producing a structured reading report.

                Rules:
                - Be concrete.
                - Prefer a short main path and keep full details available.
                - Explain what to read first, why it matters, and what demo to run.
                - Return a structured result, not a loose essay.

                Project name:
                %s

                Learning goal:
                %s

                README:
                %s

                Project tree:
                %s

                Config snippet:
                %s
                """.formatted(
                request.projectName(),
                request.learningGoal(),
                request.readme(),
                request.projectTree(),
                request.configSnippet() == null ? "" : request.configSnippet()
        );
    }
}
