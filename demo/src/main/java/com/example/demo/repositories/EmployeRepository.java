package com.example.demo.repositories;

import com.example.demo.entities.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface EmployeRepository extends JpaRepository<Employee,Long> {


    @Query(value ="select  * from employee " ,countQuery = "select count(id) from employee",nativeQuery = true)
    List<Employee> findByNameL(Pageable pageable);

    @Query("SELECT e FROM Employee e JOIN FETCH e.department")
    List<Employee> findAllWithDepartment();
}
