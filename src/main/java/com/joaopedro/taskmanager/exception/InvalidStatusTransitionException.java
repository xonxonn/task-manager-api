package com.joaopedro.taskmanager.exception;

import com.joaopedro.taskmanager.entity.TaskStatus;

public class InvalidStatusTransitionException extends RuntimeException {

	public InvalidStatusTransitionException(TaskStatus from, TaskStatus to) 
	{
		super("Transição de status inválida: " + from + " -> " + to);
	}
}
