package com.pantsunil.project_drill.controller;

import com.pantsunil.project_drill.dto.MovieHallResponseDTO;
import com.pantsunil.project_drill.dto.MovieRequestDTO;
import com.pantsunil.project_drill.entity.Hall;
import com.pantsunil.project_drill.entity.Movie;
import com.pantsunil.project_drill.entity.MovieHall;
import com.pantsunil.project_drill.service.MovieHallService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MovieHallController {

    private final MovieHallService movieHallService;

    //constructor
    public MovieHallController(MovieHallService movieHallService) {
        this.movieHallService = movieHallService;
    }

    //get all movie hall
    @GetMapping("/movieHalls")
    public ResponseEntity<List<MovieHall>> getMovieHalls(){
        List<MovieHall> movieHalls = movieHallService.getMovieHall();
        return new ResponseEntity<>(movieHalls, HttpStatus.OK);
    }

    // get movie hall by id
    @GetMapping("/movieHalls/{id}")
    public ResponseEntity<MovieHall> getMovieHall(@PathVariable int id){
        MovieHall movieHall = movieHallService.getMovieHallById(id);
        return new ResponseEntity<>(movieHall, HttpStatus.OK);
    }

    // create a movie hall
    @PostMapping("/movieHalls")
    public ResponseEntity<MovieHall> createMovieHall(@RequestBody MovieHall movieHall){
        MovieHall savedMovieHall = movieHallService.saveHall(movieHall);
        return new ResponseEntity<>(savedMovieHall, HttpStatus.OK);
    }

    //delete a movie hall
    @DeleteMapping("/movieHalls")
    public ResponseEntity<Void> deleteMovieHall(@RequestBody int id){
        movieHallService.deleteHall(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // save a movie in particular hall
    @PostMapping("movieHalls/{hallId}/movies/{movieName}")
    public ResponseEntity<MovieHallResponseDTO> saveMovieInHall(@PathVariable int hallId,
                                                                @PathVariable String movieName){
        MovieHallResponseDTO savedMovieByHall = movieHallService.saveMovieByHall(hallId, movieName);
        return new ResponseEntity<>(savedMovieByHall, HttpStatus.OK);
    }

    //delete a movie in particular hall
    @DeleteMapping("movieHalls/{hallId}/movies/{movieName}")
    public ResponseEntity<Void> deleteMovieInHall(@PathVariable int hallId,
                                                  @PathVariable String movieName){
        movieHallService.deleteMovieByHall(hallId, movieName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
