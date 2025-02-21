package com.example.task_service.services.dtos;

import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.util.Set;

@Getter
public class TaskFilter {
    private long id;
    private String name;
    private String description;
    private String status;
    private Set<String> technologyNames;
    private Pageable pageable;

}
