package com.moviepulse.movieApi.Services.impl;

import com.moviepulse.movieApi.Services.FileService;
import com.moviepulse.movieApi.Services.MovieService;
import com.moviepulse.movieApi.dto.MovieDto;
import com.moviepulse.movieApi.entities.Movie;
import com.moviepulse.movieApi.exceptions.EmptyFileException;
import com.moviepulse.movieApi.exceptions.FileAlreadyExistException;
import com.moviepulse.movieApi.exceptions.MovieNotFoundException;
import com.moviepulse.movieApi.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final FileService fileService;
    private final ModelMapper modelMapper;

    @Value("${project.poster}")
    private String path;

    @Value("${base.url}")
    private String baseUrl;

    @Override
    public MovieDto addMovie(MovieDto movieDto, MultipartFile file) throws Exception {
//        1. upload the file
        if (file.isEmpty()){
            throw new EmptyFileException("File is empty! please send another file!");
        }

        if (Files.exists(Paths.get(path + File.separator + file.getOriginalFilename()))){
            throw new FileAlreadyExistException("File already exists! Please enter another file name: ");
        }
        String uploadedFileName = fileService.uploadFile(path, file);

//        2. set the value of field 'poster' as filename
        movieDto.setPoster(uploadedFileName);

//        3. map dto to movie Object
        Movie movie = modelMapper.map(movieDto,Movie.class);

//        4. saved the movie object -> saved movie object
        Movie savedMovie = movieRepository.save(movie);

//        5. generate the posterUrl
        String posterUrl = baseUrl + "/file/"+ uploadedFileName;

//        6. map Movie Object to dto object and set poster Url and return it
        MovieDto response = modelMapper.map(savedMovie,MovieDto.class);
        response.setPosterUrl(posterUrl);
        return response;
    }

    @Override
    public MovieDto getMovieById(Integer movieId) {
//        1. Try to find the movie from the database
        Movie movie =  movieRepository.findById(movieId)
              .orElseThrow(()-> new MovieNotFoundException("Movie not found with id: "+movieId));

//        2. generate posterUrl and Use ModelMapper to convert Movie Entity to MovieDto
        String posterUrl = baseUrl + "/file/"+ movie.getPoster();
        MovieDto movieDto = modelMapper.map(movie, MovieDto.class);

//        3. Set the postUrl manually since it's not part of entity
      //String posterUrl = baseUrl + "/file/"+ movie.getPoster();
      movieDto.setPosterUrl(posterUrl);
      return movieDto;
    }

    @Override
    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll()
                .stream() // 1. convert list to stream
                .map(movie -> {
                    // 2. map each Movie entity to MovieDto
                    MovieDto movieDto = modelMapper.map(movie, MovieDto.class);
                    // 3. set posterUrl manually (not present in entity)
                    movieDto.setPosterUrl(baseUrl+ "/file/" +movie.getPoster());
                    return movieDto; // 4. return the modified Dto
                })
                .collect(Collectors.toList()); // 5. collect DTO in the form of List
    }

    @Override
    public MovieDto updateMovie(Integer movieId, MovieDto movieDto, MultipartFile file) throws Exception {
        // 1. Fetch existing movie
        Movie existingMovie = movieRepository.findById(movieId)
                .orElseThrow(()-> new MovieNotFoundException("Movie not found with id: "+movieId));

        String currentPoster = existingMovie.getPoster();
        String newPoster = currentPoster; // Default to existing

        // 2. If new file is provided, delete old and upload new
        if (file != null && !file.isEmpty()) {
            // Delete old file
            Files.deleteIfExists(Paths.get(path + File.separator + currentPoster));
            // Upload new file
            newPoster = fileService.uploadFile(path, file);
        }

        // 3. Map incoming DTO into existing entity and set MovieId and set MoviePoster
        modelMapper.map(movieDto, existingMovie);
        existingMovie.setMovieId(movieId);              // Ensure ID is retained
        existingMovie.setPoster(newPoster);             // Use new or existing poster

        // 4. Save updated movie
        Movie updatedMovie = movieRepository.save(existingMovie);

        // 5. Map back to DTO and set full poster URL
        MovieDto responseDto = modelMapper.map(updatedMovie, MovieDto.class);

        String posterUrl = baseUrl + "/file/"+ newPoster;
        responseDto.setPosterUrl(posterUrl);
        return responseDto;
    }


    @Override
    public String deleteMovie(Integer id) throws IOException {
//        1. check the movie Object exist in DB
        Movie movie = movieRepository.findById(id).orElseThrow(()-> new MovieNotFoundException("Movie not found with id: "+id));
        Integer movieId = movie.getMovieId();

//        2. delete the file associated with that Object
        Files.deleteIfExists(Paths.get(path + File.separator + movie.getPoster()));

//        3. delete the movie object
        movieRepository.deleteById(movieId);
        return "Movie deleted with id: "+id;
    }
}
