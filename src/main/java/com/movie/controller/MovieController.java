package com.movie.controller;

import com.movie.domain.dto.MovieDto;
import com.movie.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0/movies")
@Slf4j
public class MovieController {

  @Autowired private MovieService movieService;

  @PostMapping
  public ResponseEntity<MovieDto> createMovie(@RequestBody @Valid final MovieDto movie) {
    log.info("Request received for creating a movie : {}", movie);
    return ResponseEntity.status(HttpStatus.CREATED).body(movieService.createMovie(movie));
  }

  @GetMapping
  public ResponseEntity<List<MovieDto>> getAllMovies() {
    log.info("Request received for fetching all movies");
    return ResponseEntity.status(HttpStatus.OK).body(movieService.getAllMovies());
  }

  @GetMapping("/{id}")
  public ResponseEntity<MovieDto> getMovieById(@PathVariable("id") final Long id) {
    log.info("Request received for getting movie a by its id : {}", id);
    return ResponseEntity.status(HttpStatus.OK).body(movieService.getMovieById(id));
  }

  @PutMapping
  public ResponseEntity<MovieDto> updateMovie(@RequestBody @Valid final MovieDto movieDto) {
    log.info("Request received for updating a movie fetched by id : {}", movieDto.getId());
    return ResponseEntity.status(HttpStatus.OK).body(movieService.updateMovie(movieDto));
  }

  @DeleteMapping("/{id}")
  public void deleteMovie(@PathVariable("id") final Long id) {
    log.info("Request received for deleting a movie fetched by id : {}", id);
    movieService.deleteMovieById(id);
  }
}
