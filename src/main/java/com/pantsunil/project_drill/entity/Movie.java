package com.pantsunil.project_drill.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "movie_start_date_time")
    private LocalDateTime movieStartDateTime;

    @Column(name = "movie_end_date_time")
    private LocalDateTime movieEndDateTime;

    @Column(name = "description")
    private String movieDescription;

    //relation
    @OneToMany(mappedBy = "movie")
    private List<Show> shows;

    //constructor
    public Movie() {

    }


    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public LocalDateTime getMovieStartTime() {
        return movieStartDateTime;
    }

    public void setMovieStartTime(LocalDateTime movieStartDateTime) {
        this.movieStartDateTime = movieStartDateTime;
    }

    public LocalDateTime getMovieEndTime() {
        return movieEndDateTime;
    }

    public void setMovieEndTime(LocalDateTime movieEndDateTime) {
        this.movieEndDateTime = movieEndDateTime;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    //toString method
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", movieStartTime=" + movieStartDateTime +
                ", movieEndTime=" + movieEndDateTime +
                ", movieDescription='" + movieDescription + '\'' +
                ", shows=" + shows +
                '}';
    }
}
