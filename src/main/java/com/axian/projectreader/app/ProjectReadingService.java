package com.axian.projectreader.app;

import com.axian.projectreader.ai.AiGateway;
import com.axian.projectreader.ai.ProjectReadingPromptBuilder;
import com.axian.projectreader.api.ProjectReadingRequest;
import com.axian.projectreader.domain.ProjectReadingReport;
import org.springframework.stereotype.Service;

@Service
public class ProjectReadingService {

    private final ProjectReadingPromptBuilder promptBuilder;
    private final AiGateway aiGateway;

    public ProjectReadingService(ProjectReadingPromptBuilder promptBuilder, AiGateway aiGateway) {
        this.promptBuilder = promptBuilder;
        this.aiGateway = aiGateway;
    }

    public ProjectReadingReport analyze(ProjectReadingRequest request) {
        String prompt = promptBuilder.build(request);
        return aiGateway.analyzeProject(request, prompt);
    }
}
