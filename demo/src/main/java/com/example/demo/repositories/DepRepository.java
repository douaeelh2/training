package com.example.demo.repositories;

import com.example.demo.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepRepository extends JpaRepository<Department,Long> {

    @Query("select d from Department d join fetch d.employees")
    List<Department> findAllWithEmp();
}
