package com.pantsunil.project_drill.controller;

import com.pantsunil.project_drill.dto.showdtos.*;
import com.pantsunil.project_drill.service.ShowService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<ShowResponseDTO>> getShows(){
        List<ShowResponseDTO> shows = showService.getAllShows();
        return new ResponseEntity<>(shows, HttpStatus.OK);
    }

    //get show by id
    @GetMapping("/shows/{id}")
    public ResponseEntity<ShowResponseDTO> getShow(@PathVariable int id){
        ShowResponseDTO show = showService.getShowDTOById(id);
        return new ResponseEntity<>(show, HttpStatus.OK);
    }

    //create a show
    @PostMapping("/shows")
    public ResponseEntity<ShowResponseDTO> createShow(@RequestBody ShowRequestDTO showDTO){
        ShowResponseDTO savedDTO = showService.saveShow(showDTO);
        return new ResponseEntity<>(savedDTO, HttpStatus.OK);
    }

    //delete a show
    @DeleteMapping("/shows/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable int id){
        showService.deleteShow(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Custom Query: get shows and moviedetails from movie_id
    @GetMapping("/shows/movies/{movieId}")
    public ResponseEntity<GetShowsByMovieIdDTO> getShowByMovieId(@PathVariable int movieId){
        GetShowsByMovieIdDTO showDTO = showService.getShowsByMovieId(movieId);
        return new ResponseEntity<>(showDTO, HttpStatus.OK);
    }

    //get shows with movieId and hallId
//    @GetMapping("/shows/movies/{movieId}/halls/{hallId}")
//    public ResponseEntity<ShowDetailsDTO> getShowsDetails(@PathVariable int movieId,
//                                                          @PathVariable int hallId)
//    {
//        ShowDetailsDTO showDetailsDTO = showService.getShowDetails(movieId, hallId);
//        return new ResponseEntity<>(showDetailsDTO, HttpStatus.OK);
//    }

    //get available show tickets
    @GetMapping("/shows/{showId}/tickets")
    public ResponseEntity<List<AvailableShowTicketDTO>> getAvailableTickets(@PathVariable int showId){
        List<AvailableShowTicketDTO> tickets = showService.getAvailableTickets(showId);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
}
