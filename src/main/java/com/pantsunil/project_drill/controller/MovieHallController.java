package com.pantsunil.project_drill.controller;

import com.pantsunil.project_drill.dto.moviedtos.MovieDTO;
import com.pantsunil.project_drill.dto.moviehalldtos.MovieHallDTO;
import com.pantsunil.project_drill.dto.moviehalldtos.MovieHallDeleteDTO;
import com.pantsunil.project_drill.dto.moviehalldtos.MovieHallResponseDTO;
import com.pantsunil.project_drill.entity.MovieHall;
import com.pantsunil.project_drill.service.MovieHallService;
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
    public ResponseEntity<List<MovieHallDTO>> getMovieHalls(){
        List<MovieHallDTO> movieHalls = movieHallService.getMovieHall();
        return new ResponseEntity<>(movieHalls, HttpStatus.OK);
    }

    // get movie hall by id
    @GetMapping("/movieHalls/{id}")
    public ResponseEntity<MovieHallDTO> getMovieHall(@PathVariable int id){
        MovieHallDTO movieHall = movieHallService.getMovieHallById(id);
        return new ResponseEntity<>(movieHall, HttpStatus.OK);
    }

    // create a movie hall
    @PostMapping("/movieHalls")
    public ResponseEntity<MovieHallDTO> createMovieHall(@RequestBody MovieHallDTO movieHall){
        MovieHallDTO savedMovieHall = movieHallService.saveHall(movieHall);
        return new ResponseEntity<>(savedMovieHall, HttpStatus.OK);
    }

    //delete a movie hall
    @DeleteMapping("/movieHalls")
    public ResponseEntity<Void> deleteMovieHall(@RequestBody MovieHallDTO movieHallDTO){
        movieHallService.deleteHall(movieHallDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //save a movie in particular hall
    @PostMapping("/movieHalls/{hallId}/movies")
    public ResponseEntity<MovieHallResponseDTO> saveMovieInHall(@PathVariable int hallId,
                                                                @RequestParam("movieName") String movieName){
        MovieHallResponseDTO savedMovieByHall = movieHallService.saveMovieByHall(hallId, movieName);
        return new ResponseEntity<>(savedMovieByHall, HttpStatus.OK);
    }

    //delete a movie in particular hall
    @DeleteMapping("/movieHalls/hall/movies")
    public ResponseEntity<Void> deleteMovieInHall(@RequestBody MovieHallDeleteDTO movieHallDTO){
        movieHallService.deleteMovieByHall(movieHallDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
