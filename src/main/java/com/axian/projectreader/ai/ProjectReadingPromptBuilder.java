package com.axian.projectreader.ai;

import com.axian.projectreader.dto.ProjectQuestionRequest;
import com.axian.projectreader.dto.ProjectReadingRequest;
import com.axian.projectreader.model.ProjectContext;
import com.axian.projectreader.model.SourceFileContext;
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

    public String buildQuestionPrompt(ProjectQuestionRequest request, ProjectContext context) {
        return """
                You are an AI code reading assistant.

                User question:
                %s

                Project context:
                Project name: %s
                Root path: %s

                README:
                %s

                Build file:
                %s

                Project tree:
                %s

                Key source files:
                %s

                Answer rules:
                1. Only answer from the provided context.
                2. If context is not enough, say exactly what is missing.
                3. Start with a short answer.
                4. Then list related files.
                5. Then explain the logic trace from entry to output.
                6. End with the next file or action the learner should inspect.
                """.formatted(
                request.question(),
                context.projectName(),
                context.rootPath(),
                context.readme(),
                context.buildFile(),
                context.projectTree(),
                sourceFileText(context)
        );
    }

    private String sourceFileText(ProjectContext context) {
        return context.sourceFiles()
                .stream()
                .map(this::sourceFileText)
                .reduce((left, right) -> left + "\n\n" + right)
                .orElse("");
    }

    private String sourceFileText(SourceFileContext sourceFile) {
        return """
                File: %s
                Role: %s
                Content:
                %s
                """.formatted(sourceFile.path(), sourceFile.role(), sourceFile.contentPreview());
    }
}
