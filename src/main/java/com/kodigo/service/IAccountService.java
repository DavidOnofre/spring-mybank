package com.kodigo.service;

import com.kodigo.model.Account;

public interface IAccountService extends ICRUD<Account, Integer> {

	Account saveAccount(Account account) throws Exception;

}