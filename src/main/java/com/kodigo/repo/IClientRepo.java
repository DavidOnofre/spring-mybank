package com.kodigo.repo;

import com.kodigo.model.Client;

public interface IClientRepo extends IGenericRepo<Client, Integer> {

    /*@Query("FROM Client c WHERE c.persona.idPersona=:idPersona")
    Client consultarClientePorPersona(@Param(Constant.PARAM_ID_PERSONA) Integer idPersona);  no uso */

   /* Client findOneByUsuario(String usuario); seguridad */

}
