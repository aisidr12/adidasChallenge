package com.adidas.subscription.subcription.controllerAdvice;

import com.adidas.subscription.subcription.dto.error.ErrorResponse;
import com.adidas.subscription.subcription.exception.DuplicatedException;
import com.adidas.subscription.subcription.exception.SqsExceptionCustom;
import jakarta.validation.ConstraintViolationException;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerHandlerException {

  @ExceptionHandler(DuplicatedException.class)
  public ResponseEntity<ErrorResponse> handleRepeatEmail(DuplicatedException duplicatedException) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse("ADIDAS-001", duplicatedException.getMessage())
        , HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(SqsExceptionCustom.class)
  public ResponseEntity<ErrorResponse> handleRepeatEmail(SqsExceptionCustom sqsExceptionCustom) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse("ADIDAS-002", sqsExceptionCustom.getMessage())
        , HttpStatus.INTERNAL_SERVER_ERROR);
  }


  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleMethodArgNotValid(MethodArgumentNotValidException ex) {
    var errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(f -> f.getField() + ": " + f.getDefaultMessage())
        .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
    var errors = ex.getConstraintViolations()
        .stream()
        .map(v -> v.getPropertyPath() + ": " + v.getMessage())
        .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }

}
