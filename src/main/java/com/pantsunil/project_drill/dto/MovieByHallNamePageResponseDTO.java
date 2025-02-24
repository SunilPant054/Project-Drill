package com.pantsunil.project_drill.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MovieByHallNamePageResponseDTO {
    private List<MovieByHallPageResponseDTO> pagedMovies;
    private int size;
    private int totalPages;
    private long totalElements;
}
