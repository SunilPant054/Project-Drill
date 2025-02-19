package com.pantsunil.project_drill.service;

import com.pantsunil.project_drill.entity.Hall;
import com.pantsunil.project_drill.entity.Movie;
import com.pantsunil.project_drill.exception.IdNotFoundException;
import com.pantsunil.project_drill.respository.HallRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
                .orElseThrow(() -> new IdNotFoundException("Hall with the given id not found!!"));
    }

    public void deleteHall(Integer id){
        hallRepository.deleteById(id);
    }

    //get movie by hallname
    public Page<Movie> getMoviesByHallName(String hallName, int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return hallRepository.getMovieByHallName(hallName, pageable);
    }


}
