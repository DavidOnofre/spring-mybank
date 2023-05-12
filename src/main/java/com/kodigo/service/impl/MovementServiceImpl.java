package com.kodigo.service.impl;

import com.kodigo.exception.ModelNotFoundException;
import com.kodigo.model.Account;
import com.kodigo.model.Movement;
import com.kodigo.repo.IAccountRepo;
import com.kodigo.repo.IGenericRepo;
import com.kodigo.repo.IMovementRepo;
import com.kodigo.service.IMovementService;
import com.kodigo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MovementServiceImpl extends CRUDImpl<Movement, Integer> implements IMovementService {

    @Autowired
    private IMovementRepo iMovementRepo;

    @Autowired
    private IAccountRepo iAccountRepo;

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

}
