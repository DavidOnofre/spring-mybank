package com.kodigo.model;

import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kodigo.util.Constant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = Constant.ACCOUNT_TABLE)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAccount;

    @NotNull
    @Schema(description = Constant.ACCOUNT_10_DIGITS)
    @Size(min = 10, max = 10, message = Constant.ACCOUNT_10_DIGITS)
    @Column(name = Constant.ACCOUNT_NUMBER, nullable = false, length = 10, unique = true)
    private String accountNumber;

    @NotNull
    @Schema(description = Constant.ACCOUNT_TYPE_ALLOWED)
    @Size(min = 6, max = 7, message = Constant.ACCOUNT_TYPE_ALLOWED)
    @Pattern(regexp = Constant.ACCOUNT_TYPE_REGEX, message = Constant.ACCOUNT_TYPE_ALLOWED)
    @Column(name = Constant.accountType, nullable = false, length = 7)
    private String accountType;

    @NotNull
    @Column(name = Constant.INITIAL_BALANCE, nullable = false)
    private BigDecimal initialBalance;

    @Column(name = Constant.AVAILABLE_BALANCE, nullable = false)
    private BigDecimal availableBalance;

    @NotNull
    @Column(name = Constant.STATE_ACCOUNT, nullable = false)
    private Boolean stateAccount;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    @JoinColumn(name = Constant.ID_CLIENT, nullable = false, foreignKey = @ForeignKey(name = Constant.FK_ACCOUNT_CLIENT))
    private Client client;
}
