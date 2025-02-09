package com.pantsunil.project_drill.service;

import com.pantsunil.project_drill.entity.Hall;
import com.pantsunil.project_drill.respository.HallRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {

    private final HallRepository hallRepository;

    //Constructor
    public HallService(HallRepository hallRepository){
        this.hallRepository = hallRepository;
    }

    public Hall saveHall(Hall hall){
        return hallRepository.save(hall);
    }

    public List<Hall> getAllHalls(){
        return hallRepository.findAll();
    }

    public Hall getHallById(Integer id){
        return hallRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hall with the given id not found!!"));
    }

    public void deleteHall(Integer id){
        hallRepository.deleteById(id);
    }
}
