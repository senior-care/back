package rnqhstlr.senior.domain;

import lombok.Getter;

@Getter
public enum Gender {

    M("남성"), F("여성")
    ;

    private String des;
    Gender(String des){
        this.des = des;
    }

}
