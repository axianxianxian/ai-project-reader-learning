package com.axian.projectreader.model;

import java.util.List;

public record ProjectReadingReport(
        String projectName,
        String summary,
        List<FileToRead> readingPath,
        List<StepAction> demoPlan,
        List<RiskNote> risks,
        String nextAction,
        String promptPreview
) {
}
