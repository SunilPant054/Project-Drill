package com.pantsunil.project_drill.dto.ticketdtos;

import com.pantsunil.project_drill.entity.Show;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TicketResponseDTO {
    public int hallId;
    public int screenId;
    private int seatId;
    private int price;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String movieName;
    private String status;
}
