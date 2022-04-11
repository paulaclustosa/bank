package com.letscode.bank.shared.exception;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorMessage methodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request) {
    return new ErrorMessage(
        HttpStatus.BAD_REQUEST.value(),
        LocalDateTime.now(),
        exception.getMessage(),
        request.getDescription(false)
    );
  }

  @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorMessage resourceNotFoundException(ChangeSetPersister.NotFoundException exception, WebRequest request) {
    return new ErrorMessage(
        HttpStatus.NOT_FOUND.value(),
        LocalDateTime.now(),
        exception.getMessage(),
        request.getDescription(false)
    );
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorMessage globalExceptionHandler(Exception exception, WebRequest request) {
    return new ErrorMessage(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        LocalDateTime.now(),
        exception.getMessage(),
        request.getDescription(false)
    );
  }

}
