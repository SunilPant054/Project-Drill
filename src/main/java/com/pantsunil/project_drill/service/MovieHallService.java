package com.pantsunil.project_drill.service;

import com.pantsunil.project_drill.dto.moviehalldtos.MovieHallDTO;
import com.pantsunil.project_drill.dto.moviehalldtos.MovieHallDeleteDTO;
import com.pantsunil.project_drill.dto.moviehalldtos.MovieHallResponseDTO;
import com.pantsunil.project_drill.entity.Hall;
import com.pantsunil.project_drill.entity.Movie;
import com.pantsunil.project_drill.entity.MovieHall;
import com.pantsunil.project_drill.exception.IdNotFoundException;
import com.pantsunil.project_drill.respository.MovieHallRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MovieHallService {

    private final MovieHallRepository movieHallRepository;
    private final MovieService movieService;
    private final HallService hallService;

    //constructor
    public MovieHallService(MovieHallRepository movieHallRepository,
                            MovieService movieService,
                            HallService hallService) {
        this.movieHallRepository = movieHallRepository;
        this.movieService = movieService;
        this.hallService = hallService;
    }

    public MovieHallDTO saveHall(MovieHallDTO movieHallDTO){
        int movieId = movieHallDTO.getMovieId();
        Movie movie = movieService.getMovieById(movieId);


        int hallId = movieHallDTO.getHallId();
        Hall hall = hallService.getHallById(hallId);

        MovieHall movieHall = new MovieHall();
        movieHall.setMovie(movie);
        movieHall.setHall(hall);
        MovieHall savedMovieHall = movieHallRepository.save(movieHall);

        MovieHallDTO dto = new MovieHallDTO();
        dto.setMovieId(savedMovieHall.getMovie().getId());
        dto.setHallId(savedMovieHall.getHall().getId());

        return dto;
    }

    //get all movie halls
    public List<MovieHallDTO> getMovieHall(){
        List<MovieHall> movieHalls = movieHallRepository.findAll();
        List<MovieHallDTO> movieHallDTO = movieHalls.stream()
                .map(movieHall -> {
                    MovieHallDTO dto = new MovieHallDTO();
                    dto.setId(movieHall.getId());
                    dto.setMovieId(movieHall.getHall().getId());
                    dto.setHallId(movieHall.getMovie().getId());
                    return dto;
                })
                .collect(Collectors.toList());

        return movieHallDTO;
    }

    //get movie hall by moviehallId
    public MovieHallDTO getMovieHallById(int id){
        MovieHall movieHall = movieHallRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Movie Hall with given id not found!!"));

        MovieHallDTO movieHallDTO = new MovieHallDTO();

        movieHallDTO.setId(movieHall.getId());
        movieHallDTO.setMovieId(movieHall.getMovie().getId());
        movieHallDTO.setHallId(movieHall.getHall().getId());

        return movieHallDTO;
    }

    public void deleteHall(MovieHallDTO movieHallDTO){
        int id = movieHallDTO.getId();
        movieHallRepository.deleteById(id);
    }

//    save movie by hall id
    public MovieHallResponseDTO saveMovieByHall(int hallId, String movieName){

        Movie movie = movieService.getMovieByName(movieName);

        Hall hall = hallService.getHallById(hallId);

        MovieHall movieHall = new MovieHall();
        movieHall.setMovie(movie);
        movieHall.setHall(hall);
        MovieHall savedMovieHall = movieHallRepository.save(movieHall);
        return new MovieHallResponseDTO(savedMovieHall.getHall().getHallName(),
                savedMovieHall.getMovie().getMovieName(),
                savedMovieHall.getMovie().getMovieStartTime(),
                savedMovieHall.getMovie().getMovieEndTime(),
                savedMovieHall.getMovie().getMovieDescription());
    }

    //delete movie by hall_id and movieName in movie-halls
    public void deleteMovieByHall(MovieHallDeleteDTO movieHallDTO){
        Movie movie = movieService.getMovieByName(movieHallDTO.getMovieName());
        movieHallRepository.deleteMovieByHallIdAndMovieId(movie.getId(), movieHallDTO.getHallId());
    }
}
