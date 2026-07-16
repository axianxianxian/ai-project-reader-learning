package com.axian.projectreader.domain;

public record SourceFileContext(
        String path,
        String role,
        String contentPreview
) {
}
