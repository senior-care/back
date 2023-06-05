package rnqhstlr.senior.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import rnqhstlr.senior.dao.IllnessRepository;
import rnqhstlr.senior.dao.ImageRepository;
import rnqhstlr.senior.dao.SeniorRepository;
import rnqhstlr.senior.dao.SocialWorkerRepository;
import rnqhstlr.senior.domain.*;
import rnqhstlr.senior.dto.CalendarList;
import rnqhstlr.senior.dto.ImageList;
import rnqhstlr.senior.dto.SeniorDetails;
import rnqhstlr.senior.dto.SeniorList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SeniorServiceImplTest {

    @Autowired SocialWorkerRepository socialWorkerRepository;
    @Autowired SeniorRepository seniorRepository;
    @Autowired ImageRepository imageRepository;
    @Autowired SeniorService seniorService;
    @Autowired IllnessRepository illnessRepository;
    @Autowired CalendarService calendarService;

    SocialWorker saveSocialWorker;
    @BeforeEach
    void init(){
        //복지사 저장
        SocialWorker creatWorker = SocialWorker.createSocialWorker("김대죄", "1940-01-08", "test", "test", Gender.M,
                "010-3321-6132", null, LocalDate.of(2021, 5, 10));
        saveSocialWorker = socialWorkerRepository.save(creatWorker);
    }

    public Senior 노인_추가(String name, Gender gender, LocalDate birth, String imagePath, String mainPhone, String subPhone,
                        LocalDate registrationDate, LocalDate endDate){

        Address address = Address.createAddress("11", "1111", "details");
        Senior createSenior = Senior.createSenior(name, gender, birth, imagePath, mainPhone, subPhone, address, registrationDate, endDate);
        createSenior.setSocialWorker(saveSocialWorker);
        return seniorRepository.save(createSenior);
    }

    public Image 노인_빈이미지_추가(Senior senior, LocalDate detectDate, EmotionCode code){
        Image image = Image.createImage(null, null, null, detectDate, LocalTime.now(), code);
        image.setSenior(senior);
        return imageRepository.save(image);
    }

    public Image 노인_이미지_추가(Senior senior, String imagePath, String originalName, String storeName, LocalDate detectDate, LocalTime detectTime,EmotionCode code){
        Image image = Image.createImage(imagePath, originalName, storeName, detectDate, detectTime, code);
        image.setSenior(senior);
        return imageRepository.save(image);
    }

    public Illness 노인_질병_추가(Senior senior, LocalDate createdDate, LocalDate recoveryDate, String illnessCode){
        Illness createIllness = Illness.createIllness(createdDate, recoveryDate, illnessCode);
        createIllness.setSenior(senior);
        return illnessRepository.save(createIllness);
    }

    @Test
    @DisplayName("복지사가 관리 중인 노인 리스트를 조회한다.")
    @Transactional
    public void findSeniorList(){
        //given
        Senior senior1 = 노인_추가("한소회", Gender.F, LocalDate.of(1999, 7, 27), null,
                "010-3321-6132", "010-3321-6132", LocalDate.of(2020, 1, 1), LocalDate.of(2024, 1, 1));
        Senior senior2 = 노인_추가("이주명", Gender.F, LocalDate.of(1996, 1, 27), null,
                "010-1234-6132", "010-1234-6132", LocalDate.of(2022, 1, 1), LocalDate.of(2025, 1, 1));
        Senior senior3 = 노인_추가("고수", Gender.M, LocalDate.of(2000, 1, 27), null,
                "010-0987-6132", "010-0987-6132", LocalDate.of(2019, 1, 1), LocalDate.of(2025, 1, 1));

        노인_빈이미지_추가(senior1,LocalDate.of(2023, 6, 1), EmotionCode.HAPPY);
        노인_빈이미지_추가(senior1, LocalDate.of(2023, 6, 1), EmotionCode.HAPPY);
        노인_빈이미지_추가(senior1, LocalDate.of(2023, 6, 1), EmotionCode.SAD);
        노인_빈이미지_추가(senior1, LocalDate.of(2023, 6, 1), EmotionCode.ANGRY);

        노인_빈이미지_추가(senior1, LocalDate.of(2023, 6, 2), EmotionCode.SAD);
        노인_빈이미지_추가(senior1, LocalDate.of(2023, 6, 2), EmotionCode.HAPPY);
        노인_빈이미지_추가(senior1, LocalDate.of(2023, 6, 2), EmotionCode.SAD);
        노인_빈이미지_추가(senior1, LocalDate.of(2023, 6, 2), EmotionCode.ANGRY);

        //when
        List<SeniorList> findSeniors = seniorService.findSeniors(saveSocialWorker.getSocialWorkerNo());

        //then
        for(SeniorList list : findSeniors){
            System.out.println(list);
        }
    }

    @Test
    @DisplayName("노인 상세 조회에 성공하다.")
    @Transactional
    public void findSeniorDetails(){
        //given
        Senior senior = 노인_추가("한소회", Gender.F, LocalDate.of(1999, 7, 27), null,
                "010-3321-6132", "010-3321-6132", LocalDate.of(2020, 1, 1), LocalDate.of(2024, 1, 1));

        노인_질병_추가(senior, LocalDate.of(2022,3,3), LocalDate.of(2022,4,3), "1111");
        노인_질병_추가(senior, LocalDate.of(2022,2,2), LocalDate.of(2022,2,2), "2222");
        노인_질병_추가(senior, LocalDate.of(2022,1,1), LocalDate.of(2022,1,1),"3333");

        노인_빈이미지_추가(senior, LocalDate.of(2023, 6, 1) , EmotionCode.SAD);
        노인_빈이미지_추가(senior, LocalDate.of(2023, 6, 1) , EmotionCode.SAD);
        노인_빈이미지_추가(senior, LocalDate.of(2023, 6, 1) , EmotionCode.HAPPY);
        노인_빈이미지_추가(senior, LocalDate.of(2023, 6, 1) , EmotionCode.ANGRY);

        노인_빈이미지_추가(senior, LocalDate.of(2023, 6, 2) , EmotionCode.SAD);
        노인_빈이미지_추가(senior, LocalDate.of(2023, 6, 2) , EmotionCode.HAPPY);
        노인_빈이미지_추가(senior, LocalDate.of(2023, 6, 2) , EmotionCode.HAPPY);
        노인_빈이미지_추가(senior, LocalDate.of(2023, 6, 2) , EmotionCode.ANGRY);

        노인_빈이미지_추가(senior, LocalDate.of(2023, 6, 3) , EmotionCode.ANGRY);
        노인_빈이미지_추가(senior, LocalDate.of(2023, 6, 3) , EmotionCode.SAD);
        노인_빈이미지_추가(senior, LocalDate.of(2023, 6, 3) , EmotionCode.HAPPY);
        노인_빈이미지_추가(senior, LocalDate.of(2023, 6, 3) , EmotionCode.ANGRY);

        //when
        SeniorDetails seniorDetails = seniorService.findSenior(senior.getSeniorNo(), LocalDate.of(2023, 6, 1),
                LocalDate.of(2023, 6, 3));

        //then
        System.out.println(seniorDetails);
    }

    @Test
    @DisplayName("지정된 년, 월에 존재하는 일별 최대 감정 코드 리스트 조회에 성공하다.")
    @Transactional
    public void findCalendarList(){
        //given
        Senior senior = 노인_추가("한소회", Gender.F, LocalDate.of(1999, 7, 27), null,
                "010-3321-6132", "010-3321-6132", LocalDate.of(2020, 1, 1), LocalDate.of(2024, 1, 1));

        노인_빈이미지_추가(senior, LocalDate.of(2023, 6, 1) , EmotionCode.SAD);
        노인_빈이미지_추가(senior, LocalDate.of(2023, 6, 1) , EmotionCode.SAD);
        노인_빈이미지_추가(senior, LocalDate.of(2023, 6, 1) , EmotionCode.HAPPY);
        노인_빈이미지_추가(senior, LocalDate.of(2023, 6, 1) , EmotionCode.ANGRY);

        노인_빈이미지_추가(senior, LocalDate.of(2023, 6, 2) , EmotionCode.SAD);
        노인_빈이미지_추가(senior, LocalDate.of(2023, 6, 2) , EmotionCode.HAPPY);
        노인_빈이미지_추가(senior, LocalDate.of(2023, 6, 2) , EmotionCode.HAPPY);
        노인_빈이미지_추가(senior, LocalDate.of(2023, 6, 2) , EmotionCode.ANGRY);

        노인_빈이미지_추가(senior, LocalDate.of(2023, 6, 3) , EmotionCode.ANGRY);
        노인_빈이미지_추가(senior, LocalDate.of(2023, 6, 3) , EmotionCode.SAD);
        노인_빈이미지_추가(senior, LocalDate.of(2023, 6, 3) , EmotionCode.HAPPY);
        노인_빈이미지_추가(senior, LocalDate.of(2023, 6, 3) , EmotionCode.ANGRY);

        //when
        List<CalendarList> calendarStateList =
                calendarService.findCalendarStateList(senior.getSeniorNo(), LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 30));

        //then
        Assertions.assertThat(calendarStateList.size()).isEqualTo(3);
        for(CalendarList list : calendarStateList){
            System.out.println(list);
        }
    }

    @Test
    @DisplayName("지정된 년,월,일에 존재하는 사진 리스트 조회에 성공하다.")
    @Transactional
    public void findCalendarDetails(){
        //given
        Senior senior = 노인_추가("한소회", Gender.F, LocalDate.of(1999, 7, 27), null,
                "010-3321-6132", "010-3321-6132", LocalDate.of(2020, 1, 1), LocalDate.of(2024, 1, 1));

        노인_이미지_추가(senior, "1111", "1111", "1111",LocalDate.of(2023, 6, 1),LocalTime.now(),EmotionCode.SAD);
        노인_이미지_추가(senior, "2222", "2222","2222",LocalDate.of(2023, 6, 1),LocalTime.now(),EmotionCode.SAD);
        노인_이미지_추가(senior, "3333", "3333", "3333",LocalDate.of(2023, 6, 1),LocalTime.now(),EmotionCode.HAPPY);
        노인_이미지_추가(senior, "4444", "4444", "4444", LocalDate.of(2023, 6, 1),LocalTime.of(1,1,1),EmotionCode.ANGRY);

        //when
        List<ImageList> calendarDetails = calendarService.findCalendarDetails(senior.getSeniorNo(), LocalDate.of(2023, 6, 1));

        //then
        Assertions.assertThat(calendarDetails.size()).isEqualTo(4);
        for(ImageList list : calendarDetails){
            System.out.println(list);
        }
    }
}