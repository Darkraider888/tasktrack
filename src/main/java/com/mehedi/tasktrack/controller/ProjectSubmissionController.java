package com.mehedi.tasktrack.controller;

import com.mehedi.tasktrack.model.ProjectSubmission;
import com.mehedi.tasktrack.repository.ProjectSubmissionRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectSubmissionController {

    private final ProjectSubmissionRepository projectSubmissionRepository;

    public ProjectSubmissionController(
            ProjectSubmissionRepository projectSubmissionRepository) {

        this.projectSubmissionRepository =
                projectSubmissionRepository;
    }


    // =========================================
    // PROJECT BOARD
    // =========================================

    @GetMapping
    public String projectBoard(Model model) {

        loadProjectBoard(model);

        model.addAttribute(
                "submission",
                new ProjectSubmission()
        );

        model.addAttribute(
                "editing",
                false
        );

        return "projects";
    }


    // =========================================
    // OPEN EDIT MODE
    // =========================================

    @GetMapping("/edit/{id}")
    public String editProject(
            @PathVariable String id,
            Model model) {

        ProjectSubmission submission =
                projectSubmissionRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Project not found"
                                )
                        );

        loadProjectBoard(model);

        model.addAttribute(
                "submission",
                submission
        );

        model.addAttribute(
                "editing",
                true
        );

        return "projects";
    }


    // =========================================
    // CREATE PROJECT
    // =========================================

    @PostMapping
    public String submitProject(
            @ModelAttribute ProjectSubmission submission,
            RedirectAttributes redirectAttributes) {

        String error =
                prepareAndValidateSubmission(
                        submission
                );

        if (error != null) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    error
            );

            return "redirect:/projects";
        }

        // Always create a new MongoDB document
        submission.setId(null);

        submission.setCreatedAt(
                System.currentTimeMillis()
        );

        projectSubmissionRepository.save(
                submission
        );

        redirectAttributes.addFlashAttribute(
                "success",
                "Project published successfully."
        );

        return "redirect:/projects";
    }


    // =========================================
    // UPDATE PROJECT
    // =========================================

    @PostMapping("/update/{id}")
    public String updateProject(
            @PathVariable String id,
            @ModelAttribute ProjectSubmission formSubmission,
            RedirectAttributes redirectAttributes) {

        ProjectSubmission existing =
                projectSubmissionRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Project not found"
                                )
                        );

        String error =
                prepareAndValidateSubmission(
                        formSubmission
                );

        if (error != null) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    error
            );

            return "redirect:/projects/edit/" + id;
        }

        existing.setStudentName(
                formSubmission.getStudentName()
        );

        existing.setProjectTitle(
                formSubmission.getProjectTitle()
        );

        existing.setLiveUrl(
                formSubmission.getLiveUrl()
        );

        existing.setGithubUrl(
                formSubmission.getGithubUrl()
        );

        existing.setLinkedinUrl(
                formSubmission.getLinkedinUrl()
        );

        projectSubmissionRepository.save(
                existing
        );

        redirectAttributes.addFlashAttribute(
                "success",
                "Project updated successfully."
        );

        return "redirect:/projects";
    }


    // =========================================
    // DELETE PROJECT
    // =========================================

    @PostMapping("/delete/{id}")
    public String deleteProject(
            @PathVariable String id,
            RedirectAttributes redirectAttributes) {

        if (projectSubmissionRepository.existsById(id)) {

            projectSubmissionRepository.deleteById(id);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Project removed from the board."
            );
        }

        return "redirect:/projects";
    }


    // =========================================
    // LOAD BOARD DATA
    // =========================================

    private void loadProjectBoard(Model model) {

        List<ProjectSubmission> submissions =
                projectSubmissionRepository
                        .findAllByOrderByCreatedAtDesc();

        model.addAttribute(
                "submissions",
                submissions
        );

        model.addAttribute(
                "submissionCount",
                submissions.size()
        );
    }


    // =========================================
    // VALIDATE + CLEAN FORM
    // =========================================

    private String prepareAndValidateSubmission(
            ProjectSubmission submission) {

        String studentName =
                cleanText(
                        submission.getStudentName()
                );

        String projectTitle =
                cleanText(
                        submission.getProjectTitle()
                );

        String liveUrl =
                normalizeHttpUrl(
                        submission.getLiveUrl()
                );

        String githubUrl =
                normalizeOptionalHttpUrl(
                        submission.getGithubUrl()
                );

        String linkedinUrl =
                normalizeOptionalHttpUrl(
                        submission.getLinkedinUrl()
                );


        if (studentName.isBlank()) {

            return "Student name is required.";
        }


        if (projectTitle.isBlank()) {

            return "Project title is required.";
        }


        if (liveUrl == null) {

            return "Please enter a valid deployed website URL.";
        }


        if (githubUrl == null) {

            return "Please enter a valid GitHub URL or leave it empty.";
        }


        if (linkedinUrl == null) {

            return "Please enter a valid LinkedIn URL or leave it empty.";
        }


        submission.setStudentName(
                studentName
        );

        submission.setProjectTitle(
                projectTitle
        );

        submission.setLiveUrl(
                liveUrl
        );

        submission.setGithubUrl(
                githubUrl
        );

        submission.setLinkedinUrl(
                linkedinUrl
        );

        return null;
    }


    // =========================================
    // CLEAN TEXT
    // =========================================

    private String cleanText(String value) {

        return value == null
                ? ""
                : value.trim();
    }


    // =========================================
    // OPTIONAL URL
    // =========================================

    private String normalizeOptionalHttpUrl(
            String value) {

        if (value == null
                || value.trim().isEmpty()) {

            return "";
        }

        return normalizeHttpUrl(
                value
        );
    }


    // =========================================
    // HTTP / HTTPS URL VALIDATION
    // =========================================

    private String normalizeHttpUrl(
            String value) {

        if (value == null
                || value.trim().isEmpty()) {

            return null;
        }


        String url =
                value.trim();


        if (!url.regionMatches(
                true,
                0,
                "http://",
                0,
                7)

                &&

                !url.regionMatches(
                        true,
                        0,
                        "https://",
                        0,
                        8)) {

            url =
                    "https://" + url;
        }


        try {

            URI uri =
                    URI.create(url);

            String scheme =
                    uri.getScheme();

            String host =
                    uri.getHost();


            if (scheme == null
                    || host == null) {

                return null;
            }


            if (!scheme.equalsIgnoreCase(
                    "http"
            )

                    &&

                    !scheme.equalsIgnoreCase(
                            "https"
                    )) {

                return null;
            }


            return uri.toString();

        } catch (
                IllegalArgumentException exception
        ) {

            return null;
        }
    }
}