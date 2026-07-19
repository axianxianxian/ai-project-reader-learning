package com.axian.projectreader.model;

public record SourceFileContext(
        String path,
        String role,
        String contentPreview
) {
}
