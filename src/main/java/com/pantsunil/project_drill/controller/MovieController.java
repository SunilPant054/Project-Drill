package com.pantsunil.project_drill.controller;

import com.pantsunil.project_drill.dto.MovieRequestDTO;
import com.pantsunil.project_drill.entity.Movie;
import com.pantsunil.project_drill.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MovieController {
    private final MovieService movieService;

    //constructor
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // get all movies
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getMovies(){
        List<Movie> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    // get movie by id
    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable int id){
        Movie movie = movieService.getMovieById(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    // create a movie
    @PostMapping("/movies")
    public ResponseEntity<Movie> createMovie(@RequestBody MovieRequestDTO movie){
        Movie savedMovie = movieService.saveMovie(movie);
        return new ResponseEntity<>(savedMovie, HttpStatus.OK);
    }

    // delete a movie
    @DeleteMapping("/movies")
    public ResponseEntity<Void> deleteMovie(@RequestBody MovieRequestDTO idRequest){
        movieService.deleteMovie(idRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //find movie by name
    @GetMapping("movies/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name){
        Movie movie = movieService.getMovieByName(name);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }
}
