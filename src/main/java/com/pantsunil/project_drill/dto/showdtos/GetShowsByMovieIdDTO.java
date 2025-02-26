package com.pantsunil.project_drill.dto.showdtos;

import com.pantsunil.project_drill.entity.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class GetShowsByMovieIdDTO {
    public int id;
    public int screenId;
    public LocalDateTime startTime;
    public LocalDateTime endTime;
    public Movie movie;
}
