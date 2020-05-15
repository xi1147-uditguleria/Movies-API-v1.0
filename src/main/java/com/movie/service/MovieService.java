package com.movie.service;

import com.movie.domain.dto.MovieDto;

import java.util.List;

public interface MovieService {

  MovieDto createMovie(MovieDto movieDto);

  List<MovieDto> getAllMovies();

  MovieDto getMovieById(Long id);

  MovieDto updateMovie(MovieDto movieDto);

  void deleteMovieById(Long id);
}
