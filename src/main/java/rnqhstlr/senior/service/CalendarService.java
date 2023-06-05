package rnqhstlr.senior.service;

import rnqhstlr.senior.dto.CalendarList;

import java.time.LocalDate;
import java.util.List;

public interface CalendarService {

    public List<CalendarList> findCalendarStateList(Long seniorNo, LocalDate startDay, LocalDate endDay);
}
