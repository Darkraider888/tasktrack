package com.mehedi.tasktrack.controller;

import com.mehedi.tasktrack.model.Task;
import com.mehedi.tasktrack.repository.TaskRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    // ============================================
    // DASHBOARD / READ
    // ============================================

    @GetMapping("/")
    public String home(Model model) {

        List<Task> tasks = taskRepository.findAll();

        long pending = tasks.stream()
                .filter(task ->
                        "Pending".equals(task.getStatus()))
                .count();

        long inProgress = tasks.stream()
                .filter(task ->
                        "In Progress".equals(task.getStatus()))
                .count();

        long completed = tasks.stream()
                .filter(task ->
                        "Completed".equals(task.getStatus()))
                .count();

        model.addAttribute("tasks", tasks);

        model.addAttribute(
                "total",
                tasks.size()
        );

        model.addAttribute(
                "pending",
                pending
        );

        model.addAttribute(
                "inProgress",
                inProgress
        );

        model.addAttribute(
                "completed",
                completed
        );

        return "index";
    }


    // ============================================
    // ADD NEW TASK PAGE
    // ============================================

    @GetMapping("/add")
    public String addTask(Model model) {

        Task task = new Task();

        task.setPriority("Medium");
        task.setStatus("Pending");

        model.addAttribute(
                "task",
                task
        );

        return "form";
    }


    // ============================================
    // EDIT TASK
    //
    // IMPORTANT:
    // Uses the SAME form.html page.
    // Existing data is automatically loaded.
    // ============================================

    @GetMapping("/edit/{id}")
    public String editTask(
            @PathVariable String id,
            Model model) {

        Task task = taskRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Task not found: " + id
                        )
                );

        model.addAttribute(
                "task",
                task
        );

        return "form";
    }


    // ============================================
    // CREATE OR UPDATE
    //
    // New task:
    // id == null
    // MongoDB creates a new ID.
    //
    // Existing task:
    // id exists
    // MongoDB updates the same document.
    // ============================================

    @PostMapping("/save")
    public String saveTask(
            @ModelAttribute Task task) {

        if (task.getId() != null
                && task.getId().trim().isEmpty()) {

            task.setId(null);
        }

        taskRepository.save(task);

        return "redirect:/";
    }


    // ============================================
    // MARK COMPLETE
    // ============================================

    @PostMapping("/complete/{id}")
    public String completeTask(
            @PathVariable String id) {

        taskRepository
                .findById(id)
                .ifPresent(task -> {

                    task.setStatus(
                            "Completed"
                    );

                    taskRepository.save(task);

                });

        return "redirect:/";
    }


    // ============================================
    // DELETE
    // ============================================

    @PostMapping("/delete/{id}")
    public String deleteTask(
            @PathVariable String id) {

        if (taskRepository.existsById(id)) {

            taskRepository.deleteById(id);

        }

        return "redirect:/";
    }
}