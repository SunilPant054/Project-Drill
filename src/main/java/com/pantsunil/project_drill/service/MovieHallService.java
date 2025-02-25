package com.pantsunil.project_drill.service;

import com.pantsunil.project_drill.dto.halldtos.HallResponseDTO;
import com.pantsunil.project_drill.dto.moviedtos.MovieDTO;
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
        int movieId = movieHallDTO.getMovie_id();
        Movie movie = movieService.getMovieById(movieId);


        int hallId = movieHallDTO.getHall_id();
        Hall hall = hallService.getHallById(hallId);

        MovieHall movieHall = new MovieHall();
        movieHall.setMovie(movie);
        movieHall.setHall(hall);
//        movie.setMovieHalls(movie.getMovieHalls().add(movieHall));
        MovieHall savedMovieHall = movieHallRepository.save(movieHall);

        MovieHallDTO dto = new MovieHallDTO();
        dto.setMovie_id(savedMovieHall.getMovie().getId());
        dto.setHall_id(savedMovieHall.getHall().getId());

        return dto;
    }

    //get all movie halls
    public List<MovieHallDTO> getMovieHall(){
        List<MovieHall> movieHalls = movieHallRepository.findAll();
        List<MovieHallDTO> movieHallDTO = movieHalls.stream()
                .map(movieHall -> {
                    MovieHallDTO dto = new MovieHallDTO();
                    dto.setId(movieHall.getId());
                    dto.setMovie_id(movieHall.getHall().getId());
                    dto.setHall_id(movieHall.getMovie().getId());
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
        movieHallDTO.setMovie_id(movieHall.getMovie().getId());
        movieHallDTO.setHall_id(movieHall.getHall().getId());

        return movieHallDTO;
    }

    public void deleteHall(MovieHallDTO movieHallDTO){
        int id = movieHallDTO.getId();
        movieHallRepository.deleteById(id);
    }

//    save movie by hall id
    public MovieHallResponseDTO saveMovieByHall(int hallId, String movieName){
        MovieDTO movieDTO = movieService.getMovieDTOByName(movieName);
        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setMovieName(movieDTO.getMovieName());
        movie.setMovieDescription(movieDTO.getMovieDescription());
        movie.setMovieStartTime(movieDTO.getMovieStartDateTime());
        movie.setMovieEndTime(movieDTO.getMovieEndDateTime());

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
