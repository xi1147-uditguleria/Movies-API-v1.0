package com.movie.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor()
@Getter
@Setter
public class ExceptionDetails {
  private Date timestamp;
  private String message;
  private List<String> details;
}
