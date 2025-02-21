package com.example.demo.resources;

import com.example.demo.entities.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.dtos.EmployeeDto;
import com.example.demo.service.dtos.EmployeeFilter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeResource {

    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable Long id)
    {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeDto>> findAll(EmployeeFilter employeeFilter, Pageable pageable)
    {
        employeeFilter.setPageable(pageable);
        return ResponseEntity.ok(employeeService.findAll(employeeFilter));
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> save(@Valid @RequestBody EmployeeDto employeeDto)
    {
        return ResponseEntity.ok(employeeService.createEmployee(employeeDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(@PathVariable Long id,@Valid @RequestBody EmployeeDto employeeDto)
    {
        return ResponseEntity
                .ok(employeeService.update(id,employeeDto));
    }


    @GetMapping("/with-department")
    public ResponseEntity<List<Employee>> findAllWithDepartment() {
        List<Employee> employees = employeeService.findAllWithDepartment();

        return ResponseEntity.ok(employees);
    }

}
