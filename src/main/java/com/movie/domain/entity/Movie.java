package com.movie.domain.entity;

import com.movie.domain.dto.MovieDto;
import com.movie.domain.enums.MovieCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Version;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Getter
@Setter
@Builder
@Table(name = "MOVIE")
public class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Version int version;

  /**
   * Auto-incrementing version (integer) column. Incremented automatically on each UPDATE. Can be
   * used for optimistic locking.
   */
  private String title;

  @Enumerated(EnumType.STRING)
  private MovieCategory category;

  private Double starRating;

  public static Movie from(MovieDto movieDto) {
    MovieBuilder movieBuilder =
        Movie.builder()
            .title(movieDto.getTitle())
            .category(movieDto.getCategory())
            .starRating(movieDto.getStarRating());
    Movie movie = movieBuilder.build();

    return movie;
  }

  public static MovieDto toDto(Movie movie) {
    return new MovieDto(movie);
  }
}
