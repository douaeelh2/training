package com.example.task_service.employee.service;

import com.example.task_service.employee.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(name = "demo", url = "http://localhost:5959")
public interface EmployeeRestClient {

    @GetMapping("/employees/{id}")
    EmployeeDto getEmployeeById(@PathVariable("id") Long id);
}


