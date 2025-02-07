package com.pantsunil.project_drill.respository;

import com.pantsunil.project_drill.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    // Inherits methods like save(), findAll(), findById() and so on

    //Custom query: Find by name
    Movie findByName(String name);
}
