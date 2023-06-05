package rnqhstlr.senior.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rnqhstlr.senior.dao.ImageRepository;
import rnqhstlr.senior.domain.Image;
import rnqhstlr.senior.dto.CalendarList;
import rnqhstlr.senior.dto.ImageList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ImageList> findCalendarDetails(Long seniorNo, LocalDate date) {

        List<Image> images = imageRepository.findBySenior_SeniorNoAndDetectDateOrderByDetectTimeDesc(seniorNo, date);

        return images.stream()
                .map(i -> new ImageList(i))
                .collect(Collectors.toList());
    }

}
