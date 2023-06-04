package rnqhstlr.senior.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SocialWorker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long socialWorkerNo;
    private String name;
    private String birth;
    private String userId;
    private String password;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String phone;
    private String imagePath;
    private LocalDate joiningDate;

    public static SocialWorker createSocialWorker(String name, String birth, String id, String password, Gender gender, String phone, String imagePath,
                                           LocalDate joiningDate){
        SocialWorker socialWorker = new SocialWorker();
        socialWorker.name = name;
        socialWorker.birth = birth;
        socialWorker.userId = id;
        socialWorker.password = password;
        socialWorker.gender = gender;
        socialWorker.phone = phone;
        socialWorker.imagePath = imagePath;
        socialWorker.joiningDate = joiningDate;
        return socialWorker;
    }
}
