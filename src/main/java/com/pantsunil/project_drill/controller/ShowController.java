package com.pantsunil.project_drill.controller;

import com.pantsunil.project_drill.entity.Show;
import com.pantsunil.project_drill.service.ShowService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ShowController {
    private final ShowService showService;

    //constructor
    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    //get all show
    @GetMapping("/shows")
    public ResponseEntity<List<Show>> getShows(){
        List<Show> shows = showService.getAllShows();
        return new ResponseEntity<>(shows, HttpStatus.OK);
    }

    //get show by id
    @GetMapping("/shows/{id}")
    public ResponseEntity<Show> getShow(@PathVariable int id){
        Show show = showService.getShowById(id);
        return new ResponseEntity<>(show, HttpStatus.OK);
    }

    //create a show
    @PostMapping("shows")
    public ResponseEntity<Show> createShow(@RequestBody Show show){
        Show savedShow = showService.saveShow(show);
        return new ResponseEntity<>(show, HttpStatus.OK);
    }

    //delete a show
    @DeleteMapping("shows/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable int id){
        showService.deleteShow(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
