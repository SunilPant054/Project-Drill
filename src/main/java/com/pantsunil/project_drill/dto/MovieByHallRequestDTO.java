package com.pantsunil.project_drill.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MovieByHallRequestDTO(String hallName,
                                    LocalDateTime startDate,
                                    LocalDateTime endDate) {
}
