package com.pantsunil.project_drill.service;

import com.pantsunil.project_drill.entity.Hall;
import com.pantsunil.project_drill.entity.Show;
import com.pantsunil.project_drill.exception.IdNotFoundException;
import com.pantsunil.project_drill.respository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {

    private final ShowRepository showRepository;

    //constructor
    public ShowService(ShowRepository showRepository){
        this.showRepository = showRepository;
    }

    public Show saveShow(Show show){
        return showRepository.save(show);
    }

    public List<Show> getAllShows(){
        return showRepository.findAll();
    }

    public Show getShowById(Integer id){
        return showRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Show with the given id not found!!"));
    }

    public void deleteShow(Integer id){
        showRepository.deleteById(id);
    }
}
