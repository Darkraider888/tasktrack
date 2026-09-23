package com.mehedi.tasktrack.repository;

import com.mehedi.tasktrack.model.ProjectSubmission;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProjectSubmissionRepository
        extends MongoRepository<ProjectSubmission, String> {

    List<ProjectSubmission> findAllByOrderByCreatedAtDesc();
}