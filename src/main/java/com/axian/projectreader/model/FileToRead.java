package com.axian.projectreader.model;

public record FileToRead(
        String path,
        String reason,
        int order
) {
}
