package com.kodigo.service.impl;

import com.kodigo.exception.ModelNotFoundException;
import com.kodigo.model.Client;
import com.kodigo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodigo.model.Account;
import com.kodigo.repo.IClientRepo;
import com.kodigo.repo.IAccountRepo;
import com.kodigo.repo.IGenericRepo;
import com.kodigo.service.IAccountService;

import java.util.Optional;

@Service
public class AccountServiceImpl extends CRUDImpl<Account, Integer> implements IAccountService {

	@Autowired
	private IAccountRepo iAccountRepo;

	@Autowired
	private IClientRepo iClientRepo;

	@Override
	protected IGenericRepo<Account, Integer> getRepo() {
		return iAccountRepo;
	}

	@Override
	public Account saveAccount(Account account) {

		Client client = getClientByAccount(account);
		account.setClient(client);
		account.setAvailableBalance(account.getInitialBalance());
		return iAccountRepo.save(account);
	}

	//Método usado para obtener el cliente desde una cuenta
	private Client getClientByAccount(Account account) {
		Optional<Client> optional = iClientRepo.findById(account.getClient().getIdClient());
		if (optional.isEmpty()) {
			throw new ModelNotFoundException(Constant.ID_NOT_FOUND + account.getClient().getIdClient());
		}
		return optional.get();
	}

}
