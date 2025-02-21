package com.example.task_service.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee{
    private long id;
    private String name;
    private int age;
    private String role;
    private String email;
    private int experienceYears;
}