package com.axian.projectreader.model;

import java.util.List;

public record ProjectContext(
        String projectName,
        String rootPath,
        String readme,
        String buildFile,
        String projectTree,
        List<SourceFileContext> sourceFiles
) {
}
