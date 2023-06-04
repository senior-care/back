package rnqhstlr.senior.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalTime;

@Entity
@Getter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageNo;

    private String imagePath;
    private String originalName;
    private String storeName;
    private LocalTime detectTime;

    @Enumerated(EnumType.STRING)
    private EmotionCode emotionCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seniorImageNo")
    private SeniorImage seniorImage;

}
