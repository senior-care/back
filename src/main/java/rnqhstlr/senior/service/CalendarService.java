package rnqhstlr.senior.service;

import rnqhstlr.senior.domain.Image;
import rnqhstlr.senior.dto.CalendarList;
import rnqhstlr.senior.dto.ImageList;

import java.time.LocalDate;
import java.util.List;

public interface CalendarService {

    public List<CalendarList> findCalendarStateList(Long seniorNo, LocalDate startDay, LocalDate endDay);

    public List<ImageList> findCalendarDetails(Long seniorNo, LocalDate date);
}
