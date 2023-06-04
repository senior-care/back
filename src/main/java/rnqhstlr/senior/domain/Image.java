package rnqhstlr.senior.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageNo;

    private String imagePath;
    private String originalName;
    private String storeName;

    private LocalDate detectDate;
    private LocalTime detectTime;

    @Enumerated(EnumType.STRING)
    private EmotionCode emotionCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seniorNo")
    private Senior senior;

    public static Image createImage(String imagePath, String originalName, String storeName, LocalDate detectDate, LocalTime detectTime, EmotionCode code){
        Image image = new Image();
        image.imagePath = imagePath;
        image.originalName = originalName;
        image.storeName = storeName;
        image.detectDate = detectDate;
        image.detectTime = detectTime;
        image.emotionCode = code;
        return image;
    }

    public void setSenior(Senior senior){
        this.senior = senior;
    }

}
