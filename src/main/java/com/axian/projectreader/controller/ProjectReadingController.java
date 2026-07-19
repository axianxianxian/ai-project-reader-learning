package com.axian.projectreader.controller;

import com.axian.projectreader.dto.LocalProjectRequest;
import com.axian.projectreader.dto.ProjectQuestionRequest;
import com.axian.projectreader.dto.ProjectReadingRequest;
import com.axian.projectreader.model.ProjectContext;
import com.axian.projectreader.model.ProjectQuestionAnswer;
import com.axian.projectreader.model.ProjectReadingReport;
import com.axian.projectreader.service.ProjectReadingService;
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

    @PostMapping("/scan")
    public ProjectContext scan(@Valid @RequestBody LocalProjectRequest request) {
        return projectReadingService.scan(request);
    }

    @PostMapping("/analyze-local")
    public ProjectReadingReport analyzeLocal(@Valid @RequestBody LocalProjectRequest request) {
        return projectReadingService.analyzeLocal(request);
    }

    @PostMapping("/ask")
    public ProjectQuestionAnswer ask(@Valid @RequestBody ProjectQuestionRequest request) {
        return projectReadingService.ask(request);
    }
}
