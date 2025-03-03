package com.pantsunil.project_drill.respository;

import com.pantsunil.project_drill.dto.showdtos.AvailableShowTicketDTO;
import com.pantsunil.project_drill.dto.showdtos.AvailableShowTicketsStatusDTO;
import com.pantsunil.project_drill.dto.showdtos.ShowDetailsDTO;
import com.pantsunil.project_drill.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Integer> {

    //    @Query(value = """
//            SELECT * FROM shows INNER JOIN movies
//            On shows.movie_id = movies.id
//            WHERE shows.movie_id = :movieId
//            """, nativeQuery = true)
    @Query("""
            SELECT s FROM Show s
            JOIN FETCH s.movie m
            WHERE s.movie.id = :movieId
            """)
    Show getShowsByMovieId(@Param("movieId") int movieId);

    @Query(value = """
        SELECT
            shows.id,
            shows.movie_id AS showMovieId,
            shows.screen_id AS showScreenId,
            shows.start_time AS showStartTime,
            shows.end_time AS showEndTime,
            shows.hall_id AS showHallId,
            movies.id AS movieId,
            movies.movie_name AS movieName,
            movies.movie_start_date_time AS movieStartTime,
            movies.movie_end_date_time AS movieEndTime,
            movies.description AS movieDescription,
            screens.id AS screenId,
            screens.number_of_seats AS numberOfSeats,
            screens.hall_id AS screenHallId,
            halls.id AS hallId,
            halls.hall_name AS hallName,
            halls.location AS hallLocation
        FROM shows
        INNER JOIN movies ON shows.movie_id = movies.id
        INNER JOIN screens ON shows.screen_id = screens.id
        INNER JOIN halls ON shows.hall_id = halls.id
        WHERE shows.movie_id = :movieId AND shows.hall_id = :hallId;
    """, nativeQuery = true)
    List<ShowDetailsDTO> getShowDetails(@Param("movieId") int movieId,
                                  @Param("hallId") int hallId);


    //get available tickets for shows with show_id
    @Query(value = """
            SELECT
            shows.movie_id,
            shows.screen_id,
            shows.start_time,
            shows.end_time,
            shows.hall_id,
            tickets.seat_id,
            tickets.price
            FROM shows
            INNER JOIN tickets ON
            shows.id = tickets.show_id
            WHERE show_id = :showId AND tickets.status = "Booked"
            """, nativeQuery = true)
    List<AvailableShowTicketsStatusDTO> getTicketsForShows(@Param("showId") int showId);
}