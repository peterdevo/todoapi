package net.java.todoapi.cores;

import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    public static ResponseEntity<?> handleResponse(ResultHandler<?> result) {
        if (result.isSuccess() && result.getValue() != null) {
            return ResponseEntity.ok(result.getValue());
        } else if (result.isSuccess() && result.getValue() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().build();
    }


}
