package com.joaopedro.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaopedro.taskmanager.entity.Task;
import com.joaopedro.taskmanager.entity.TaskStatus;
import com.joaopedro.taskmanager.entity.TaskPriority;

public interface TaskRepository extends JpaRepository<Task, Long> 
{

    List<Task> findByStatus(TaskStatus status);

    List<Task> findByPriority(TaskPriority priority);

    List<Task> findByStatusAndPriority(TaskStatus status, TaskPriority priority);
}