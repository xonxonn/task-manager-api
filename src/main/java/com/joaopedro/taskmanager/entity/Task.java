
package com.joaopedro.taskmanager.entity;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;


@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Size(max = 100, message = "O título deve ter no máximo 100 caracteres")
    private String title;
    
    private String description;
    
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;
    
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;

    public Task() 
    {
    	
    }

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public TaskStatus getStatus() 
    {
        return status;
    }

    public void setStatus(TaskStatus status) 
    {
        this.status = status;
    }

    public TaskPriority  getPriority() 
    {
        return priority;
    }

    public void setPriority(TaskPriority priority) 
    {
        this.priority = priority;
    }

    public LocalDateTime getDueDate() 
    {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) 
    {
        this.dueDate = dueDate;
    }

    public LocalDateTime getCreatedAt() 
    {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) 
    {
        this.createdAt = createdAt;
    }
}
