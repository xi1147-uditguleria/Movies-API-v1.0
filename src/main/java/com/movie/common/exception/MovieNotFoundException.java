package com.movie.common.exception;

public class MovieNotFoundException extends RuntimeException {

  /** Custom Exception for movies_api_1.0. */
  public MovieNotFoundException(String message) {
    super(message);
  }
}
