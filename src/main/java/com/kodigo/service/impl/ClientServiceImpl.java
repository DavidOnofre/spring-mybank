package com.kodigo.service.impl;

import com.kodigo.model.Client;
//import com.kodigo.model.Persona;
import com.kodigo.repo.IClientRepo;
import com.kodigo.repo.IGenericRepo;
//import com.kodigo.repo.IPersonaRepo;

import com.kodigo.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends CRUDImpl<Client, Integer> implements IClientService {

    @Autowired
    private IClientRepo clientRepo;

    /*@Autowired
    private IPersonaRepo personaRepo;*/

    /*@Autowired
    @Lazy
    private BCryptPasswordEncoder bcrypt;*/

    @Override
    protected IGenericRepo<Client, Integer> getRepo() {
        return clientRepo;
    }

    /*private void validarIdPersonaUsado(Client cliente) {
        if (clienteRepo.consultarClientPorPersona(cliente.getPersona().getIdPersona()) != null) {
            throw new ModeloNotFoundException(Constant.ID_PERSONA_USADO);
        }
    }*/

    /*private void validarUsuarioUsado(Client cliente) {
        if (clienteRepo.findOneByUsuario(cliente.getUsuario()) != null) {
            throw new ModeloNotFoundException(Constant.CAMBIE_DE_USUARIO);
        }
    }*/

    /*private String cifrarClave(Client cliente) {
        return bcrypt.encode(cliente.getClave());
    }*/

   /* private Persona cargarPersona(Client cliente) {
        return personaRepo.getById(cliente.getPersona().getIdPersona());
    }*/
}
