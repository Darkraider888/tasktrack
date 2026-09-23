package com.mehedi.tasktrack.repository;

import com.mehedi.tasktrack.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {

}