package com.pantsunil.project_drill.dto.ticketdtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TicketRequestDTO {
    private int showId;
    private int seatId;
    private int price;
    private String status;
}
