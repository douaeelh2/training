package com.example.task_service.dao;

import com.example.task_service.entities.QTask;
import com.example.task_service.entities.QTechnology;
import com.example.task_service.repositories.TaskRepository;
import com.example.task_service.services.dtos.TaskDto;
import com.example.task_service.services.dtos.TaskFilter;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;

import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Repository
@Getter
public class TaskDao extends AbstractDao {

    private TaskRepository taskRepository;

    /**
     * Finds all tasks based on the given filter criteria.
     *
     * @param taskFilter - filter containing search criteria like task name and technology names.
     * @return Page<TaskDto> - paginated list of tasks.
     */
    public Page<TaskDto> findAll(TaskFilter taskFilter) {
        // Alias QueryDSL
        QTask task = QTask.task;
        QTechnology technology = QTechnology.technology;

        JPAQuery<TaskDto> query = getFactory()
                .select(Projections.constructor(
                        TaskDto.class,
                        task.id,
                        task.name,
                        task.technologies,
                        task.description,
                        task.status
                ))
                .from(task)
                .leftJoin(task.technologies, technology)
                .distinct();

        // Filter by task name
        var name = taskFilter.getName();
        if (StringUtils.hasText(name)) {
            query.where(task.name.contains(name));
        }

        // filter by technology names
        Set<String> technologyNames = taskFilter.getTechnologyNames();
        if (!CollectionUtils.isEmpty(technologyNames)) {
            query.where(technology.name.in(technologyNames));
        }

        // filter by status
        var status = taskFilter.getStatus();
        if (StringUtils.hasText(status)) {
            query.where(task.status.contains(status));
        }

        Pageable pageable = taskFilter.getPageable();

        //count paginated data with filter
        long count = getFactory()
                .select(task.id.countDistinct())
                .from(task)
                .leftJoin(task.technologies, technology)
                .where(technology.name.in(technologyNames))
                .fetchOne();

        // Exécuter la requête avec pagination
        List<TaskDto> taskDtos = query
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        return new PageImpl<>(taskDtos, pageable, count);
    }
}
