package com.axian.projectreader.ai;

import com.axian.projectreader.dto.ProjectQuestionRequest;
import com.axian.projectreader.dto.ProjectReadingRequest;
import com.axian.projectreader.model.ProjectContext;
import com.axian.projectreader.model.ProjectQuestionAnswer;
import com.axian.projectreader.model.ProjectReadingReport;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "project-reader.ai.provider", havingValue = "spring-ai")
public class SpringAiGateway implements AiGateway {

    private final ChatClient chatClient;

    public SpringAiGateway(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public ProjectReadingReport analyzeProject(ProjectReadingRequest request, String prompt) {
        return chatClient.prompt()
                .system("""
                        You are an AI project reading assistant.
                        Return a ProjectReadingReport object.
                        Keep the main path short, concrete, and useful for a backend engineer.
                        """)
                .user(prompt)
                .call()
                .entity(ProjectReadingReport.class);
    }

    @Override
    public ProjectQuestionAnswer answerProjectQuestion(ProjectQuestionRequest request, ProjectContext context, String prompt) {
        return chatClient.prompt()
                .system("""
                        You are an AI code reading assistant.
                        Return a ProjectQuestionAnswer object.
                        Only use the provided project context.
                        If the context is insufficient, explain what is missing.
                        """)
                .user(prompt)
                .call()
                .entity(ProjectQuestionAnswer.class);
    }
}
