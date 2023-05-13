package com.kodigo.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {
    private String accountNumber;
    private String accountType;
    private Boolean state;
    private BigDecimal initialBalance;
    private BigDecimal availableBalance;
    private List<MovementDTO> movements;
}
