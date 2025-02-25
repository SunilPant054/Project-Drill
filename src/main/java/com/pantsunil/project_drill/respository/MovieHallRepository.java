package com.pantsunil.project_drill.respository;

import com.pantsunil.project_drill.entity.MovieHall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MovieHallRepository extends JpaRepository<MovieHall, Integer> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM movie_halls " +
            "WHERE movie_halls.movie_id = :movieId " +
            "AND movie_halls.hall_id = :hallId",
            nativeQuery = true)
    public void deleteMovieByHallIdAndMovieId(@Param("movieId") int movieId,
                                              @Param("hallId") int hallId);
}
