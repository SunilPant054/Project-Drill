package com.pantsunil.project_drill.controller;

import com.pantsunil.project_drill.entity.Seat;
import com.pantsunil.project_drill.service.SeatService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class SeatController {

    private final SeatService seatService;

    //constructor
    public SeatController(SeatService seatService){
        this.seatService = seatService;
    }

    @GetMapping("/seats")
    public ResponseEntity<List<Seat>> getSeats(){
        List<Seat> seats = seatService.getAllSeats();
        return new ResponseEntity<>(seats, HttpStatus.OK);
    }

    @GetMapping("/seats/{id}")
    public ResponseEntity<Seat> getSeat(@PathVariable int id){
        Seat seat = seatService.getSeatById(id);
        return new ResponseEntity<>(seat, HttpStatus.OK);
    }

    @PostMapping("/seats")
    public ResponseEntity<Seat> createSeat(@RequestBody Seat seat){
        Seat savedSeat = seatService.saveSeat(seat);
        return new ResponseEntity<>(savedSeat, HttpStatus.OK);
    }

    @DeleteMapping("seats/{id}")
    public ResponseEntity<Void> deleteSeat(@PathVariable int id){
        seatService.deleteSeat(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
