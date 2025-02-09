package com.pantsunil.project_drill.respository;

import com.pantsunil.project_drill.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    // Inherits methods like save(), findAll(), findById() and so on

    //Custom query: Find by name
    public Optional<Movie> getMovieByMovieName(String name);
}
