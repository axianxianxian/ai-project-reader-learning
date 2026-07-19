package com.axian.projectreader.dto;

import jakarta.validation.constraints.NotBlank;

public record ProjectQuestionRequest(
        @NotBlank String projectPath,
        @NotBlank String question
) {
}
