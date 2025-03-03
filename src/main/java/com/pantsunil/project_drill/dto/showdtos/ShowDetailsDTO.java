package com.pantsunil.project_drill.dto.showdtos;

import com.pantsunil.project_drill.entity.Hall;
import com.pantsunil.project_drill.entity.Movie;
import com.pantsunil.project_drill.entity.Screen;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowDetailsDTO {
    private int id;
    private int showMovieId;
    private int showScreenId;
    private LocalDateTime showStartTime;
    private LocalDateTime showEndTime;
    private int showHallId;
    private int movieId;
    private String movieName;
    private LocalDateTime movieStartTime;
    private LocalDateTime movieEndTime;
    private String movieDescription;
    private int screenId;
    private int numberOfSeats;
    private int screenHallId;
    private int hallId;
    private String hallName;
    private String hallLocation;
}