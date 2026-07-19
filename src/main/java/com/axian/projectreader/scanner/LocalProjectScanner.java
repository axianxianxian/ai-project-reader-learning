package com.axian.projectreader.scanner;

import com.axian.projectreader.model.ProjectContext;
import com.axian.projectreader.model.SourceFileContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class LocalProjectScanner {

    private static final int MAX_TREE_FILES = 80;
    private static final int MAX_SOURCE_FILES = 20;
    private static final int MAX_PREVIEW_CHARS = 4_000;

    public ProjectContext scan(String projectPath) {
        Path root = Path.of(projectPath).toAbsolutePath().normalize();
        if (!Files.isDirectory(root)) {
            throw new IllegalArgumentException("Project path must be an existing directory: " + root);
        }

        String readme = readFirstExisting(root, "README.md", "readme.md").orElse("");
        String buildFile = readFirstExisting(root, "pom.xml", "build.gradle", "build.gradle.kts").orElse("");
        String projectTree = buildProjectTree(root);
        List<SourceFileContext> sourceFiles = readSourceFiles(root);

        return new ProjectContext(
                root.getFileName().toString(),
                root.toString(),
                readme,
                buildFile,
                projectTree,
                sourceFiles
        );
    }

    private Optional<String> readFirstExisting(Path root, String... names) {
        for (String name : names) {
            Path file = root.resolve(name);
            if (Files.isRegularFile(file)) {
                return Optional.of(readPreview(file));
            }
        }
        return Optional.empty();
    }

    private String buildProjectTree(Path root) {
        try (Stream<Path> paths = Files.walk(root, 4)) {
            return paths
                    .filter(path -> !path.equals(root))
                    .filter(this::isUsefulPath)
                    .sorted()
                    .limit(MAX_TREE_FILES)
                    .map(root::relativize)
                    .map(Path::toString)
                    .toList()
                    .stream()
                    .reduce((left, right) -> left + "\n" + right)
                    .orElse("");
        } catch (IOException e) {
            throw new IllegalStateException("Failed to scan project tree: " + root, e);
        }
    }

    private List<SourceFileContext> readSourceFiles(Path root) {
        try (Stream<Path> paths = Files.walk(root.resolve("src"), 8)) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter(this::isJavaFile)
                    .filter(this::isLikelyMainFlowFile)
                    .sorted(Comparator.comparing(Path::toString))
                    .limit(MAX_SOURCE_FILES)
                    .map(path -> new SourceFileContext(
                            root.relativize(path).toString(),
                            classify(path),
                            readPreview(path)
                    ))
                    .toList();
        } catch (IOException e) {
            return List.of();
        }
    }

    private boolean isUsefulPath(Path path) {
        String value = path.toString();
        return !value.contains("/target/")
                && !value.contains("/.git/")
                && !value.contains("/.idea/")
                && !value.contains("/node_modules/")
                && !value.contains("/build/");
    }

    private boolean isJavaFile(Path path) {
        return path.getFileName().toString().endsWith(".java");
    }

    private boolean isLikelyMainFlowFile(Path path) {
        String name = path.getFileName().toString().toLowerCase(Locale.ROOT);
        return name.contains("controller")
                || name.contains("service")
                || name.contains("gateway")
                || name.contains("client")
                || name.contains("builder")
                || name.contains("scanner")
                || name.contains("application");
    }

    private String classify(Path path) {
        String name = path.getFileName().toString().toLowerCase(Locale.ROOT);
        if (name.contains("controller")) {
            return "入口层：接收用户请求";
        }
        if (name.contains("service")) {
            return "业务编排层：串起主流程";
        }
        if (name.contains("gateway") || name.contains("client")) {
            return "外部能力边界：模型或第三方调用";
        }
        if (name.contains("builder")) {
            return "构造层：组织 prompt 或上下文";
        }
        if (name.contains("scanner")) {
            return "工具层：读取本地项目资料";
        }
        if (name.contains("application")) {
            return "启动入口";
        }
        return "待判断";
    }

    private String readPreview(Path file) {
        try {
            String content = Files.readString(file, StandardCharsets.UTF_8);
            if (content.length() <= MAX_PREVIEW_CHARS) {
                return content;
            }
            return content.substring(0, MAX_PREVIEW_CHARS) + "\n...<truncated>";
        } catch (IOException e) {
            return "";
        }
    }
}
