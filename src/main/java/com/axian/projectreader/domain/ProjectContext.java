package com.axian.projectreader.domain;

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
