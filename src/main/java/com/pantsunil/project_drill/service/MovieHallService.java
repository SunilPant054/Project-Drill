package com.pantsunil.project_drill.service;

import com.pantsunil.project_drill.dto.HallResponseDTO;
import com.pantsunil.project_drill.dto.MovieHallResponseDTO;
import com.pantsunil.project_drill.entity.Hall;
import com.pantsunil.project_drill.entity.Movie;
import com.pantsunil.project_drill.entity.MovieHall;
import com.pantsunil.project_drill.exception.IdNotFoundException;
import com.pantsunil.project_drill.respository.MovieHallRepository;
import org.springframework.stereotype.Service;
import java.util.List;


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

    //save movie by hall id
    public MovieHallResponseDTO saveMovieByHall(int hallId, String movieName){
        Movie movie = movieService.getMovieByName(movieName);
        HallResponseDTO hallDto = hallService.getHallById(hallId);
        Hall hall = new Hall();
        hall.setId(hallDto.getId());
        hall.setHallName(hallDto.getHallName());
        hall.setLocation(hallDto.getLocation());

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
    public void deleteMovieByHall(int hallId, String movieName){
        Movie movie = movieService.getMovieByName(movieName);
        MovieHall movieHall = new MovieHall();
        int movieId = movie.getId();
        if (movieId == movieHall.getMovie().getId() && hallId == movieHall.getHall().getId()){
            movieHallRepository.deleteById(movieHall.getId());
        }
    }
}
