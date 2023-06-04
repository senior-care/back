package rnqhstlr.senior;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import rnqhstlr.senior.dao.SocialWorkerRepository;
import rnqhstlr.senior.domain.Gender;
import rnqhstlr.senior.domain.SocialWorker;

import java.time.LocalDate;

@Transactional
@RequiredArgsConstructor
public class DummyDataInit {

    private final SocialWorkerRepository socialWorkerRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void dummyData(){

        //복지사 더미 데이터 세팅
        SocialWorker socialWorker = SocialWorker.createSocialWorker("문성훈", "1940-01-08", "test", "test", Gender.M,
                "010-3321-6132", null, LocalDate.of(2021, 5, 10));
        socialWorkerRepository.save(socialWorker);



    }
}
