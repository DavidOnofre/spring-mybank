package com.kodigo.service.impl;

import com.kodigo.exception.ModelNotFoundException;
import com.kodigo.model.Account;
import com.kodigo.model.Client;
import com.kodigo.repo.IAccountRepo;
import com.kodigo.repo.IClientRepo;
import com.kodigo.util.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AccountServiceImplTest {

    @Mock
    private IAccountRepo iAccountRepo;

    @Mock
    private IClientRepo iClientRepo;

    @InjectMocks
    private AccountServiceImpl accountService;

    Account account;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        initAccount();
    }

    @DisplayName("Successful test save account")
    @Test
    void saveAccountOk() {
        when(iAccountRepo.save(any())).thenReturn(Account.builder().build());
        when(iClientRepo.findById(any())).thenReturn(Optional.of(getClient()));
        assertNotNull(accountService.saveAccount(account));
    }

    @DisplayName("Error test save account")
    @Test
    void saveAccountError() {
        when(iAccountRepo.save(any())).thenReturn(Account.builder().build());
        when(iClientRepo.findById(any())).thenReturn(Optional.empty());

        Exception exception = assertThrows(ModelNotFoundException.class, () -> {
            accountService.saveAccount(account);
        });

        String expectedMessage = Constant.NO_REPORT;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    private void initAccount() {
        account = Account.builder()
                .idAccount(1)
                .client(getClient())
                .build();
    }

    private Client getClient() {
        return Client.BBuilder()
                .idClient(1)
                .build();
    }

}