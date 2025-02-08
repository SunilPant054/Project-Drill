package com.pantsunil.project_drill.entity;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name="movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "movie_start_time")
    private LocalDateTime movieStartTime;

    @Column(name = "movie_end_time")
    private LocalDateTime movieEndTime;

    @Column(name = "description")
    private String movieDescription;

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

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public LocalDateTime getMovieStartTime() {
        return movieStartTime;
    }

    public void setMovieStartTime(LocalDateTime movieStartTime) {
        this.movieStartTime = movieStartTime;
    }

    public LocalDateTime getMovieEndTime() {
        return movieEndTime;
    }

    public void setMovieEndTime(LocalDateTime movieEndTime) {
        this.movieEndTime = movieEndTime;
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
        return "movies{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", movieStartTime=" + movieStartTime +
                ", movieEndTime=" + movieEndTime +
                ", movieDescription=" + movieDescription +
                '}';
    }
}
