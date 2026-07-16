package com.axian.projectreader.ai;

import com.axian.projectreader.api.ProjectQuestionRequest;
import com.axian.projectreader.api.ProjectReadingRequest;
import com.axian.projectreader.domain.ProjectContext;
import com.axian.projectreader.domain.ProjectQuestionAnswer;
import com.axian.projectreader.domain.ProjectReadingReport;

public interface AiGateway {

    ProjectReadingReport analyzeProject(ProjectReadingRequest request, String prompt);

    ProjectQuestionAnswer answerProjectQuestion(ProjectQuestionRequest request, ProjectContext context, String prompt);
}
