package rnqhstlr.senior.service;

import rnqhstlr.senior.dto.SeniorDetails;
import rnqhstlr.senior.dto.SeniorList;

import java.time.LocalDate;
import java.util.List;

public interface SeniorService {

    public List<SeniorList> findSeniors(Long socialWorkerNo);

    public SeniorDetails findSenior(Long seniorNo, LocalDate startDay, LocalDate endDay);
}
