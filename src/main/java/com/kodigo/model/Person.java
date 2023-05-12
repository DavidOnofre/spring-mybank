package com.kodigo.model;

import com.kodigo.util.Constant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@MappedSuperclass
public class Person {

    @NotNull
    @Schema(description = Constant.NAME_MINIMUM_3_CHARACTERS)
    @Size(min = 3,max = 70, message = Constant.NAME_MINIMUM_3_CHARACTERS)
    @Column(name = Constant.NAME, nullable = false, length = 70)
    private String name;

    @NotNull
    @Schema(description = Constant.GENDER_ACCEPTS_MASCULINE_FEMALE)
    @Size(min = 1, max = 1, message = Constant.GENDER_ACCEPTS_MASCULINE_FEMALE)
    @Column(name = Constant.GENDER, nullable = false, length = 1)
    private String gender;

    @NotNull
    @Schema(description = Constant.AGE_0VER_18)
    @Min(value = 18, message = Constant.AGE_0VER_18)
    @Column(name = Constant.AGE, nullable = false)
    private Integer age;

    @NotNull
    @Schema(description = Constant.IDENTIFICATION_10_CHARACTERS)
    @Size(min = 10, max = 10, message = Constant.IDENTIFICATION_10_CHARACTERS)
    @Column(name = Constant.IDENTIFICATION, nullable = false, length = 10, unique = true)
    private String identification;

    @NotNull
    @Schema(description = Constant.ADDRESS_150_CHARACTERS)
    @Size(max = 150, message = Constant.ADDRESS_150_CHARACTERS)
    @Column(name = Constant.ADDRESS, nullable = false, length = 150)
    private String address;

    @NotNull
    @Schema(description = Constant.PHONE_10_CHARACTERS)
    @Size(min = 10, max = 10, message = Constant.PHONE_10_CHARACTERS)
    @Column(name = Constant.PHONE, nullable = false, length = 10)
    private String phone;

}
