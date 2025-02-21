package com.example.task_service.services.mappers;


import com.example.task_service.entities.Task;
import com.example.task_service.services.dtos.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "technologies", source = "technologies")
    Task toEntity(TaskDto taskDto);

    @Mapping(target = "technologies", source = "technologies")
    TaskDto toDto(Task task);

    void updateEntityFromDto(TaskDto taskDto, @MappingTarget Task task);
}
