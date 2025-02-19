package com.pantsunil.project_drill.service;

import com.pantsunil.project_drill.entity.MovieHall;
import com.pantsunil.project_drill.exception.IdNotFoundException;
import com.pantsunil.project_drill.respository.MovieHallRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieHallService {

    private final MovieHallRepository movieHallRepository;

    //constructor
    public MovieHallService(MovieHallRepository movieHallRepository) {
        this.movieHallRepository = movieHallRepository;
    }

    public MovieHall saveHall(MovieHall movieHall){
        return movieHallRepository.save(movieHall);
    }

    public List<MovieHall> getMovieHall(){
        return movieHallRepository.findAll();
    }

    public MovieHall getMovieHallById(int id){
        return movieHallRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Movie Hall with given id not found!!"));
    }

    public void deleteHall(int id){
        movieHallRepository.deleteById(id);
    }
}
