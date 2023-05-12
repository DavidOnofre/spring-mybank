package com.kodigo.repo;

import com.kodigo.model.Movement;
import com.kodigo.util.Constant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IReportRepo extends IGenericRepo<Movement, Integer> {

    @Query("FROM Movement c WHERE (c.account.client.idClient =:idClient) AND (c.date BETWEEN :initialDate AND :endDate)")
    List<Movement> getReport(@Param(Constant.INITIAL_DATE) LocalDateTime initialDate,
                             @Param(Constant.END_DATE) LocalDateTime endDate,
                             @Param(Constant.ID_CLIENT_PARAM) Integer idClient);
}
