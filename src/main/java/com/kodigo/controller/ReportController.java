package com.kodigo.controller;

import com.kodigo.model.dto.ParamsReportDTO;
import com.kodigo.model.dto.ReportDTO;
import com.kodigo.service.IReportService;
import com.kodigo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constant.REPORTS)
public class ReportController {

    @Autowired
    private IReportService service;

    @GetMapping
    public ResponseEntity<ReportDTO> reportByMovements(@RequestBody ParamsReportDTO params) throws Exception {
        ReportDTO obj = service.generateReport(params);
        return new ResponseEntity<ReportDTO>(obj, HttpStatus.OK);
    }

}
