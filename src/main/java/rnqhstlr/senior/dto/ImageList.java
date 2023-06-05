package rnqhstlr.senior.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rnqhstlr.senior.domain.Image;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class ImageList {

    private String detectTime;
    private String emotionName;
    private String imagePath;

    public ImageList(Image image){
        this.detectTime = image.getDetectTime().format(DateTimeFormatter.ofPattern("HH-mm-ss"));
        this.emotionName = image.getEmotionCode().getDes();
        this.imagePath = image.getImagePath();
    }
}
