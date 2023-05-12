package com.kodigo.service.impl;

import com.kodigo.exception.ModelNotFoundException;
import com.kodigo.model.Movement;
import com.kodigo.model.dto.MovementDTO;
import com.kodigo.model.dto.ParamsReportDTO;
import com.kodigo.model.dto.ReportDTO;
import com.kodigo.repo.IGenericRepo;
import com.kodigo.repo.IReportRepo;
import com.kodigo.service.IReportService;
import com.kodigo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl extends CRUDImpl<Movement, Integer> implements IReportService {

    @Autowired
    private IReportRepo iReportRepo;

    @Override
    protected IGenericRepo<Movement, Integer> getRepo() {
        return iReportRepo;
    }

    @Override
    public ReportDTO generateReport(ParamsReportDTO params) {

        ReportDTO reporteDTO = new ReportDTO();
        List<Movement> movements = getMovements(params);

        List<MovementDTO> movimientoDTOList = new ArrayList<>();
        for (Movement movement : movements) {

            reporteDTO.setClient(movement.getAccount().getClient().getName());
            reporteDTO.setAccountNumber(movement.getAccount().getAccountNumber());
            reporteDTO.setAccountType(movement.getAccount().getAccountType());
            reporteDTO.setState(movement.getAccount().getStateAccount());
            reporteDTO.setInitialBalance(movement.getAccount().getInitialBalance());
            reporteDTO.setAvailableBalance(movement.getBalance());

            MovementDTO movementDTO = new MovementDTO();
            movementDTO.setMovementType(movement.getMovementType());
            movementDTO.setAmount(movement.getAmount());
            movementDTO.setDate(movement.getDate());
            movimientoDTOList.add(movementDTO);

            reporteDTO.setMovements(movimientoDTOList);
        }
        return reporteDTO;
    }

    private List<Movement> getMovements(ParamsReportDTO params) {
        List<Movement> movements = iReportRepo.getReport(params.getInitialDate(), params.getEndDate(), params.getIdClient());
        if (movements.size() == 0) {
            throw new ModelNotFoundException(Constant.NO_REPORT + params.getIdClient());
        }
        return movements;
    }

}
