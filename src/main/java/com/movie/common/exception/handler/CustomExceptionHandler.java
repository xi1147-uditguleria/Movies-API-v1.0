package com.movie.common.exception.handler;

import com.movie.common.exception.ExceptionDetails;
import com.movie.common.exception.MovieNotFoundException;
import com.movie.common.exception.RedundantMovieException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
  @org.springframework.web.bind.annotation.ExceptionHandler({RedundantMovieException.class})
  public ResponseEntity<ExceptionDetails> handleRedundantMovieException(RedundantMovieException e) {
    List<String> details = new ArrayList<>();
    details.add("Existing Entry");
    ExceptionDetails exceptionDetails =
            new ExceptionDetails(
                    new Date(), e.getMessage(), details); // only displaying the required details
    return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler({MovieNotFoundException.class})
  public ResponseEntity<ExceptionDetails> handleMovieNotFoundException(MovieNotFoundException e) {
    List<String> details = new ArrayList<>();
    details.add("Entry Doesn't Exist");
    ExceptionDetails exceptionDetails =
        new ExceptionDetails(
            new Date(), e.getMessage(), details); // only displaying the required details
    return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    List<String> details = new ArrayList<>();
    for (ObjectError error : ex.getBindingResult().getAllErrors()) {
      details.add(error.getDefaultMessage());
    }
    ExceptionDetails errorDetails =
        new ExceptionDetails(new Date(), "Validation unsuccessful", details);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
  }
}
