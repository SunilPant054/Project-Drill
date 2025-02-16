package com.pantsunil.project_drill.service;


import com.pantsunil.project_drill.entity.Screen;
import com.pantsunil.project_drill.exception.IdNotFoundException;
import com.pantsunil.project_drill.respository.ScreenRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenService {

    private final ScreenRepository screenRepository;

    //constructor
    public ScreenService(ScreenRepository screenRepository){
        this.screenRepository = screenRepository;
    }

    //required methods
    public Screen saveScreen(Screen screen){
        return screenRepository.save(screen);
    }

    public List<Screen> getAllScreens(){
        return screenRepository.findAll();
    }

    public Screen getScreenById(Integer id){
        return screenRepository.findById(id)
                .orElseThrow( () -> new IdNotFoundException("Screen by the given id Not Found!!"));
    }

    public void deleteHall(Integer id){
        screenRepository.deleteById(id);
    }
}
