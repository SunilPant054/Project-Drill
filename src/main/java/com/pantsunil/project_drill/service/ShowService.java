package com.pantsunil.project_drill.service;

import com.pantsunil.project_drill.dto.moviedtos.MovieDTO;
import com.pantsunil.project_drill.dto.showdtos.*;
import com.pantsunil.project_drill.entity.Hall;
import com.pantsunil.project_drill.entity.Movie;
import com.pantsunil.project_drill.entity.Screen;
import com.pantsunil.project_drill.entity.Show;
import com.pantsunil.project_drill.exception.IdNotFoundException;
import com.pantsunil.project_drill.respository.ShowRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final MovieService movieService;
    private final ScreenService screenService;
    private final HallService hallService;

    //constructor
    public ShowService(ShowRepository showRepository,
                       MovieService movieService,
                       ScreenService screenService,
                       HallService hallService){
        this.showRepository = showRepository;
        this.movieService = movieService;
        this.screenService = screenService;
        this.hallService = hallService;
    }

    public ShowResponseDTO saveShow(ShowRequestDTO showDTO){
        String movieName  = showDTO.getMovieName();
        Movie movie = movieService.getMovieByName(showDTO.getMovieName());

        Show show = new Show();
        show.setScreenID(showDTO.getScreenId());
        show.setMovie(movie);
        show.setHallId(showDTO.getHallId());
        show.setStartTime(showDTO.getStartTime());
        show.setEndTime(showDTO.getEndTime());

        Show savedShow = showRepository.save(show);

        ShowResponseDTO dto = new ShowResponseDTO();
        dto.setId(savedShow.getId());
        dto.setScreenId(savedShow.getScreenID());
        dto.setMovieId(savedShow.getMovie().getId());
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

    //returns showdto by id
    public ShowResponseDTO getShowDTOById(Integer id){
        Show show = getShowById(id);

        ShowResponseDTO showDTO = new ShowResponseDTO();

        showDTO.setId(show.getId());
        showDTO.setScreenId(show.getScreenID());
        showDTO.setMovieId(show.getMovie().getId());
        showDTO.setMovieName(show.getMovie().getMovieName());
        showDTO.setStartTime(show.getStartTime());
        showDTO.setEndTime(show.getEndTime());

        return showDTO;
    }

    //returns show by id
    public Show getShowById(int id)
    {
        return showRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Show with the given id not found!!"));
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

    //get shows details with movidId and hallId
    public List<ShowDetailsFinalDTO> getShowDetails(int movieId, int hallId){
        List<ShowDetailsDTO> shows = showRepository.getShowDetails(movieId, hallId);

        List<ShowDetailsFinalDTO> showDetailsFinalDTO = shows.stream()
                .map(show -> {
                    ShowDetailsFinalDTO dto = new ShowDetailsFinalDTO();
                    dto.setShowId(show.getId());
                    dto.setHallId(show.getHallId());
                    dto.setHallLocation(show.getHallLocation());
                    dto.setHallName(show.getHallName());
                    dto.setScreenId(show.getScreenId());
                    dto.setMovieId(show.getMovieId());
                    dto.setMovieName(show.getMovieName());
                    dto.setMovieDescription(show.getMovieDescription());
                    dto.setNumberOfSeats(show.getNumberOfSeats());
                    dto.setShowStartTime(show.getShowStartTime());
                    dto.setShowEndTime(show.getShowEndTime());
                    return dto;
                })
                .collect(Collectors.toList());

        return showDetailsFinalDTO;
    }

    //get available show tickets
    public List<AvailableShowTicketDTO> getAvailableTickets(int showId){
        List<AvailableShowTicketsStatusDTO> ticketsDTO = showRepository.getTicketsForShows(showId);

        Show show = getShowById(showId);

        Movie movie = movieService.getMovieById(show.getMovie().getId());
        Hall hall = hallService.getHallById(show.getHallId());

        List<AvailableShowTicketDTO> availableShowTicketDTO = ticketsDTO.stream()
                .map(ticket -> {
                    AvailableShowTicketDTO dto = new AvailableShowTicketDTO();
                    dto.setMovieName(movie.getMovieName());
                    dto.setHallName(hall.getHallName());
                    dto.setScreenId(ticket.getScreenId());
                    dto.setSeatId(ticket.getSeatId());
                    dto.setPrice(ticket.getPrice());
                    dto.setStartTime(ticket.getStartTime());
                    dto.setEndTime(ticket.getEndTime());
                    return dto;
                })
                .collect(Collectors.toList());

        return availableShowTicketDTO;
    }

}
