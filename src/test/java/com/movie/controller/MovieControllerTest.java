package com.movie.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.domain.dto.MovieDto;
import com.movie.domain.enums.MovieCategory;
import com.movie.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
class MovieControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private MovieService movieService;

  @Test
  void getAllMoviesTest() throws Exception {
    List<MovieDto> responseList = new ArrayList<>();
    MovieDto response1 = new MovieDto((long) 1.0, "SAW", MovieCategory.SUSPENSE, 4.0);
    MovieDto response2 = new MovieDto((long) 2.0, "RING", MovieCategory.HORROR, 5.0);
    responseList.add(response1);
    responseList.add(response2);

    when(movieService.getAllMovies()).thenReturn(responseList);
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1.0/movies"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
  }

  @Test
  void getMovieByIdTest() throws Exception {
    MovieDto response = new MovieDto((long) 3.0, "Ring", MovieCategory.HORROR, 4.0);
    when(movieService.getMovieById((long) 3.0)).thenReturn(response);
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1.0/movies/3"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Ring"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("HORROR"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.starRating").value("4.0"));
  }

  @Test
  public void deleteMovieTest() throws Exception {
    doNothing().when(movieService).deleteMovieById(Long.valueOf(2));
    RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1.0/movies/2");
    mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk());
  }

  @Test
  void createMovieTest() throws JsonProcessingException, Exception {
    MovieDto request = getMovieRequestDto("Saw", MovieCategory.SUSPENSE, 2.0);
    MovieDto response = new MovieDto(2l, "Saw", MovieCategory.SUSPENSE, 2.0);
    when(movieService.createMovie(request)).thenReturn(response);

    ObjectMapper mapper = new ObjectMapper();
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/v1.0/movies")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Saw"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("SUSPENSE"));
  }

  @Test
  void updateMovieByIdTest() throws JsonProcessingException, Exception {
    MovieDto response = new MovieDto((long) 2.0, "Up", MovieCategory.SCIFI, 5.0);
    MovieDto request = new MovieDto((long) 2.0, "Up", MovieCategory.SCIFI, 5.0);
    when(movieService.updateMovie(request)).thenReturn(response);
    ObjectMapper mapper = new ObjectMapper();
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.put("/api/v1.0/movies")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Up"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("SCIFI"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.starRating").value(5.0));
  }

  private MovieDto getMovieRequestDto(String title, MovieCategory category, Double rating) {
    MovieDto movieDto = new MovieDto();
    movieDto.setTitle(title);
    movieDto.setCategory(category);
    movieDto.setStarRating(rating);
    return movieDto;
  }
}
