package com.example.demo.service.mappers;

import com.example.demo.entities.Employee;
import com.example.demo.service.dtos.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(EmployeeDto employeeDto);
    EmployeeDto toDto(Employee employee);

    void updateEntityFromDto(EmployeeDto employeeDto,@MappingTarget Employee employee);
}
