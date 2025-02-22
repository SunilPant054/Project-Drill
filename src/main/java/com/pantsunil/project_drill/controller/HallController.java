package com.pantsunil.project_drill.controller;

import com.pantsunil.project_drill.dto.HallRequestDTO;
import com.pantsunil.project_drill.dto.HallResponseDTO;
import com.pantsunil.project_drill.dto.MovieByHallRequestDTO;
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
    public ResponseEntity<List<HallResponseDTO>> getHalls(){
        List<HallResponseDTO> halls = hallService.getAllHalls();
        return new ResponseEntity<>(halls, HttpStatus.OK);
    }

    //get a hall by id
    @GetMapping("/halls/{id}")
    public ResponseEntity<HallResponseDTO> getHall(@PathVariable int id){
        HallResponseDTO hallDto = hallService.getHallById(id);
        return new ResponseEntity<>(hallDto, HttpStatus.OK);
    }

    //create a hall
    @PostMapping("/halls")
    public ResponseEntity<HallResponseDTO> createHall(@RequestBody HallRequestDTO hallRequestDTO){
        HallResponseDTO savedHall = hallService.saveHall(hallRequestDTO);
        return new ResponseEntity<>(savedHall, HttpStatus.OK);
    }

    //delete a hall
    @DeleteMapping("movies/{id}")
    public ResponseEntity<Void> deleteHall(@PathVariable int id){
        hallService.deleteHall(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //custom query:: get movies by hall {name, name & date both}
    @GetMapping("halls/movie")
    public ResponseEntity<Page<Movie>> getMoviesByHallName(
            @RequestBody MovieByHallRequestDTO movieByHallRequestDTO,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = ""+Integer.MAX_VALUE, required = false) int pageSize){
        Page<Movie> movieByHall = hallService.getMoviesByHall(movieByHallRequestDTO, pageNo, pageSize);
        return new ResponseEntity<>(movieByHall, HttpStatus.OK);
    }
}
