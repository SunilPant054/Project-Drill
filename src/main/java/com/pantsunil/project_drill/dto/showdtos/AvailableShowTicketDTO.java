package com.pantsunil.project_drill.dto.showdtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AvailableShowTicketDTO {
    private String movieName;
    private String hallName;
    private int screenId;
    private int seatId;
    private int price;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
