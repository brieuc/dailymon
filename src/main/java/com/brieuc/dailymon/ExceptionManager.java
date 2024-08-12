package com.brieuc.dailymon;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {
      //@ResponseStatus(HttpStatus.ACCEPTED)
      @ExceptionHandler(ExistingEntriesException.class)
      public ResponseEntity<ExceptionDto> handleExistsAttachedEntries(ExistingEntriesException exception) {
            //return new ExceptionDto(exception.getMessage());
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ExceptionDto(exception.getMessage()));
      }

      @ExceptionHandler(MethodArgumentNotValidException.class)
      public ResponseEntity<ExceptionDto> HandleArgumentNotValid(MethodArgumentNotValidException exception) {
            //return new ExceptionDto(exception.getMessage());
            // All errors came at the same time and are concatenated.
            String error = exception.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(System.lineSeparator()));
            // Not be able to get the message from @NotNull annotation using simply exception.getMessage()
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ExceptionDto(error));
      }

      @ExceptionHandler(HttpMessageNotReadableException.class)
      public ResponseEntity<ExceptionDto> HandleArgumentNotValid(HttpMessageNotReadableException exception) {
            //return new ExceptionDto(exception.getMessage());
            // All errors came at the same time and are concatenated.
            String error = exception.getMessage();
            // Not be able to get the message from @NotNull annotation using simply exception.getMessage()
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ExceptionDto(error));
      }

}
