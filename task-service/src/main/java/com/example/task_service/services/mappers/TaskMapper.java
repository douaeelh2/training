package com.example.task_service.services.mappers;


import com.example.task_service.entities.Task;
import com.example.task_service.services.dtos.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "technologies", source = "technologies")
    @Mapping(target = "employee", ignore = true)
    Task toEntity(TaskDto taskDto);

    @Mapping(target = "technologies", source = "technologies")
    TaskDto toDto(Task task);

    @Mapping(target = "employee", ignore = true)
    void updateEntityFromDto(TaskDto taskDto, @MappingTarget Task task);
}
