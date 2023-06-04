package rnqhstlr.senior.dto;

import lombok.*;
import rnqhstlr.senior.domain.EmotionCode;
import rnqhstlr.senior.domain.Illness;
import rnqhstlr.senior.domain.Image;
import rnqhstlr.senior.domain.Senior;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Data
public class SeniorDetails {

    private SeniorInfo seniorInfo;
    private List<IllnessInfo> illnessInfo;
    private List<CalendarInfo> calendarInfo;

    public static SeniorDetails createSeniorDetails(Senior senior, List<Illness> illnesses, List<Image> images){
        SeniorDetails seniorDetails = new SeniorDetails();

        seniorDetails.seniorInfo = new SeniorInfo(senior);
        seniorDetails.illnessInfo = illnesses.stream()
                .map(i -> new IllnessInfo(i))
                .collect(Collectors.toList());
        seniorDetails.calendarInfo = images.stream()
                .map(i -> new CalendarInfo(i))
                .collect(Collectors.toList());
        return seniorDetails;
    }

    @NoArgsConstructor
    @Data
    public static class SeniorInfo {
        String name;
        String gender;
        String profile;
        String birth;
        String sido;
        String sigungu;
        String details;
        String majorPhone;
        String minorPhone;
        String registrationData;
        String endDate;

        public SeniorInfo(Senior senior){
            this.name = senior.getName();
            this.gender = senior.getGender().getDes();
            this.profile = senior.getImagePath();
            this.birth = senior.getBirth().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            this.sido = senior.getAddress().getSido();
            this.sigungu = senior.getAddress().getSigungu();
            this.details = senior.getAddress().getDetails();
            this.majorPhone = senior.getMainPhone();
            this.minorPhone = senior.getSubPhone();
            this.registrationData = senior.getRegistrationDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            this.endDate = senior.getEndDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        }
    }

    @NoArgsConstructor
    @Data
    public static class IllnessInfo{
        String illnessCode;
        String createdDate;
        String recoveryDate;

        public IllnessInfo(Illness illness){
            this.illnessCode = illness.getIllnessCode();
            this.createdDate = illness.getCreatedDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            this.recoveryDate = illness.getRecoveryDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        }
    }

    @NoArgsConstructor
    @Data
    public static class CalendarInfo{
        String date;
        String state;

        public CalendarInfo(Image image){
            this.date = image.getDetectDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            this.state = image.getEmotionCode().getDes();
        }
    }
}
