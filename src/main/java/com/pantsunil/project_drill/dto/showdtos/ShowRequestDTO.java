package com.pantsunil.project_drill.dto.showdtos;

import com.pantsunil.project_drill.entity.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ShowRequestDTO {
    private int screenId;
    private int movieId;
    private int hallId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String movieName;
}