package com.example.demo.service.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmployeeDto(long id, @NotBlank String name,int age,String role,String email,int experienceYears, @NotNull long depId) {
}
