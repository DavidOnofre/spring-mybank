package com.kodigo.util;

import java.math.BigDecimal;

public class Constant {

    //endpoints
    public static final String CLIENTS = "/clients";
    public static final String ACCOUNTS = "/accounts";
    public static final String MOVEMENTS = "/movements";
    public static final String REPORTS = "/reports";

    //exception handlers
    public static final String COMMA = ",";
    public static final String SPACE = "";
    public static final String ON_PUBLIC = "ON PUBLIC.";
    public static final String NULLS_FIRST = "NULLS FIRST";
    public static final String BACK_SLASH_DOUBLE_QUOTES = "\"";
    public static final String BACK_SLASH_N = "\n";

    //base de datos
    public static final String CLIENT_TABLE = "client";
    public static final String PASSWORD_CLIENT = "password_client";
    public static final String STATE_CLIENT = "state_client";
    public static final String USER_MINIMUM_8_CHARACTERS = "Field userClient minimum 8 characters";
    public static final String PASS_MINIMUM_8_CHARACTERS = "Field passwordClient minimum 8 characters";
    public static final String ACCOUNT_TABLE = "account";
    public static final String ACCOUNT_10_DIGITS = "Field accountNumber must be 10 digits";
    public static final String ACCOUNT_NUMBER = "account_number";
    public static final String ACCOUNT_TYPE_ALLOWED = "Field accountType allowed: SAVING->saving account | CURRENT->current account";
    public static final String ACCOUNT_TYPE_REGEX = "SAVING|CURRENT";
    public static final String accountType = "account_type";
    public static final String INITIAL_BALANCE = "initial_balance";
    public static final String AVAILABLE_BALANCE = "available_balance";
    public static final String STATE_ACCOUNT = "state_account";
    public static final String ID_CLIENT = "id_client";
    public static final String MOVEMENT_TABLE = "movement";
    public static final String DATE = "date";
    public static final String MOVEMENT_TYPE_ALLOWED = "Field movementType accepts values: DEPOSIT->deposit | WITHDRAWAL->:withdrawal";
    public static final String MOVEMENT_TYPE_REGEXP = "DEPOSIT|WITHDRAWAL";
    public static final String MOVEMENT_AMOUNT_DESCRIPTION = "Field amount";
    public static final String MOVEMENT_BALANCE_DESCRIPTION = "Field balance";
    public static final String MOVEMENT_TYPE = "movement_type";
    public static final String MOVEMENT_AMOUNT = "amount";
    public static final String BALANCE = "balance";
    public static final String ID_ACCOUNT = "id_account";
    public static final String NAME_MINIMUM_3_CHARACTERS = "Field name minimum 3 characters";
    public static final String GENDER_ACCEPTS_MASCULINE_FEMALE = "Field gender accepts values: M->masculine | F->female";
    public static final String AGE_0VER_18 = "Field age must be greater than 18";
    public static final String NAME = "name";
    public static final String GENDER = "gender";
    public static final String AGE = "age";
    public static final String IDENTIFICATION = "identification";
    public static final String ADDRESS = "address";
    public static final String PHONE = "phone";
    public static final String ADDRESS_150_CHARACTERS = "Field address limit 150 characters";
    public static final String IDENTIFICATION_10_CHARACTERS = "Field identification must be 10 characters";
    public static final String PHONE_10_CHARACTERS = "Field phone must be 10 digits";
    public static final String FK_ACCOUNT_CLIENT = "fk_account_client";
    public static final String FK_MOVEMENT_ACCOUNT = "fk_movement_account";
    public static final String USER_CLIENT = "user_client";

    //report
    public static final String INITIAL_DATE = "initialDate";
    public static final String END_DATE = "endDate";
    public static final String ID_CLIENT_PARAM = "idClient";
    public static final String ID_ACCOUNT_PARAM = "idAccount";

    //service
     public static final String ID_NOT_FOUND = "ID not found: ";
    public static final String WITHDRAWAL = "WITHDRAWAL";
    public static final String DEPOSIT = "DEPOSIT";
    public static final String BALANCE_NOT_AVAILABLE = "Balance not available";
    public static final String NO_REPORT = "No Record for this report idClient: ";
    public static final Double DAILY_AMOUNT = 1000.0;
    public static final String DAILY_AMOUNT_EXCEEDED = "Daily coupon exceeded, coupon -> $1000";

    //seguridad
    public static final String USUARIO_NO_EXISTE = "Usuario no existe";
    public static final String CAMBIE_DE_USUARIO = "El usuario ya existe, por favor cambie de usuario";

}
