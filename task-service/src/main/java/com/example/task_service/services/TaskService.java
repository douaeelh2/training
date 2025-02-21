package com.example.task_service.services;


import com.example.task_service.dao.TaskDao;

import com.example.task_service.entities.Task;
import com.example.task_service.entities.Technology;
import com.example.task_service.model.Employee;
import com.example.task_service.repositories.TaskRepository;
import com.example.task_service.repositories.TechnologyRepository;
import com.example.task_service.restclients.EmployeeRestClient;
import com.example.task_service.services.dtos.TaskDto;
import com.example.task_service.services.dtos.TaskFilter;
import com.example.task_service.services.mappers.TaskMapper;
import com.example.task_service.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;
    private TechnologyRepository technologyRepository;
    private TaskMapper taskMapper;
    private TaskDao taskDao;
    private EmployeeRestClient employeeRestClient;


    public Task findById(@PathVariable long id){
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        Employee employee = employeeRestClient.getEmployeeById(existingTask.getEmployeeId());

        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }
             existingTask.setEmployee(employee);

             return existingTask;
    }
    @Transactional
    public TaskDto createTask(TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);

        Set<Technology> technologies = taskDto.getTechnologies().stream()
                .map(tech -> technologyRepository.findById(tech.getId())
                        .orElseGet(() -> technologyRepository.save(new Technology(tech.getName()))))
                .collect(Collectors.toSet());

        task.setTechnologies(technologies);

        Task savedTask = taskRepository.save(task);
        return taskMapper.toDto(savedTask);
    }

    @Transactional
    public Task updateTask(TaskDto updatedTaskDto) {

        Task existingTask = taskRepository.findById(updatedTaskDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));


        taskMapper.updateEntityFromDto(updatedTaskDto, existingTask);

        return taskRepository.save(existingTask);
    }


    @Transactional
    public void deleteTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new EntityNotFoundException("Task not found with ID: " + taskId);
        }
        taskRepository.deleteById(taskId);
    }


    public Set<TaskDto> getAllTasksWithTechnologies() {
        return taskRepository.findAllWithTechnologies().stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toSet());
    }


    public Page<TaskDto> findAllTasks(TaskFilter taskFilter) {
        return taskDao.findAll(taskFilter);
    }

    public TaskDto assignEmployeeToTask(Long taskId, Long employeeId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        Employee employee = employeeRestClient.getEmployeeById(employeeId);

        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }

        task.setEmployeeId(employee.getId());

        return taskMapper.toDto(taskRepository.save(task));
    }
}
