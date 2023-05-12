package com.kodigo.model;

import com.kodigo.util.Constant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = Constant.MOVEMENT_TABLE)
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMovement;

    @Column(name = Constant.DATE, nullable = false)
    private LocalDateTime date;

    @NotNull
    @Schema(description = Constant.MOVEMENT_TYPE_ALLOWED)
    @Size(min = 7, max = 10, message = Constant.MOVEMENT_TYPE_ALLOWED)
    @Pattern(regexp = Constant.MOVEMENT_TYPE_REGEXP, message = Constant.MOVEMENT_TYPE_ALLOWED)
    @Column(name = Constant.MOVEMENT_TYPE, nullable = false, length = 3)
    private String movementType;

    @NotNull
    @Schema(description = Constant.MOVEMENT_AMOUNT_DESCRIPTION)
    @Column(name = Constant.MOVEMENT_AMOUNT, nullable = false)
    private BigDecimal amount;

    @Schema(description = Constant.MOVEMENT_BALANCE_DESCRIPTION)
    @Column(name = Constant.BALANCE, nullable = false)
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = Constant.ID_ACCOUNT, nullable = false, foreignKey = @ForeignKey(name = Constant.FK_MOVEMENT_ACCOUNT))
    private Account account;
}
