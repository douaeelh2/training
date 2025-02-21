package com.example.demo.dao;

import com.example.demo.entities.QEmployee;
import com.example.demo.repositories.EmployeRepository;
import com.example.demo.service.dtos.EmployeeDto;
import com.example.demo.service.dtos.EmployeeFilter;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@Getter
public class EmployeeDao extends AbstractDao {

    @Autowired
    private EmployeRepository employeRepository;

    /**
     * Finds all employees based on the given filter criteria.
     *
     * @param employeeFilter - filter containing search criteria like department ID and name.
     * @return Page<EmployeeDto> - paginated list of employees.
     */
    public Page<EmployeeDto> findAll(EmployeeFilter employeeFilter) {
        // Create an alias for the Employee entity using QueryDSL
        QEmployee employee = QEmployee.employee;

        // Create a JPAQuery to map only specific fields into EmployeeDto
        JPAQuery<EmployeeDto> query = getFactory()
                .select(Projections.constructor(
                        EmployeeDto.class,
                        employee.id,
                        employee.name,
                        employee.age,
                        employee.role,
                        employee.email,
                        employee.experienceYears,
                        employee.department.id)
                ).from(employee);

        // Filtering by department ID if provided
        var depId = employeeFilter.getDepId();
        if (depId != null)
            query.where(employee.department.id.eq(depId));

        // Filtering by name if provided (case-insensitive search)
        var name = employeeFilter.getName();
        if (StringUtils.hasText(name))
            query.where(employee.name.contains(name));

        // Retrieve pagination information from the filter
        Pageable pageable = employeeFilter.getPageable();

        // Count the total number of matching records (used for pagination)
        long count = query.select(employee.id.count()).fetchOne();

        // Execute the query with pagination
        List<EmployeeDto> employeeDtos = query
                .limit(pageable.getPageSize()) // Limit the number of results per page
                .offset(pageable.getOffset())  // Set the starting point for pagination
                .fetch();

        // Return the results as a paginated response
        return new PageImpl<>(employeeDtos, pageable, count);
    }
}
