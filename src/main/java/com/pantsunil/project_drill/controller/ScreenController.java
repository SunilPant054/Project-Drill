package com.pantsunil.project_drill.controller;

import com.pantsunil.project_drill.entity.Screen;
import com.pantsunil.project_drill.service.ScreenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ScreenController {

    private final ScreenService screenService;

    //constructor
    public ScreenController(ScreenService screenService){
        this.screenService = screenService;
    }

    //get all Screen
    @GetMapping("/screens")
    public ResponseEntity<List<Screen>> getScreens(){
        List<Screen> screens = screenService.getAllScreens();
        return new ResponseEntity<>(screens, HttpStatus.OK);
    }

    //get Screen by id
    @GetMapping("/screens/{id}")
    public ResponseEntity<Screen> getScreen(@PathVariable int id){
        Screen screen = screenService.getScreenById(id);
        return new ResponseEntity<>(screen, HttpStatus.OK);
    }

    //add a screen
    @PostMapping("/screens")
    public ResponseEntity<Screen> createScreen(@RequestBody Screen screen){
        Screen savedScreen = screenService.saveScreen(screen);
        return new ResponseEntity<>(savedScreen, HttpStatus.OK);
    }

    //delete a screen
    @DeleteMapping("/screens/{id}")
    public ResponseEntity<Void> deleteScreen(@PathVariable int id){
        screenService.deleteHall(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
