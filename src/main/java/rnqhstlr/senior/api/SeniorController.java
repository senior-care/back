package rnqhstlr.senior.api;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rnqhstlr.senior.domain.SocialWorker;
import rnqhstlr.senior.dto.*;
import rnqhstlr.senior.service.CalendarService;
import rnqhstlr.senior.service.SeniorService;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SeniorController {

    private final SeniorService seniorService;
    private final CalendarService calendarService;

    @GetMapping("/senior")
    public ResponseEntity<SeniorListResponse> seniorList(HttpSession session){

        SocialWorker loginWorker = (SocialWorker) session.getAttribute(SessionConst.LOGIN_SOCIAL_WORKER);

        List<SeniorList> seniorList = seniorService.findSeniors(loginWorker.getSocialWorkerNo());
        return ResponseEntity.ok(new SeniorListResponse(seniorList));
    }

    @GetMapping("/senior/{no}")
    public ResponseEntity<SeniorDetails> seniorDetails(@PathVariable("no")Long seniorNo){

        LocalDate endDay = LocalDate.now();
        LocalDate startDay = endDay.with(TemporalAdjusters.firstDayOfMonth());

        SeniorDetails seniorDetails = seniorService.findSenior(seniorNo, startDay, endDay);
        return ResponseEntity.ok(seniorDetails);
    }

    @GetMapping("/senior/{no}/calendar")
    public ResponseEntity<CalendarListResponse> seniorCalendarList(@PathVariable("no")Long seniorNo,
                                                                   @RequestParam("year")Integer year,
                                                                   @RequestParam("mon")Integer mon){

        LocalDate startDay = LocalDate.of(year, mon, 1);
        LocalDate endDay = startDay.with(TemporalAdjusters.lastDayOfMonth());

        List<CalendarList> calendarStateList = calendarService.findCalendarStateList(seniorNo, startDay, endDay);
        return ResponseEntity.ok(new CalendarListResponse(calendarStateList));
    }
}
