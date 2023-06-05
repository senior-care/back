package rnqhstlr.senior.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CalendarList {

    private LocalDate date;
    private String state;

}
