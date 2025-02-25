package com.pantsunil.project_drill.dto.moviehalldtos;

import java.time.LocalDateTime;

public record MovieHallResponseDTO (String hallName,
                                    String movieName,
                                    LocalDateTime movieStartTime,
                                    LocalDateTime movieEndTime,
                                    String movieDescription) {
}


