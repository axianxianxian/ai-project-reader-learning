package com.axian.projectreader.app;

import com.axian.projectreader.ai.AiGateway;
import com.axian.projectreader.ai.ProjectReadingPromptBuilder;
import com.axian.projectreader.api.LocalProjectRequest;
import com.axian.projectreader.api.ProjectQuestionRequest;
import com.axian.projectreader.api.ProjectReadingRequest;
import com.axian.projectreader.domain.ProjectContext;
import com.axian.projectreader.domain.ProjectQuestionAnswer;
import com.axian.projectreader.domain.ProjectReadingReport;
import org.springframework.stereotype.Service;

@Service
public class ProjectReadingService {

    private final ProjectReadingPromptBuilder promptBuilder;
    private final AiGateway aiGateway;
    private final LocalProjectScanner localProjectScanner;

    public ProjectReadingService(
            ProjectReadingPromptBuilder promptBuilder,
            AiGateway aiGateway,
            LocalProjectScanner localProjectScanner
    ) {
        this.promptBuilder = promptBuilder;
        this.aiGateway = aiGateway;
        this.localProjectScanner = localProjectScanner;
    }

    public ProjectReadingReport analyze(ProjectReadingRequest request) {
        String prompt = promptBuilder.build(request);
        return aiGateway.analyzeProject(request, prompt);
    }

    public ProjectContext scan(LocalProjectRequest request) {
        return localProjectScanner.scan(request.projectPath());
    }

    public ProjectReadingReport analyzeLocal(LocalProjectRequest request) {
        ProjectContext context = localProjectScanner.scan(request.projectPath());
        ProjectReadingRequest readingRequest = new ProjectReadingRequest(
                context.projectName(),
                request.learningGoal(),
                context.readme(),
                context.projectTree(),
                context.buildFile()
        );
        return analyze(readingRequest);
    }

    public ProjectQuestionAnswer ask(ProjectQuestionRequest request) {
        ProjectContext context = localProjectScanner.scan(request.projectPath());
        String prompt = promptBuilder.buildQuestionPrompt(request, context);
        return aiGateway.answerProjectQuestion(request, context, prompt);
    }
}
