package com.nexora.operations.exception;

import com.nexora.operations.service.CustomerService.NotFoundException;
import com.nexora.operations.service.AuthService.DuplicateUserException;
import com.nexora.operations.service.AuthService.InvalidCredentialsException;
import jakarta.persistence.OptimisticLockException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.*;

@RestControllerAdvice
public class ApiExceptionHandler {
  record Problem(Instant timestamp, int status, String errorCode, String message, String path, String correlationId, Map<String, String> fieldErrors) {}
  @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<Problem> validation(MethodArgumentNotValidException ex, HttpServletRequest request) { Map<String,String> fields = new HashMap<>(); for (FieldError error : ex.getBindingResult().getFieldErrors()) fields.put(error.getField(), error.getDefaultMessage()); return response(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", "Please correct the highlighted fields.", request, fields); }
  @ExceptionHandler(NotFoundException.class) ResponseEntity<Problem> missing(NotFoundException ex, HttpServletRequest request) { return response(HttpStatus.NOT_FOUND, "NOT_FOUND", ex.getMessage(), request, Map.of()); }
  @ExceptionHandler(OptimisticLockException.class) ResponseEntity<Problem> concurrent(OptimisticLockException ex, HttpServletRequest request) { return response(HttpStatus.CONFLICT, "CONCURRENT_MODIFICATION", "This customer was modified by another user. Refresh to see the latest version.", request, Map.of()); }
  @ExceptionHandler(DuplicateUserException.class) ResponseEntity<Problem> duplicate(DuplicateUserException ex, HttpServletRequest request) { return response(HttpStatus.CONFLICT, "EMAIL_ALREADY_REGISTERED", "An account with this email already exists.", request, Map.of()); }
  @ExceptionHandler(InvalidCredentialsException.class) ResponseEntity<Problem> credentials(InvalidCredentialsException ex, HttpServletRequest request) { return response(HttpStatus.UNAUTHORIZED, "INVALID_CREDENTIALS", "Email or password is incorrect.", request, Map.of()); }
  private ResponseEntity<Problem> response(HttpStatus status, String code, String message, HttpServletRequest request, Map<String,String> fields) { return ResponseEntity.status(status).body(new Problem(Instant.now(), status.value(), code, message, request.getRequestURI(), Optional.ofNullable(request.getHeader("X-Correlation-ID")).orElse(UUID.randomUUID().toString()), fields)); }
}
