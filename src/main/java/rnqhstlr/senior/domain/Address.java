package rnqhstlr.senior.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

    private String sido;
    private String sigungu;
    private String details;
}
