package com.example.task_service;


import com.example.task_service.exceptions.EntityNotFoundException;
import com.example.task_service.repositories.TaskRepository;

import com.example.task_service.services.TaskService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TaskServiceApplicationTests {

	@Mock
	private TaskRepository taskRepository;

	@InjectMocks
	private TaskService taskService;


	@Test
	void testDeleteTask_shouldThrowEntityNotFoundException() {
		// Arrange
		when(taskRepository.existsById(10L)).thenReturn(false);

		// Act
		Executable executable = () -> taskService.deleteTask(1L);

		// Assert
		assertThrows(EntityNotFoundException.class, executable);
	}



}
