package com.example.task_service.controllers;


import com.example.task_service.entities.Task;
import com.example.task_service.services.TaskService;
import com.example.task_service.services.dtos.TaskDto;
import com.example.task_service.services.dtos.TaskFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;


    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id)
    {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        TaskDto createdTask = taskService.createTask(taskDto);
        return ResponseEntity.ok(createdTask);
    }

    @PutMapping
    public ResponseEntity<Void> updateTask(@RequestBody TaskDto taskDto) {
        taskService.updateTask(taskDto);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Set<TaskDto>> getAllTasksWithTechnologies() {
        Set<TaskDto> tasks = taskService.getAllTasksWithTechnologies();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<TaskDto>> findAllTasks(@RequestBody TaskFilter taskFilter) {
        Page<TaskDto> tasks = taskService.findAllTasks(taskFilter);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/{taskId}/assign/{employeeId}")
    public ResponseEntity<TaskDto> assignEmployeeToTask(@PathVariable Long taskId, @PathVariable Long employeeId) {
        TaskDto updatedTask = taskService.assignEmployeeToTask(taskId, employeeId);
        return ResponseEntity.ok(updatedTask);
    }
}
