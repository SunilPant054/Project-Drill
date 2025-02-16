package com.pantsunil.project_drill.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = IdNotFoundException.class)
    public ResponseEntity<?> handleIdException(IdNotFoundException i){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(i.getMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException i){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(i.getMessage());
    }
}
