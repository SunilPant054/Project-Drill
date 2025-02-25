package com.pantsunil.project_drill.service;

import com.pantsunil.project_drill.dto.moviedtos.MovieDTO;
import com.pantsunil.project_drill.entity.Movie;
import com.pantsunil.project_drill.exception.IdNotFoundException;
import com.pantsunil.project_drill.respository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    //constructor
    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    //save a movie
    public MovieDTO saveMovie(MovieDTO movieDTO){
        //mapping movieDTO to movie
        Movie movie = new Movie();
        movie.setMovieName(movieDTO.getMovieName());
        movie.setMovieStartTime(movieDTO.getMovieStartDateTime());
        movie.setMovieEndTime(movieDTO.getMovieEndDateTime());
        movie.setMovieDescription(movieDTO.getMovieDescription());

        Movie savedMovie = movieRepository.save(movie);

        MovieDTO dto = new MovieDTO();
        dto.setId(savedMovie.getId());
        dto.setMovieName(savedMovie.getMovieName());
        dto.setMovieDescription(savedMovie.getMovieDescription());
        dto.setMovieStartDateTime(savedMovie.getMovieStartTime());
        dto.setMovieEndDateTime(savedMovie.getMovieEndTime());

        return dto;
    }

    //get all movies
    public List<MovieDTO> getAllMovies(){
        List<Movie> movies = movieRepository.findAll();
        List<MovieDTO> movieDTO = movies.stream()
                .map(movie -> {
                    MovieDTO dto = new MovieDTO();
                    dto.setId(movie.getId());
                    dto.setMovieName(movie.getMovieName());
                    dto.setMovieDescription(movie.getMovieDescription());
                    dto.setMovieStartDateTime(movie.getMovieStartTime());
                    dto.setMovieEndDateTime(movie.getMovieEndTime());
                    return dto;
                })
                .collect(Collectors.toList());

        return movieDTO;
    }

    //get movie by ID
    public MovieDTO getMovieDTOById(Integer id){
        Movie movie = getMovieById(id);

        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movieDTO.getId());
        movieDTO.setMovieName(movie.getMovieName());
        movieDTO.setMovieDescription(movie.getMovieDescription());
        movieDTO.setMovieStartDateTime(movie.getMovieStartTime());
        movieDTO.setMovieEndDateTime(movie.getMovieEndTime());

        return movieDTO;
    }

    public Movie getMovieById(Integer id)
    {
        return movieRepository.findById(id)
                .orElseThrow(()-> new IdNotFoundException("Movie with ID not found!!"));
    }


    //Delete movie by ID
    public void deleteMovie(MovieDTO movieDTO){
        int id = movieDTO.getId();
        movieRepository.deleteById(id);
    }

    //Custom query: find by name
    public MovieDTO getMovieDTOByName(String name){
        Movie movie = getMovieByName(name);

        MovieDTO movieDto = new MovieDTO();
        movieDto.setId(movie.getId());
        movieDto.setMovieName(movie.getMovieName());
        movieDto.setMovieDescription(movie.getMovieDescription());
        movieDto.setMovieStartDateTime(movie.getMovieStartTime());
        movieDto.setMovieEndDateTime(movie.getMovieEndTime());

        return movieDto;

    }

    public Movie getMovieByName(String name){
        return movieRepository.getMovieByMovieName(name)
                .orElseThrow(() -> new RuntimeException("Movie with the given name not found!!"));
    }

}
