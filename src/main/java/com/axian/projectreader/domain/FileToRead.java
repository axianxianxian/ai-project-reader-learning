package com.axian.projectreader.domain;

public record FileToRead(
        String path,
        String reason,
        int order
) {
}
