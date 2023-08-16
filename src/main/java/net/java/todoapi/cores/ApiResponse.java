package net.java.todoapi.cores;

import java.time.ZonedDateTime;

public class ApiResponse<T> {
    String message;
    Integer status;
    T result;
    ZonedDateTime timeStamp;

    public ApiResponse(String message, Integer status, T result, ZonedDateTime timeStamp) {
        this.message = message;
        this.status = status;
        this.result = result;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }

    public T getResult() {
        return result;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }
}
