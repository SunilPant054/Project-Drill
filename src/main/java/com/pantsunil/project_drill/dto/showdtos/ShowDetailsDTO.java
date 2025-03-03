package com.pantsunil.project_drill.dto.showdtos;

import java.time.LocalDateTime;

public interface ShowDetailsDTO {
    int getId();
    int getShowMovieId();
    int getShowScreenId();
    LocalDateTime getShowStartTime();
    LocalDateTime getShowEndTime();
    int getShowHallId();
    int getMovieId();
    String getMovieName();
    LocalDateTime getMovieStartTime();
    LocalDateTime getMovieEndTime();
    String getMovieDescription();
    int getScreenId();
    int getNumberOfSeats();
    int getScreenHallId();
    int getHallId();
    String getHallName();
    String getHallLocation();
}