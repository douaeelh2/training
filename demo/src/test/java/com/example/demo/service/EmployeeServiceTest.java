package com.example.demo.service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.repositories.EmployeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    EmployeeDao employeeDao;
    @Mock
    EmployeRepository employeRepository;

    @InjectMocks
    EmployeeService employeeService;


    @Test
    void testFindById_should_throw_DataNotFoundException()
    {
        ///AAA
        // arrange
        when(employeeDao.getEmployeRepository()).thenReturn(employeRepository);
        when(employeRepository.findById(anyLong())).thenReturn(Optional.empty());
        // act
        Executable  executable=()->employeeService.findById(1L);

        // assert
        assertThrows(DataNotFoundException.class,executable);
    }

}