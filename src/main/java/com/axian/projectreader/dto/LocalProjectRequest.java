package com.axian.projectreader.dto;

import jakarta.validation.constraints.NotBlank;

public record LocalProjectRequest(
        @NotBlank String projectPath,
        @NotBlank String learningGoal
) {
}
