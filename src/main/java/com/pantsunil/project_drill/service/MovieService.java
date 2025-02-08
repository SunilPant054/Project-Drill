package com.pantsunil.project_drill.service;

import com.pantsunil.project_drill.entity.Movie;
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
    public Movie saveMovie(Movie movie){
        return movieRepository.save(movie);
    }

    //get all movie
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    //get movie by ID
    public Movie getMovieById(Integer id){
        return movieRepository.findById(id).orElse(null);
    }

    //Delete movie by ID
    public void deleteMovie(Integer id){
        movieRepository.deleteById(id);
    }

    //Custom query: find by name
    public Movie findByName(String name){
        return movieRepository.getMovieByMovieName(name);
    }

}
