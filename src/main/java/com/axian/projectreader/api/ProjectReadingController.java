package com.axian.projectreader.api;

import com.axian.projectreader.app.ProjectReadingService;
import com.axian.projectreader.domain.ProjectReadingReport;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project-reading")
public class ProjectReadingController {

    private final ProjectReadingService projectReadingService;

    public ProjectReadingController(ProjectReadingService projectReadingService) {
        this.projectReadingService = projectReadingService;
    }

    @PostMapping("/analyze")
    public ProjectReadingReport analyze(@Valid @RequestBody ProjectReadingRequest request) {
        return projectReadingService.analyze(request);
    }
}
