package com.example.demo.service.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
public class EmployeeFilter {
    private Long id;
    private String name;
    private Long depId;
    private Pageable pageable;
}
