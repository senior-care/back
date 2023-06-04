package rnqhstlr.senior.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rnqhstlr.senior.domain.EmotionCode;
import rnqhstlr.senior.domain.Gender;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class SeniorList {

    private Long no;
    private String name;
    private String gender;
    private String birth;
    private String majorPhone;
    private String minorPhone;
    private String state;

    public static SeniorList createSeniorList(Long no, String name, Gender gender, LocalDate birth, String majorPhone, String minorPhone, EmotionCode code){
        SeniorList seniorList = new SeniorList();
        seniorList.no = no;
        seniorList.name = name;
        seniorList.gender = gender.getDes();
        seniorList.birth = birth.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        seniorList.majorPhone = majorPhone;
        seniorList.minorPhone = minorPhone;
        seniorList.state = code.getDes();
        return seniorList;
    }

}
