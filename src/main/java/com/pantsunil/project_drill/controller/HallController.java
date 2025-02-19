package com.pantsunil.project_drill.controller;

import com.pantsunil.project_drill.entity.Hall;

import com.pantsunil.project_drill.entity.Movie;
import com.pantsunil.project_drill.service.HallService;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class HallController {
    private final HallService hallService;

    //Constructor
    public HallController(HallService hallService){
        this.hallService = hallService;
    }

    //get all hall
    @GetMapping("/halls")
    public ResponseEntity<List<Hall>> getHalls(){
        List<Hall> halls = hallService.getAllHalls();
        return new ResponseEntity<>(halls, HttpStatus.OK);
    }

    //get a hall by id
    public ResponseEntity<Hall> getHall(@PathVariable int id){
        Hall hall = hallService.getHallById(id);
        return new ResponseEntity<>(hall, HttpStatus.OK);
    }

    //create a hall
    @PostMapping("/halls")
    public ResponseEntity<Hall> createHall(@RequestBody Hall hall){
        Hall savedHall = hallService.saveHall(hall);
        return new ResponseEntity<>(savedHall, HttpStatus.OK);
    }

    //delete a hall
    @DeleteMapping("movies/{id}")
    public ResponseEntity<Void> deleteHall(@PathVariable int id){
        hallService.deleteHall(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //custom query:: get movies by hall name
    @GetMapping("halls/{hallName}/movie")
    public ResponseEntity<Page<Movie>> getMoviesByHallName(
            @RequestParam(value = "hallName", defaultValue = "", required = false) String hallName,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = ""+Integer.MAX_VALUE, required = false) int pageSize){
        Page<Movie> movieByHallName = hallService.getMoviesByHallName(hallName, pageNo, pageSize);
        return new ResponseEntity<>(movieByHallName, HttpStatus.OK);
    }

}
