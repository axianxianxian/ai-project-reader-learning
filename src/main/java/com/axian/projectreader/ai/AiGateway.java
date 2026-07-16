package com.axian.projectreader.ai;

import com.axian.projectreader.api.ProjectReadingRequest;
import com.axian.projectreader.domain.ProjectReadingReport;

public interface AiGateway {

    ProjectReadingReport analyzeProject(ProjectReadingRequest request, String prompt);
}
