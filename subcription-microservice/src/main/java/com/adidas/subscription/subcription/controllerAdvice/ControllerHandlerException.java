package com.adidas.subscription.subcription.controllerAdvice;

import com.adidas.subscription.subcription.dto.error.ErrorResponse;
import com.adidas.subscription.subcription.exception.DuplicatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
