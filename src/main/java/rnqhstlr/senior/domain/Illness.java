package rnqhstlr.senior.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Illness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long illnessNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "senioNo")
    private Senior senior;

    private LocalDate createdDate;
    private LocalDate recoveryDate;
    private String IllnessCode;
}
