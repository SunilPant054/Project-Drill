package com.pantsunil.project_drill.controller;

import com.pantsunil.project_drill.dto.moviedtos.MovieDTO;
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
    public ResponseEntity<List<MovieDTO>> getMovies(){
        List<MovieDTO> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    // get movie by id
    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable int id){
        MovieDTO movie = movieService.getMovieDTOById(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    // create a movie
    @PostMapping("/movies")
    public ResponseEntity<MovieDTO> createMovie(@RequestBody MovieDTO movie){
        MovieDTO savedMovie = movieService.saveMovie(movie);
        return new ResponseEntity<>(savedMovie, HttpStatus.OK);
    }

    // delete a movie
    @DeleteMapping("/movies")
    public ResponseEntity<Void> deleteMovie(@RequestBody MovieDTO id){
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    find movie by name
    @GetMapping("/movies/movie")
    public ResponseEntity<MovieDTO> getMovieByName(@RequestParam("movieName") String movieName){
        MovieDTO movie = movieService.getMovieDTOByName(movieName);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }
}
