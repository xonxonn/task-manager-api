
package com.joaopedro.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaopedro.taskmanager.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
