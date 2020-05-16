package com.movie.domain.dto;

import com.movie.domain.entity.Movie;
import com.movie.domain.enums.MovieCategory;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class MovieDto {

  private Long id;

  @NotBlank private String title;
  private MovieCategory category;

  @DecimalMax("5.0")
  @DecimalMin("0.5")
  @Digits(integer = 1, fraction = 2)
  private Double starRating;

  public MovieDto(Movie movie) {
    this.id = movie.getId();
    this.title = movie.getTitle();
    this.category = movie.getCategory();
    this.starRating = movie.getStarRating();
  }

  public static MovieDto toDto(Movie movie) {
    return new MovieDto(movie);
  }

}
