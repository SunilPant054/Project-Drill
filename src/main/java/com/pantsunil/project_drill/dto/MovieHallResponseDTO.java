package com.pantsunil.project_drill.dto;

import com.pantsunil.project_drill.entity.Hall;
import com.pantsunil.project_drill.entity.Movie;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public record MovieHallResponseDTO (String hallName,
                                    String movieName,
                                    LocalDateTime movieStartTime,
                                    LocalDateTime movieEndTime,
                                    String movieDescription) {
}


