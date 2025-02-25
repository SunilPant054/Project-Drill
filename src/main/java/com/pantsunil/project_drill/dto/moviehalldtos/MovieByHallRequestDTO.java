package com.pantsunil.project_drill.dto.moviehalldtos;

import java.time.LocalDateTime;

public record MovieByHallRequestDTO(String hallName,
                                    LocalDateTime startDate,
                                    LocalDateTime endDate) {
}
