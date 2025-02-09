package com.pantsunil.project_drill.controller;

import com.pantsunil.project_drill.entity.Hall;
import com.pantsunil.project_drill.service.HallService;
import org.apache.coyote.Response;
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

}
