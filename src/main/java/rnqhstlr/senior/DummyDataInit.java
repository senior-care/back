package rnqhstlr.senior;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import rnqhstlr.senior.dao.ImageRepository;
import rnqhstlr.senior.dao.SeniorRepository;
import rnqhstlr.senior.dao.SocialWorkerRepository;
import rnqhstlr.senior.domain.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Transactional
@RequiredArgsConstructor
public class DummyDataInit {

    private final SocialWorkerRepository socialWorkerRepository;
    private final SeniorRepository seniorRepository;
    private final ImageRepository imageRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void dummyData(){

        //복지사 더미 데이터 세팅
        SocialWorker socialWorker = SocialWorker.createSocialWorker("문성훈", "1940-01-08", "test", "test", Gender.M,
                "010-3321-6132", null, LocalDate.of(2021, 5, 10));
        socialWorkerRepository.save(socialWorker);

        //노인 더미 데이터 세팅
        Senior senior1 = 노인_추가(socialWorker, "한소회", Gender.F, LocalDate.of(1999, 7, 27), null,
                "010-3321-6132", "010-3321-6132", LocalDate.of(2020, 1, 1), LocalDate.of(2024, 1, 1));
        Senior senior2 = 노인_추가(socialWorker,"이주명", Gender.F, LocalDate.of(1996, 1, 27), null,
                "010-1234-6132", "010-1234-6132", LocalDate.of(2022, 1, 1), LocalDate.of(2025, 1, 1));
        Senior senior3 = 노인_추가(socialWorker, "고수", Gender.M, LocalDate.of(2000, 1, 27), null,
                "010-0987-6132", "010-0987-6132", LocalDate.of(2019, 1, 1), LocalDate.of(2025, 1, 1));

        //노인 이미지 및 상태 더미 데이터 세팅
        노인_이미지_추가(senior1,LocalDate.of(2023, 6, 1), EmotionCode.HAPPY);
        노인_이미지_추가(senior1, LocalDate.of(2023, 6, 1), EmotionCode.HAPPY);
        노인_이미지_추가(senior1, LocalDate.of(2023, 6, 1), EmotionCode.SAD);
        노인_이미지_추가(senior1, LocalDate.of(2023, 6, 1), EmotionCode.ANGRY);

        노인_이미지_추가(senior1, LocalDate.of(2023, 6, 2), EmotionCode.SAD);
        노인_이미지_추가(senior1, LocalDate.of(2023, 6, 2), EmotionCode.HAPPY);
        노인_이미지_추가(senior1, LocalDate.of(2023, 6, 2), EmotionCode.SAD);
        노인_이미지_추가(senior1, LocalDate.of(2023, 6, 2), EmotionCode.ANGRY);


    }
    public Senior 노인_추가(SocialWorker socialWorker,String name, Gender gender, LocalDate birth, String imagePath, String mainPhone, String subPhone,
                        LocalDate registrationDate, LocalDate endDate){
        Address address = Address.createAddress("11", "1111", "details");
        Senior createSenior = Senior.createSenior(name, gender, birth, imagePath, mainPhone, subPhone, address, registrationDate, endDate);
        createSenior.setSocialWorker(socialWorker);
        return seniorRepository.save(createSenior);
    }
    public Image 노인_이미지_추가(Senior senior, LocalDate detectDate, EmotionCode code){
        Image image = Image.createImage(null, null, null, detectDate, LocalTime.now(), code);
        image.setSenior(senior);
        return imageRepository.save(image);
    }
}
