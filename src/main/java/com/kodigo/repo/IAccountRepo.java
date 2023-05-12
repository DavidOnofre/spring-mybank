package com.kodigo.repo;

import com.kodigo.model.Account;

public interface IAccountRepo extends IGenericRepo<Account, Integer> {

    Account findOneByAccountNumber(String accountNumber);
}
