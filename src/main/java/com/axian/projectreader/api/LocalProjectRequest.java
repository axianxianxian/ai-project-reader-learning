package com.axian.projectreader.api;

import jakarta.validation.constraints.NotBlank;

public record LocalProjectRequest(
        @NotBlank String projectPath,
        @NotBlank String learningGoal
) {
}
