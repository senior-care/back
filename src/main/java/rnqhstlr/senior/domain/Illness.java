package rnqhstlr.senior.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Illness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long illnessNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seniorNo")
    private Senior senior;

    private LocalDate createdDate;
    private LocalDate recoveryDate;
    private String illnessCode;

    public static Illness createIllness(LocalDate createdDate, LocalDate recoveryDate, String illnessCode){
        Illness illness = new Illness();
        illness.createdDate = createdDate;
        illness.recoveryDate = recoveryDate;
        illness.illnessCode = illnessCode;
        return illness;
    }

    public void setSenior(Senior senior){
        this.senior = senior;
    }

}
