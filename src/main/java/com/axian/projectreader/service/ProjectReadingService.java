package com.axian.projectreader.service;

import com.axian.projectreader.ai.AiGateway;
import com.axian.projectreader.ai.ProjectReadingPromptBuilder;
import com.axian.projectreader.dto.LocalProjectRequest;
import com.axian.projectreader.dto.ProjectQuestionRequest;
import com.axian.projectreader.dto.ProjectReadingRequest;
import com.axian.projectreader.model.ProjectContext;
import com.axian.projectreader.model.ProjectQuestionAnswer;
import com.axian.projectreader.model.ProjectReadingReport;
import com.axian.projectreader.scanner.LocalProjectScanner;
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
