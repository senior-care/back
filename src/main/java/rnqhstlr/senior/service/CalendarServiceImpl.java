package rnqhstlr.senior.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rnqhstlr.senior.dao.ImageRepository;
import rnqhstlr.senior.dto.CalendarList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CalendarServiceImpl implements CalendarService {

    private final ImageRepository imageRepository;

    @Override
    public List<CalendarList> findCalendarStateList(Long seniorNo, LocalDate startDay, LocalDate endDay) {

        List<CalendarList> list = new ArrayList<>();
        for(LocalDate start = startDay; start.isBefore(endDay)||start.isEqual(endDay); start=start.plusDays(1)){
            imageRepository.findEmotionCodeByDate(seniorNo, start)
                    .ifPresent(i -> list.add(new CalendarList(i.getDetectDate(), i.getEmotionCode().getDes())));
        }
        return list;
    }

}
