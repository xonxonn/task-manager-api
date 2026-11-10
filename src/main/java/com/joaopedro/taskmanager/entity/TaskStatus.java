package com.joaopedro.taskmanager.entity;

public enum TaskStatus {
    PENDENTE,
    EM_ANDAMENTO,
    CONCLUIDA;

    public boolean canTransitionTo(TaskStatus next) {
        if (this == next) {
            return true;
        }
        return switch (this) {
            case PENDENTE -> next == EM_ANDAMENTO;
            case EM_ANDAMENTO -> next == CONCLUIDA || next == PENDENTE;
            case CONCLUIDA -> false;
        };
    }
}