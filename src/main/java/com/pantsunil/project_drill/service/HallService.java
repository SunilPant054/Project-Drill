package com.pantsunil.project_drill.service;

import com.pantsunil.project_drill.dto.HallRequestDTO;
import com.pantsunil.project_drill.dto.HallResponseDTO;
import com.pantsunil.project_drill.dto.MovieByHallRequestDTO;
import com.pantsunil.project_drill.entity.Hall;
import com.pantsunil.project_drill.entity.Movie;
import com.pantsunil.project_drill.exception.IdNotFoundException;
import com.pantsunil.project_drill.respository.HallRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HallService {

    private final HallRepository hallRepository;

    //Constructor
    public HallService(HallRepository hallRepository){
        this.hallRepository = hallRepository;
    }

    public HallResponseDTO saveHall(HallRequestDTO hallRequestDTO){
        Hall hall = new Hall();
        hall.setHallName(hallRequestDTO.getHallName());
        hall.setLocation(hallRequestDTO.getLocation());

        Hall savedHall =  hallRepository.save(hall);

        HallResponseDTO hallResponseDTO = new HallResponseDTO();
        hallResponseDTO.setId(savedHall.getId());
        hallResponseDTO.setHallName(savedHall.getHallName());
        hallResponseDTO.setLocation(savedHall.getLocation());

        return hallResponseDTO;

    }

    public List<HallResponseDTO> getAllHalls(){
        List<Hall> halls = hallRepository.findAll();
        List<HallResponseDTO> hallDto = halls.stream()
                .map(hall -> {
                    HallResponseDTO dto = new HallResponseDTO();
                    dto.setId(hall.getId());
                    dto.setHallName(hall.getHallName());
                    dto.setLocation(hall.getLocation());
                    return dto;
                })
                .collect(Collectors.toList());
        return hallDto;
    }

    public HallResponseDTO getHallById(Integer id){
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Hall with the given id not found!!"));
        HallResponseDTO hallResponseDTO = new HallResponseDTO();
        hallResponseDTO.setId(hall.getId());
        hallResponseDTO.setHallName(hall.getHallName());
        hallResponseDTO.setLocation(hall.getLocation());
        return hallResponseDTO;
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
