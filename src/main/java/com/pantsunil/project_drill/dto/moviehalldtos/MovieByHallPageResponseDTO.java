package com.pantsunil.project_drill.dto.moviehalldtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MovieByHallPageResponseDTO {
    private int movieId;
    private String movieName;
    private String movieDescription;
    private LocalDateTime movieStartTime;
    private LocalDateTime movieEndTime;
}
