package com.example.task_service.services.dtos;

import com.example.task_service.entities.Technology;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


import java.util.Set;

@Getter
@AllArgsConstructor
public class TaskDto {
    long id;
    private String name;
    private Set<Technology> technologies;
   // private long employeeId;
    private String description;
    private String status;

}
