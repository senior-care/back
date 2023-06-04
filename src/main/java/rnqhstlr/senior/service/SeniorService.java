package rnqhstlr.senior.service;

import rnqhstlr.senior.dto.SeniorList;

import java.util.List;

public interface SeniorService {

    public List<SeniorList> findSeniors(Long socialWorkerNo);
}
