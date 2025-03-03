package com.pantsunil.project_drill.dto.showdtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


public interface AvailableShowTicketsStatusDTO {
    Integer getMovieId();
    Integer getScreenId();
    LocalDateTime getStartTime();
    LocalDateTime getEndTime();
    Integer getHallId();
    Integer getSeatId();
    Integer getPrice();
}
