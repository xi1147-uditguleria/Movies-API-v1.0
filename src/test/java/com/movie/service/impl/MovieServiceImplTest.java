package com.movie.service.impl;

import com.movie.domain.dto.MovieDto;
import com.movie.domain.entity.Movie;
import com.movie.domain.enums.MovieCategory;
import com.movie.repository.MovieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class MovieServiceImplTest {

  @InjectMocks private MovieServiceImpl movieService;

  @Mock private MovieRepository movieRepository;

  @Test
  public void testGetAllMovies() throws Exception {
    List<Movie> movieList = new ArrayList<>();
    movieList.add(new Movie((long) 1.0, 0, "UP", MovieCategory.FANTASY, 4.0));
    movieList.add(new Movie((long) 2.0, 0, "UP", MovieCategory.FANTASY, 4.0));
    when(movieRepository.findAll()).thenReturn(movieList);
    List<MovieDto> movieListResponse = movieService.getAllMovies();
    assertEquals(2, movieListResponse.size());
  }

  @Test
  public void testCreateMovie() throws Exception {
    MovieDto requestDto = getMovieRequestDto("UP", MovieCategory.FANTASY, 2.5);
    Movie movie = Movie.from(requestDto);
    when(movieRepository.save(movie)).thenReturn(movie);
    MovieDto responseDto = movieService.createMovie(requestDto);
    assertEquals("UP", responseDto.getTitle());
    assertEquals(2.5, responseDto.getStarRating(), 0);
  }

  @Test
  public void testGetMovieById() throws Exception {
    Movie movie = new Movie((long) 1.0, 0, "UP", MovieCategory.FANTASY, 4.0);
    when(movieRepository.findById((long) 1)).thenReturn(Optional.of(movie));
    MovieDto responseDto = movieService.getMovieById((long) 1.0);
    assertEquals("UP", responseDto.getTitle());
    assertEquals(4.0, responseDto.getStarRating(), 0);
  }

  @Test
  public void testUpdateMovie() throws Exception {
    MovieDto requestDto = new MovieDto((long) 1.0, "UP", MovieCategory.FANTASY, 2.5);
    Movie movie = Movie.from(requestDto);
    when(movieRepository.findById((long) 1.0)).thenReturn(Optional.of(movie));
    when(movieRepository.save(movie)).thenReturn(movie);
    MovieDto responseDto = movieService.updateMovie(requestDto);
    assertEquals("UP", responseDto.getTitle());
    assertEquals(2.5, responseDto.getStarRating(), 0);
  }

  @Test
  public void testDeleteMovieById() throws Exception {
    doNothing().when(movieRepository).deleteById((long) 2.0);
  }

  private MovieDto getMovieRequestDto(String title, MovieCategory category, Double rating) {
    MovieDto movieDto = new MovieDto();
    movieDto.setTitle(title);
    movieDto.setCategory(category);
    movieDto.setStarRating(rating);
    return movieDto;
  }
}
