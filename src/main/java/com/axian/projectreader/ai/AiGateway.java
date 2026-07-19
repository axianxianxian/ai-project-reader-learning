package com.axian.projectreader.ai;

import com.axian.projectreader.dto.ProjectQuestionRequest;
import com.axian.projectreader.dto.ProjectReadingRequest;
import com.axian.projectreader.model.ProjectContext;
import com.axian.projectreader.model.ProjectQuestionAnswer;
import com.axian.projectreader.model.ProjectReadingReport;

public interface AiGateway {

    ProjectReadingReport analyzeProject(ProjectReadingRequest request, String prompt);

    ProjectQuestionAnswer answerProjectQuestion(ProjectQuestionRequest request, ProjectContext context, String prompt);
}
