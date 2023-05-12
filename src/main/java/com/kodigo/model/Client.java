package com.kodigo.model;

import com.kodigo.util.Constant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "BBuilder")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = Constant.CLIENT_TABLE)
public class Client extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idClient;

    @NotNull
    @Schema(description = Constant.USER_MINIMUM_8_CHARACTERS)
    @Size(min = 8, message = Constant.USER_MINIMUM_8_CHARACTERS)
    @Column(name = Constant.USER_CLIENT, nullable = false, unique = true)
    private String userClient;

    @NotNull
    @Schema(description = Constant.PASS_MINIMUM_8_CHARACTERS)
    @Size(min = 8, message = Constant.PASS_MINIMUM_8_CHARACTERS)
    @Column(name = Constant.PASSWORD_CLIENT, nullable = false, length = 70)
    private String passwordClient;

    @NotNull
    @Schema(description = Constant.STATE_CLIENT)
    @Column(name = Constant.STATE_CLIENT, nullable = false)
    private Boolean stateClient;

}
