package com.kodigo.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParamsReportDTO {

    private Integer idClient;
    private LocalDateTime initialDate;
    private LocalDateTime endDate;

}
