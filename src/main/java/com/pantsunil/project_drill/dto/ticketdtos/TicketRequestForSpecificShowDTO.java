package com.pantsunil.project_drill.dto.ticketdtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TicketRequestForSpecificShowDTO {
    private int hallId;
    private int screenId;
    private int showId;
    private int price;
    private String status;
}
