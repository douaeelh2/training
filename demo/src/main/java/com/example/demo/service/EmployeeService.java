package com.example.demo.service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.entities.Department;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.entities.Employee;
import com.example.demo.repositories.DepRepository;
import com.example.demo.service.dtos.EmployeeDto;
import com.example.demo.service.dtos.EmployeeFilter;
import com.example.demo.service.mappers.EmployeeMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService {

    private EmployeeDao employeeDao;
    private EmployeeMapper employeeMapper;
    private DepRepository depRepository;


    public EmployeeDto findById(Long id) {
        log.info("getting by id");
        var emp= employeeDao.getEmployeRepository().findById(id)
                .orElseThrow(DataNotFoundException::new);

        return employeeMapper.toDto(emp);
    }

//    @Transactional
//    public EmployeeDto save(EmployeeDto employeeDto) {
//        var emp=employeeMapper.toEntity(employeeDto);
//        Long depId=employeeDto.depId();
//
//        if(!depRepository.existsById(depId))
//            throw new DataNotFoundException("Dep  not found");
//
//        emp.setDepartment(
//                depRepository.getReferenceById(depId)
//        );
//
//        return employeeMapper.toDto(
//                employeeDao.getEmployeRepository().save(emp)
//        );
//    }

    @Transactional
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = employeeMapper.toEntity(employeeDto);

        Department department = depRepository.getReferenceById(employeeDto.depId());

        if (department == null) {
            throw new DataNotFoundException("Department not found");
        }

        employee.setDepartment(department);

        Employee savedEmployee = employeeDao.getEmployeRepository().save(employee);
        return employeeMapper.toDto(savedEmployee);
    }

    @Transactional(readOnly = true)
    public Page<EmployeeDto> findAll(EmployeeFilter employeeFilter) {

        return employeeDao.findAll(employeeFilter);
    }

    public List<Employee> findAllWithDepartment() {

        return employeeDao.getEmployeRepository().findAllWithDepartment();

    }

    @Transactional
    public EmployeeDto update(Long id,EmployeeDto employeeDto) {

        var emp=employeeDao.getEmployeRepository().findById(id)
                .orElseThrow(DataNotFoundException::new);

        Long depId=employeeDto.depId();

        if(!depRepository.existsById(depId))
            throw new DataNotFoundException("Dep  not found");

        emp.setDepartment(depRepository.getReferenceById(depId));

        employeeMapper.updateEntityFromDto(employeeDto,emp);

        return employeeDto;
    }
}
