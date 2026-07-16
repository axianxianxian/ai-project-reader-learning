package com.axian.projectreader.api;

import jakarta.validation.constraints.NotBlank;

public record ProjectReadingRequest(
        @NotBlank String projectName,
        @NotBlank String learningGoal,
        @NotBlank String readme,
        @NotBlank String projectTree,
        String configSnippet
) {
}
