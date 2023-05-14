package com.kodigo.service.impl;

import com.kodigo.exception.ModelNotFoundException;
import com.kodigo.model.Account;
import com.kodigo.model.Client;
import com.kodigo.model.Movement;
import com.kodigo.model.dto.ParamsReportDTO;
import com.kodigo.repo.IReportRepo;
import com.kodigo.util.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ReportServiceImplTest {

    @Mock
    private IReportRepo iReportRepo;

    @InjectMocks
    private ReportServiceImpl reportService;

    ParamsReportDTO params;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        initParams();
    }

    @DisplayName("Successful test generate report")
    @Test
    void generateReportOk() {
        when(iReportRepo.getReport(any(), any(), any())).thenReturn(getMovements());
        assertNotNull(reportService.generateReport(params));
    }

    @DisplayName("Error test generate report")
    @Test
    void generateReportError() {
        when(iReportRepo.getReport(any(), any(), any())).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(ModelNotFoundException.class, () -> {
            reportService.generateReport(params);
        });

        String expectedMessage = Constant.NO_REPORT;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    private List<Movement> getMovements() {
        Movement movement = Movement
                .builder()
                .idMovement(1)
                .account(getAccount())
                .build();
        return Arrays.asList(movement);
    }

    private Account getAccount() {
        return Account
                .builder()
                .idAccount(1)
                .client(getClient())
                .build();
    }

    private Client getClient() {
        return Client.BBuilder()
                .idClient(1)
                .build();
    }

    private void initParams() {
        params = ParamsReportDTO
                .builder()
                .idClient(1)
                .initialDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .build();
    }
}