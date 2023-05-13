package com.kodigo.service.impl;

import com.kodigo.exception.ModelNotFoundException;
import com.kodigo.model.Account;
import com.kodigo.model.Movement;
import com.kodigo.repo.IAccountRepo;
import com.kodigo.repo.IGenericRepo;
import com.kodigo.repo.IMovementRepo;
import com.kodigo.repo.IReportRepo;
import com.kodigo.service.IMovementService;
import com.kodigo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovementServiceImpl extends CRUDImpl<Movement, Integer> implements IMovementService {

    @Autowired
    private IMovementRepo iMovementRepo;

    @Autowired
    private IAccountRepo iAccountRepo;

    @Autowired
    private IReportRepo iReportRepo;

    @Override
    protected IGenericRepo<Movement, Integer> getRepo() {
        return iMovementRepo;
    }

    @Transactional
    @Override
    public Movement saveTransactional(Movement movement) {
        movement.setAccount(getAccount(movement));
        registerAvailableBalanceInTheAccount(movement);
        registerMovement(movement);
        return movement;
    }

    // Método que recupera la cuenta que se va a utilizar en un movimiento.
    private Account getAccount(Movement movement) {
        Optional<Account> optional = iAccountRepo.findById(movement.getAccount().getIdAccount());
        if (optional.isEmpty()) {
            throw new ModelNotFoundException(Constant.ID_NOT_FOUND + movement.getAccount().getIdAccount());
        }
        return optional.get();
    }

    // Método que graba el movimiento
    private void registerMovement(Movement movement) {
        movement.setDate(LocalDateTime.now());
        movement.setBalance(movement.getAccount().getAvailableBalance());
        iMovementRepo.save(movement);
    }

    // Método que dependiendo del tipo de movimiento suma o resta al saldo
    // disponible en la cuenta.
    private void registerAvailableBalanceInTheAccount(Movement movement) {
        Account account = movement.getAccount();
        switch (movement.getMovementType()) {
            case Constant.WITHDRAWAL:

                validateDailyLimit(movement);

                BigDecimal balanceAfterWithdrawal = account.getAvailableBalance().subtract(movement.getAmount());
                if (balanceAfterWithdrawal.compareTo(BigDecimal.ZERO) < 0) {
                    throw new ModelNotFoundException(Constant.BALANCE_NOT_AVAILABLE);
                }

                account.setAvailableBalance(balanceAfterWithdrawal);
                iAccountRepo.save(account);

                break;
            case Constant.DEPOSIT:

                account.setAvailableBalance(account.getAvailableBalance().add(movement.getAmount()));
                iAccountRepo.save(account);

                break;
        }

    }

    private void validateDailyLimit(Movement movement) {
        LocalDateTime initialDate = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));
        LocalDateTime endDate = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 0));

        List<Movement> movements = iReportRepo.getDailyWithdrawal(initialDate, endDate, movement.getAccount().getIdAccount());

        if (movements.size() != 0) {

            Double a = movements.stream()
                    .mapToDouble(e -> e.getAmount().doubleValue())
                    .sum() + movement.getAmount().doubleValue();

            if (Constant.DAILY_AMOUNT.compareTo(a) < 0) {
                throw new ModelNotFoundException(Constant.DAILY_AMOUNT_EXCEEDED);
            }

        }
    }

}
