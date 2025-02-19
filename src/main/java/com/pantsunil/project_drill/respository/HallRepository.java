package com.pantsunil.project_drill.respository;

import com.pantsunil.project_drill.entity.Hall;
import com.pantsunil.project_drill.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HallRepository extends JpaRepository<Hall, Integer> {

//    @Query(value = "SELECT m.id, m.movie_start_time_date, m.movie_end_time_date, m.description " +
//            "FROM movies m " +
//            "JOIN movie_halls mh ON m.id = mh.movie_id " +
//            "JOIN halls h ON mh.hall_id = h.id " +
//            "WHERE h.hall_name = :hallName " +
//            "LIMIT :offset, :limit",
//            countQuery = "SELECT COUNT(*) FROM movies m " +
//                    "JOIN movie_halls mh ON m.id = mh.movie_id " +
//                    "JOIN halls h ON mh.hall_id = h.id " +
//                    "WHERE h.hall_name = :hallName",
//            nativeQuery = true)
    @Query(value = "SELECT m FROM Movie m " +
        "JOIN MovieHall mh ON m.id = mh.movie.id " +
        "JOIN Hall h ON mh.hall.id = h.id " +
        "WHERE h.hallName = :hallName",
    countQuery = "SELECT COUNT(m) FROM Movie m " +
            "JOIN MovieHall mh ON m.id = mh.movie.id " +
            "JOIN Hall h ON mh.hall.id = h.id " +
            "WHERE h.hallName = :hallName")
    Page<Movie> getMovieByHallName(@Param("hallName")String hallName, Pageable pageable);
}
