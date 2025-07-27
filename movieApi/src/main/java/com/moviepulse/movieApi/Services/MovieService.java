package com.moviepulse.movieApi.Services;

import com.moviepulse.movieApi.dto.MovieDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MovieService {

    MovieDto addMovie(MovieDto movieDto, MultipartFile file) throws Exception;

    MovieDto getMovieById(Integer movieId);

    List<MovieDto> getAllMovies();

    MovieDto updateMovie(Integer movieId, MovieDto movieDto, MultipartFile file) throws Exception;

    String deleteMovie(Integer id) throws IOException;
}
