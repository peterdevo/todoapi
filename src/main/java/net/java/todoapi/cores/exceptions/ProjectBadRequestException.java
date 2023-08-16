package net.java.todoapi.cores.exceptions;

public class ProjectBadRequestException extends RuntimeException{
    public ProjectBadRequestException(String message) {
        super(message);
    }
}
