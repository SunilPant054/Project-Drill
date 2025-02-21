package com.pantsunil.project_drill.service;

import com.pantsunil.project_drill.dto.MovieByHallRequestDTO;
import com.pantsunil.project_drill.dto.MovieRequestDTO;
import com.pantsunil.project_drill.entity.Hall;
import com.pantsunil.project_drill.entity.Movie;
import com.pantsunil.project_drill.entity.MovieHall;
import com.pantsunil.project_drill.exception.IdNotFoundException;
import com.pantsunil.project_drill.respository.HallRepository;
import com.pantsunil.project_drill.respository.MovieHallRepository;
import com.pantsunil.project_drill.respository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    //Custom Query :: get movie by hallname
    private Page<Movie> getMoviesByHallName(String hallName, int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return hallRepository.getMovieByHallName(hallName, pageable);
    }

    //Custom Query :: get movie by hallname and date filter
    private Page<Movie> getMoviesByHallNameAndDate(String hallName,
                                                  LocalDateTime startDate,
                                                  LocalDateTime endDate,
                                                  int pageNo,
                                                  int pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return hallRepository.getMovieByHallNameAndDate(hallName, startDate, endDate, pageable);
    }

    public Page<Movie> getMoviesByHall(MovieByHallRequestDTO movieByHallRequestDTO, int pageNo, int pageSize){
        if (movieByHallRequestDTO.startDate() != null && movieByHallRequestDTO.endDate() != null) {
            return getMoviesByHallNameAndDate(movieByHallRequestDTO.hallName(),
                    movieByHallRequestDTO.startDate(),
                    movieByHallRequestDTO.endDate(),
                    pageNo, pageSize);
        }
        else{
            return getMoviesByHallName(movieByHallRequestDTO.hallName(), pageNo, pageSize);
        }
    }

}
