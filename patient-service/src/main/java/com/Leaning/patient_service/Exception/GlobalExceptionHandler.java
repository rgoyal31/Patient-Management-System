package com.Leaning.patient_service.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //    will handle all the error with respect validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValdiationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleEmailExistsException(EmailAlreadyExistsException ex){

        log.warn("Email {} already Exists in the DB ", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("message","Email Already Exists in the DB");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Map<String, String>> patientNotFount(PatientNotFoundException ex){
        log.warn("Patient not found in the DB {}", ex.getMessage());
        Map<String,String> errors = new HashMap<>();
        errors.put("message", "Patient Not Found associated with the email");
        return ResponseEntity.badRequest().body(errors);
    }
}
