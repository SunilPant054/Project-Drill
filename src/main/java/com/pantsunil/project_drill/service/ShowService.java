package com.pantsunil.project_drill.service;

import com.pantsunil.project_drill.dto.moviedtos.MovieDTO;
import com.pantsunil.project_drill.dto.showdtos.ShowRequestDTO;
import com.pantsunil.project_drill.dto.showdtos.ShowResponseDTO;
import com.pantsunil.project_drill.dto.showdtos.GetShowsByMovieIdDTO;
import com.pantsunil.project_drill.entity.Movie;
import com.pantsunil.project_drill.entity.Show;
import com.pantsunil.project_drill.exception.IdNotFoundException;
import com.pantsunil.project_drill.respository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final MovieService movieService;

    //constructor
    public ShowService(ShowRepository showRepository,
                       MovieService movieService){
        this.showRepository = showRepository;
        this.movieService = movieService;
    }

    public ShowResponseDTO saveShow(ShowRequestDTO showDTO){
        String movieName  = showDTO.getMovieName();
        Movie movie = movieService.getMovieByName(showDTO.getMovieName());

        Show show = new Show();
        show.setScreenID(showDTO.getScreenId());
        show.setMovie(movie);
        show.setStartTime(showDTO.getStartTime());
        show.setEndTime(showDTO.getEndTime());

        Show savedShow = showRepository.save(show);

        ShowResponseDTO dto = new ShowResponseDTO();
        dto.setId(savedShow.getId());
        dto.setScreenId(savedShow.getScreenID());
        dto.setMovieName(savedShow.getMovie().getMovieName());
        dto.setStartTime(savedShow.getStartTime());
        dto.setEndTime(savedShow.getEndTime());


        return dto;
    }

    public List<ShowResponseDTO> getAllShows(){
        List<Show> shows = showRepository.findAll();

        List<ShowResponseDTO> showDTO = shows.stream()
                .map(show -> {
                    ShowResponseDTO dto = new ShowResponseDTO();
                    dto.setId(show.getId());
                    dto.setScreenId(show.getScreenID());
                    dto.setMovieId(show.getMovie().getId());
                    dto.setMovieName(show.getMovie().getMovieName());
                    dto.setStartTime(show.getStartTime());
                    dto.setEndTime(show.getEndTime());
                    return dto;
                })
                .collect(Collectors.toList());

        return showDTO;
    }

    public ShowResponseDTO getShowById(Integer id){
        Show show = showRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Show with the given id not found!!"));

        ShowResponseDTO showDTO = new ShowResponseDTO();

        showDTO.setId(show.getId());
        showDTO.setScreenId(show.getScreenID());
        showDTO.setMovieId(show.getMovie().getId());
        showDTO.setMovieName(show.getMovie().getMovieName());
        showDTO.setStartTime(show.getStartTime());
        showDTO.setEndTime(show.getEndTime());

        return showDTO;
    }

    public void deleteShow(Integer id){
        showRepository.deleteById(id);
    }

    //custom query: get show by movie id
    public GetShowsByMovieIdDTO getShowsByMovieId(int movieId){
        Show show = showRepository.getShowsByMovieId(movieId);
        Movie movie = show.getMovie();

        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setMovieName(movie.getMovieName());
        movieDTO.setMovieDescription(movie.getMovieDescription());
        movieDTO.setMovieStartDateTime(movie.getMovieStartTime());
        movieDTO.setMovieEndDateTime(movie.getMovieEndTime());

        Movie movies = new Movie();
        movies.setId(movieDTO.getId());
        movies.setMovieName(movieDTO.getMovieName());
        movies.setMovieDescription(movieDTO.getMovieDescription());
        movies.setMovieStartTime(movieDTO.getMovieStartDateTime());
        movies.setMovieEndTime(movieDTO.getMovieEndDateTime());

        GetShowsByMovieIdDTO showDTO = new GetShowsByMovieIdDTO();
        showDTO.setId(show.getId());
        showDTO.setScreenId(show.getScreenID());
        showDTO.setMovie(movies);
        showDTO.setStartTime(show.getStartTime());
        showDTO.setEndTime(show.getEndTime());

        return showDTO;
    }
}
