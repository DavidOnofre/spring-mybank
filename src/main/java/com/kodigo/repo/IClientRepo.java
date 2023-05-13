package com.kodigo.repo;

import com.kodigo.model.Client;

public interface IClientRepo extends IGenericRepo<Client, Integer> {

    Client findOneByUserClient(String userClient); //security

}
