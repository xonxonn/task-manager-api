package com.joaopedro.taskmanager.controller;

import org.springframework.web.bind.annotation.RequestParam;

import com.joaopedro.taskmanager.entity.TaskStatus;
import com.joaopedro.taskmanager.entity.TaskPriority;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaopedro.taskmanager.entity.Task;
import com.joaopedro.taskmanager.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Tarefas", description = "Operações de gerenciamento de tarefas")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Criar tarefa", description = "Cria uma nova tarefa. O status enviado é ignorado: toda nova tarefa nasce como PENDENTE.")
    @PostMapping
    public ResponseEntity<Task> create(@Valid @RequestBody Task task) {
        Task created = taskService.create(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Listar todas as tarefas", description = "Retorna todas as tarefas. Aceita filtros opcionais por status e prioridade.")
    @GetMapping
    public List<Task> findAll(
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) TaskPriority priority) {
        return taskService.findAll(status, priority);
    }
    
    @Operation(summary = "Buscar tarefa pelo ID", description = "Retorna a tarefa do ID passado como parâmetro.")
    @GetMapping("/{id}")
    public Task findById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @Operation(summary = "Atualizar tarefa", description = "Atualiza os dados da tarefa. O status só pode seguir o fluxo PENDENTE → EM_ANDAMENTO → CONCLUIDA (EM_ANDAMENTO também pode voltar para PENDENTE, CONCLUIDA é estado final). Transições inválidas retornam 409.")
    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @Valid @RequestBody Task task) {
        return taskService.update(id, task);
    }

    @Operation(summary = "Deletar tarefa", description = "Deleta a tarefa do ID passado como parâmetro.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}