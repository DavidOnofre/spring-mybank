package com.kodigo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovementDTO {

    private String movementType;
    private BigDecimal amount;
    private LocalDateTime date;

    @JsonIgnore
    private BigDecimal balanceReport;

}
