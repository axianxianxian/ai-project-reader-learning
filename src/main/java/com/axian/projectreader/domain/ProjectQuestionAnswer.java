package com.axian.projectreader.domain;

import java.util.List;

public record ProjectQuestionAnswer(
        String shortAnswer,
        List<String> relatedFiles,
        List<String> logicTrace,
        List<String> missingContext,
        String nextReadAction,
        String promptPreview
) {
}
