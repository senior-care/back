package rnqhstlr.senior.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Senior {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seniorNo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "socialWorkNo")
    private SocialWorker socialWorker;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String birth;
    private String imagePath;
    private String mainPhone;
    private String subPhone;
    @Embedded
    private Address address;
    private LocalDate registrationDate;
    private LocalDate endDate;
}
