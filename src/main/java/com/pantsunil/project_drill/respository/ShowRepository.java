package com.pantsunil.project_drill.respository;

import com.pantsunil.project_drill.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
}
