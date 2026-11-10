
package com.joaopedro.taskmanager.service;

import com.joaopedro.taskmanager.exception.TaskNotFoundException;
import com.joaopedro.taskmanager.exception.InvalidStatusTransitionException;
import com.joaopedro.taskmanager.entity.TaskStatus;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.joaopedro.taskmanager.entity.Task;
import com.joaopedro.taskmanager.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task create(Task task) 
    {
        task.setId(null);
        task.setCreatedAt(LocalDateTime.now());
        task.setStatus(TaskStatus.PENDENTE);
        return taskRepository.save(task);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task update(Long id, Task updatedTask) {
        Task task = findById(id);
        
        if (updatedTask.getStatus() != null && !task.getStatus().canTransitionTo(updatedTask.getStatus())) 
        {
            throw new InvalidStatusTransitionException(task.getStatus(), updatedTask.getStatus());
        }

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        
        if (updatedTask.getStatus() != null) 
        {
            task.setStatus(updatedTask.getStatus());
        }
        
        task.setPriority(updatedTask.getPriority());
        task.setDueDate(updatedTask.getDueDate());

        return taskRepository.save(task);
    }

    public void delete(Long id) {
        Task task = findById(id);
        taskRepository.delete(task);
    }
}
