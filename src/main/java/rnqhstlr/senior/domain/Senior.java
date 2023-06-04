package rnqhstlr.senior.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Senior {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seniorNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "socialWorkerNo")
    private SocialWorker socialWorker;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birth;

    private String imagePath;

    private String mainPhone;

    private String subPhone;

    @Embedded
    private Address address;

    private LocalDate registrationDate;

    private LocalDate endDate;

    public static Senior createSenior(String name, Gender gender, LocalDate birth, String imagePath, String mainPhone, String subPhone, Address address,
                                      LocalDate registrationDate, LocalDate endDate){
        Senior senior = new Senior();
        senior.name = name;
        senior.gender = gender;
        senior.birth = birth;
        senior.imagePath = imagePath;
        senior.mainPhone = mainPhone;
        senior.subPhone = subPhone;
        senior.address = address;
        senior.registrationDate = registrationDate;
        senior.endDate = endDate;
        return senior;
    }

    public void setSocialWorker(SocialWorker socialWorker){
        this.socialWorker = socialWorker;
    }
}
