package com.pantsunil.project_drill.respository;

import com.pantsunil.project_drill.entity.Hall;
import com.pantsunil.project_drill.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface HallRepository extends JpaRepository<Hall, Integer> {

    // Custom Query:: get movies by hallname
    @Query(value = "SELECT m FROM Movie m " +
        "JOIN MovieHall mh ON m.id = mh.movie.id " +
        "JOIN Hall h ON mh.hall.id = h.id " +
        "WHERE h.hallName = :hallName",
    countQuery = "SELECT COUNT(m) FROM Movie m " +
            "JOIN MovieHall mh ON m.id = mh.movie.id " +
            "JOIN Hall h ON mh.hall.id = h.id " +
            "WHERE h.hallName = :hallName")
    Page<Movie> getMovieByHallName(@Param("hallName")String hallName, Pageable pageable);

    //Custom Query :: get movies by hall name between particular dates
    @Query(value = "SELECT m FROM Movie m " +
            "INNER JOIN m.movieHalls mh " +
            "INNER JOIN mh.hall h " +
            "WHERE h.hallName = :hallName " +
            "AND m.movieStartDateTime >= :startDate " +
            "AND m.movieEndDateTime < :endDate",
            countQuery = "SELECT COUNT(m) FROM Movie m " +
                    "INNER JOIN m.movieHalls mh " +
                    "INNER JOIN mh.hall h " +
                    "WHERE h.hallName = :hallName " +
                    "AND m.movieStartDateTime >= :startDate " +
                    "AND m.movieEndDateTime < :endDate")
    Page<Movie> getMovieByHallNameAndDate(@Param("hallName") String hallName,
                                          @Param("startDate")LocalDateTime startDate,
                                          @Param("endDate") LocalDateTime endDate,
                                          Pageable pageable);
}
