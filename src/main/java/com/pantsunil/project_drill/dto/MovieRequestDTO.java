package com.pantsunil.project_drill.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class MovieRequestDTO {

    @JsonProperty(value = "movieName")
    private String movieName;

    @JsonProperty(value = "movieStartDateTime")
    private LocalDateTime movieStartDateTime;

    @JsonProperty(value = "movieEndDateTime")
    private LocalDateTime movieEndDateTime;

    @JsonProperty(value = "movieDescription")
    private String movieDescription;

    public MovieRequestDTO() {

    }

    //getter and setter
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public LocalDateTime getMovieStartDateTime() {
        return movieStartDateTime;
    }

    public void setMovieStartDateTime(LocalDateTime movieStartDateTime) {
        this.movieStartDateTime = movieStartDateTime;
    }

    public LocalDateTime getMovieEndDateTime() {
        return movieEndDateTime;
    }

    public void setMovieEndDateTime(LocalDateTime movieEndDateTime) {
        this.movieEndDateTime = movieEndDateTime;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }
}
