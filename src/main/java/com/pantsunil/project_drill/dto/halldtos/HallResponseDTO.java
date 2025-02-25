package com.pantsunil.project_drill.dto.halldtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class HallResponseDTO {
    private int id;
    private String hallName;
    private String location;
}
