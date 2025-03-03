package com.pantsunil.project_drill.dto.showdtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ShowDetailsFinalDTO {
    private Integer showId;
    private Integer hallId;
    private String hallLocation;
    private String hallName;
    private Integer screenId;
    private Integer movieId;
    private String movieName;
    private String movieDescription;
    private int numberOfSeats;
    private LocalDateTime showStartTime;
    private LocalDateTime showEndTime;
}
