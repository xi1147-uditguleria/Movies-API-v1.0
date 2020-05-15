package com.movie.service.impl;

import com.movie.common.exception.MovieNotFoundException;
import com.movie.common.exception.RedundantMovieException;
import com.movie.domain.dto.MovieDto;
import com.movie.domain.entity.Movie;
import com.movie.repository.MovieRepository;
import com.movie.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService {

  @Autowired private MovieRepository movieRepository;

  @Override
  public List<MovieDto> getAllMovies() {
    return this.movieRepository.findAll().stream().map(Movie::toDto).collect(Collectors.toList());
  }

  @Override
  public MovieDto createMovie(MovieDto movieDto) {
    Movie movie = Movie.from(movieDto);
    if (movieRepository.findByTitle(movie.getTitle()).isPresent()) {
      throw new RedundantMovieException(
          String.format("Movie with title %s already exists ", movie.getTitle()));
    }
    movie = movieRepository.save(movie);
    return Movie.toDto(movie);
  }

  @Override
  public MovieDto getMovieById(Long id) {
    Optional<Movie> movie = movieRepository.findById(id);
    if (!movie.isPresent()) {
      log.error("No movie entry found by id - {}", id);
      throw new MovieNotFoundException(String.format("No movie entry found by id - %d", id));
    }
    return Movie.toDto(movie.get());
  }

  @Override
  public MovieDto updateMovie(MovieDto movieDto) {
    Optional<Movie> optional = movieRepository.findById(movieDto.getId());
    if (!optional.isPresent()) {
      log.error("No movie entry found by id - {}", movieDto.getId());
      throw new MovieNotFoundException(String.format("No movie entry found by id - %d", movieDto.getId()));
    }

    Movie initialMovie = optional.get();

    initialMovie.setTitle(movieDto.getTitle());
    initialMovie.setStarRating(movieDto.getStarRating());
    initialMovie.setCategory(movieDto.getCategory());
    return Movie.toDto(movieRepository.save(initialMovie));
  }

  @Override
  public void deleteMovieById(Long id) {
    Optional<Movie> optional = movieRepository.findById(id);
    if (!optional.isPresent()) {
      log.error("No movie entry found by id - {}", id);
      throw new MovieNotFoundException(String.format("No movie entry found by id - %d", id));
    }
    movieRepository.deleteById(id);
  }
}
