package com.kodigo.service.impl;

import com.kodigo.model.Client;
import com.kodigo.repo.IClientRepo;
import com.kodigo.repo.IGenericRepo;
import com.kodigo.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends CRUDImpl<Client, Integer> implements IClientService {

    @Autowired
    private IClientRepo clientRepo;

    @Override
    protected IGenericRepo<Client, Integer> getRepo() {
        return clientRepo;
    }

}
