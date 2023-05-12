package com.kodigo.service;
import com.kodigo.model.dto.ReportDTO;
import com.kodigo.model.dto.ParamsReportDTO;

public interface IReportService {
    ReportDTO generateReport(ParamsReportDTO criterios);
}
