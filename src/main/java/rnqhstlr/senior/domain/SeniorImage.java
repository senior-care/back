package rnqhstlr.senior.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class SeniorImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seniorImage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seniorNo")
    private Senior senior;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calenderNo")
    private Calender calender;

}
