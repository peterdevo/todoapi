package net.java.todoapi.cores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;

public class ResponseHandler {

    public static <T> ResponseEntity<?> handleResponse(T result, String successMessage) {
        return ResponseEntity.ok(new ApiResponse(successMessage, HttpStatus.OK.value(), result, ZonedDateTime.now()));
    }

}
