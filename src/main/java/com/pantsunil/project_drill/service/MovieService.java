package com.pantsunil.project_drill.service;

import com.pantsunil.project_drill.dto.MovieRequestDTO;
import com.pantsunil.project_drill.entity.Movie;
import com.pantsunil.project_drill.exception.IdNotFoundException;
import com.pantsunil.project_drill.respository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    //constructor
    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    //save a movie
    public Movie saveMovie(MovieRequestDTO movieDTO){
        //mapping movieDTO to movie
        Movie movie = new Movie();
        movie.setMovieName(movieDTO.getMovieName());
        movie.setMovieStartTime(movieDTO.getMovieStartDateTime());
        movie.setMovieEndTime(movieDTO.getMovieEndDateTime());
        movie.setMovieDescription(movieDTO.getMovieDescription());
        return movieRepository.save(movie);
    }

    //get all movie
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    //get movie by ID
    public Movie getMovieById(Integer id){
        return movieRepository.findById(id)
                .orElseThrow(()-> new IdNotFoundException("Movie with ID not found!!"));
    }

    //Delete movie by ID
    public void deleteMovie(MovieRequestDTO movieRequestDTO){
        Movie movie = new Movie();
        int id = movieRequestDTO.getId();
        movieRepository.deleteById(id);
    }

    //Custom query: find by name
    public Movie getMovieByName(String name){
        return movieRepository.getMovieByMovieName(name)
                .orElseThrow(() -> new RuntimeException("Movie with the given name not found!!"));
    }

}
