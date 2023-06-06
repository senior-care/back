package rnqhstlr.senior;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import rnqhstlr.senior.dao.IllnessRepository;
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
    private final IllnessRepository illnessRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void dummyData(){

        //복지사 더미 데이터 세팅
        SocialWorker socialWorker = SocialWorker.createSocialWorker("문성훈", "1940-01-08", "test", "test", Gender.M,
                "010-3321-6132", null, LocalDate.of(2021, 5, 10));
        socialWorkerRepository.save(socialWorker);

        //노인 더미 데이터 세팅
        Senior senior1 = Senior.createSenior("한소회", Gender.F, LocalDate.of(1999, 7, 27), null,
                "010-3321-6132", "010-3321-6132", Address.createAddress("대구 광역시", "북구", "태전 1동 224-3"),
                LocalDate.of(2020, 1, 1), LocalDate.of(2024, 1, 1));
        senior1.setSocialWorker(socialWorker);
        Senior senior2 = Senior.createSenior("이주명", Gender.F, LocalDate.of(1996, 1, 27), null,
                "010-1234-6132", "010-1234-6132",Address.createAddress("대구 광역시", "북구", "태전 3동 삼성아파트"),
                LocalDate.of(2022, 1, 1), LocalDate.of(2025, 1, 1));
        senior2.setSocialWorker(socialWorker);
        Senior senior3 = Senior.createSenior("고수", Gender.M, LocalDate.of(2000, 1, 27), null,
                "010-0987-6132", "010-0987-6132", Address.createAddress("대구 광역시", "북구", "운암2로 한라아파트"),
                LocalDate.of(2019, 1, 1), LocalDate.of(2025, 1, 1));
        senior3.setSocialWorker(socialWorker);
        Senior senior4 = Senior.createSenior("구본식", Gender.M, LocalDate.of(1966, 1, 27), null,
                "010-1111-6132", "010-1234-6132", Address.createAddress("대구 광역시", "북구", "운암 3로 미진아파트"),
                LocalDate.of(2012, 1, 1), LocalDate.of(2025, 1, 1));
        senior4.setSocialWorker(socialWorker);
        seniorRepository.save(senior1);
        seniorRepository.save(senior2);
        seniorRepository.save(senior3);
        seniorRepository.save(senior4);

        //노인 이미지 및 상태 더미 데이터 세팅
        Image image1 = Image.createImage(null, null, null, LocalDate.of(2023, 6, 1),
                LocalTime.now(), EmotionCode.HAPPY);
        image1.setSenior(senior1);
        Image image2 = Image.createImage(null, null, null, LocalDate.of(2023, 6, 1),
                LocalTime.now(), EmotionCode.HAPPY);
        image2.setSenior(senior1);
        Image image3 = Image.createImage(null, null, null, LocalDate.of(2023, 6, 1),
                LocalTime.now(), EmotionCode.SAD);
        image3.setSenior(senior1);
        Image image4 = Image.createImage(null, null, null, LocalDate.of(2023, 6, 1),
                LocalTime.now(), EmotionCode.ANGRY);
        image4.setSenior(senior1);

        Image image5 = Image.createImage(null, null, null, LocalDate.of(2023, 6, 2),
                LocalTime.now(), EmotionCode.HAPPY);
        image5.setSenior(senior1);
        Image image6 = Image.createImage(null, null, null, LocalDate.of(2023, 6, 2),
                LocalTime.now(), EmotionCode.SAD);
        image6.setSenior(senior1);
        Image image7 = Image.createImage(null, null, null, LocalDate.of(2023, 6, 2),
                LocalTime.now(), EmotionCode.SAD);
        image7.setSenior(senior1);
        Image image8 = Image.createImage(null, null, null, LocalDate.of(2023, 6, 2),
                LocalTime.now(), EmotionCode.ANGRY);
        image8.setSenior(senior1);

        Image image9 = Image.createImage(null, null, null, LocalDate.of(2023, 6, 3),
                LocalTime.of(13,1,1), EmotionCode.HAPPY);
        image9.setSenior(senior1);
        Image image10 = Image.createImage(null, null, null, LocalDate.of(2023, 6, 3),
                LocalTime.of(14,1,1), EmotionCode.SAD);
        image10.setSenior(senior1);
        Image image11 = Image.createImage(null, null, null, LocalDate.of(2023, 6, 3),
                LocalTime.of(15,1,1), EmotionCode.ANGRY);
        image11.setSenior(senior1);
        Image image12 = Image.createImage(null, null, null, LocalDate.of(2023, 6, 3),
                LocalTime.of(12,1,1), EmotionCode.ANGRY);
        image12.setSenior(senior1);

        Image image13 = Image.createImage(null, null, null, LocalDate.of(2023, 6, 4),
                LocalTime.now(), EmotionCode.HAPPY);
        image13.setSenior(senior1);
        Image image14 = Image.createImage(null, null, null, LocalDate.of(2023, 6, 4),
                LocalTime.now(), EmotionCode.ANGRY);
        image14.setSenior(senior1);
        Image image15 = Image.createImage(null, null, null, LocalDate.of(2023, 6, 4),
                LocalTime.now(), EmotionCode.SAD);
        image15.setSenior(senior1);
        Image image16 = Image.createImage(null, null, null, LocalDate.of(2023, 6, 4),
                LocalTime.now(), EmotionCode.ANGRY);
        image16.setSenior(senior1);

        Image image17 = Image.createImage(null, null, null, LocalDate.of(2023, 6, 5),
                LocalTime.now(), EmotionCode.HAPPY);
        image17.setSenior(senior1);
        Image image18 = Image.createImage(null, null, null, LocalDate.of(2023, 6, 5),
                LocalTime.now(), EmotionCode.SAD);
        image18.setSenior(senior1);
        Image image19 = Image.createImage(null, null, null, LocalDate.of(2023, 6, 5),
                LocalTime.now(), EmotionCode.SAD);
        image19.setSenior(senior1);
        Image image20 = Image.createImage(null, null, null, LocalDate.of(2023, 6, 5),
                LocalTime.now(), EmotionCode.ANGRY);
        image20.setSenior(senior1);

        imageRepository.save(image1);
        imageRepository.save(image2);
        imageRepository.save(image3);
        imageRepository.save(image4);
        imageRepository.save(image5);
        imageRepository.save(image6);
        imageRepository.save(image7);
        imageRepository.save(image8);
        imageRepository.save(image9);
        imageRepository.save(image10);
        imageRepository.save(image11);
        imageRepository.save(image12);
        imageRepository.save(image13);
        imageRepository.save(image14);
        imageRepository.save(image15);
        imageRepository.save(image16);
        imageRepository.save(image17);
        imageRepository.save(image18);
        imageRepository.save(image19);
        imageRepository.save(image20);

        //노인 질병 더미 데이터 세팅
        Illness illness1 = Illness.createIllness(LocalDate.of(2022, 2, 4), LocalDate.of(2023, 4, 3),"당뇨");
        illness1.setSenior(senior1);
        Illness illness2 = Illness.createIllness(LocalDate.of(2022, 3, 2), LocalDate.of(2023, 3, 10), "심정 질환");
        illness2.setSenior(senior1);
        Illness illness3 = Illness.createIllness(LocalDate.of(2022, 1, 1), LocalDate.of(2023, 1, 1), "고혈압");
        illness3.setSenior(senior1);
        illnessRepository.save(illness1);
        illnessRepository.save(illness2);
        illnessRepository.save(illness3);

    }


}
