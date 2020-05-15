package com.movie.common.exception;

public class RedundantMovieException extends RuntimeException {

  /** Custom Exception for movies_api_1.0. */
  public RedundantMovieException(String message) {
    super(message);
  }
}
