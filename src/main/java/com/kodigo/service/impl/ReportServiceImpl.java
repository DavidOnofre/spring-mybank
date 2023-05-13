package com.kodigo.service.impl;

import com.kodigo.exception.ModelNotFoundException;
import com.kodigo.model.Account;
import com.kodigo.model.Movement;
import com.kodigo.model.dto.AccountDTO;
import com.kodigo.model.dto.MovementDTO;
import com.kodigo.model.dto.ParamsReportDTO;
import com.kodigo.model.dto.ReportDTO;
import com.kodigo.repo.IGenericRepo;
import com.kodigo.repo.IReportRepo;
import com.kodigo.service.IReportService;
import com.kodigo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl extends CRUDImpl<Movement, Integer> implements IReportService {

    @Autowired
    private IReportRepo iReportRepo;

    @Override
    protected IGenericRepo<Movement, Integer> getRepo() {
        return iReportRepo;
    }

    @Override
    public ReportDTO generateReport(ParamsReportDTO params) {
        List<Movement> movements = getMovements(params);
        return ReportDTO.builder()
                .client(getFirstRecordClientName(movements))
                .accounts(getAccountDTOs(movements))
                .build();
    }

    private List<AccountDTO> getAccountDTOs(List<Movement> movements) {
        Integer[] idAccounts = getIdAccounts(movements);

        List<AccountDTO> accountDTOs = new ArrayList<>();
        Arrays.stream(idAccounts).forEach(e -> {
            AccountDTO accountDTO = getAccountDTO(movements, e.intValue());
            accountDTOs.add(accountDTO);
        });
        return accountDTOs;
    }


    private String getFirstRecordClientName(List<Movement> movements) {
        return movements.get(0).getAccount().getClient().getName();
    }

    private AccountDTO getAccountDTO(List<Movement> movements, Integer idAccount) {
        List<Account> filteredAccounts = movements.stream()
                .filter(e -> e.getAccount().getIdAccount().equals(idAccount))
                .map(ac -> ac.getAccount())
                .collect(Collectors.toList());
        Account account = filteredAccounts.get(0);

        AccountDTO accountDTO = AccountDTO.builder()
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .state(account.getStateAccount())
                .initialBalance(account.getInitialBalance())
                .build();

        List<MovementDTO> movementDTOs = getMovementsDto(movements, idAccount);
        accountDTO.setMovements(movementDTOs);
        accountDTO.setAvailableBalance(movementDTOs.get(movementDTOs.size() - 1).getBalanceReport());

        return accountDTO;
    }

    private List<MovementDTO> getMovementsDto(List<Movement> movements, Integer idAccount) {

        List<Movement> movementsPerAccount = getMovementsPerAccount(movements, idAccount);

        List<MovementDTO> movementDTOs = new ArrayList<>();
        movementsPerAccount.stream()
                .forEach(e -> {
                    MovementDTO movementDTO = MovementDTO.builder()
                            .movementType(e.getMovementType())
                            .amount(e.getAmount())
                            .date(e.getDate())
                            .balanceReport(e.getBalance())
                            .build();
                    movementDTOs.add(movementDTO);
                });

        return movementDTOs;
    }

    private Integer[] getIdAccounts(List<Movement> movements) {
        Map<Account, List<Movement>> collect = movements.stream()
                .collect(Collectors.groupingBy(Movement::getAccount));

        Integer[] idAccounts = collect.entrySet().stream()
                .map(e -> {
                    return e.getKey().getIdAccount();
                }).toArray(Integer[]::new);

        return idAccounts;
    }

    private List<Movement> getMovementsPerAccount(List<Movement> movements, Integer id) {
        return movements.stream()
                .filter(x -> x.getAccount().getIdAccount().equals(id))
                .collect(Collectors.toList());
    }

    private List<Movement> getMovements(ParamsReportDTO params) {
        List<Movement> movements = iReportRepo.getReport(params.getInitialDate(), params.getEndDate(), params.getIdClient());
        if (movements.size() == 0) {
            throw new ModelNotFoundException(Constant.NO_REPORT + params.getIdClient());
        }
        return movements;
    }

}
