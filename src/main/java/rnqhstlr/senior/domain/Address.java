package rnqhstlr.senior.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String sido;
    private String sigungu;
    private String details;

    public static Address createAddress(String sido, String sigungu, String details){
        Address address = new Address();
        address.sido = sido;
        address.sigungu = sigungu;
        address.details = details;
        return address;
    }
}
