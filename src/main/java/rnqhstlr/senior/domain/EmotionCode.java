package rnqhstlr.senior.domain;

import lombok.Getter;

@Getter
public enum EmotionCode {
    HAPPY(1, "기쁨"), SAD(2, "슬픔"), ANGRY(3,"화남"), NATUAL(4, "중립"),
    NONE(10, "상태 없음"),
    ;

    private int code;
    private String des;

    EmotionCode(int code, String des){
        this.code = code;
        this.des = des;
    }
}
