package rnqhstlr.senior.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rnqhstlr.senior.domain.EmotionCode;
import rnqhstlr.senior.domain.Image;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("select i.emotionCode " +
            "from Image i " +
            "where i.senior.seniorNo=:no " +
            "and i.detectDate = (select im.detectDate from Image im where im.senior.seniorNo=:no order by im.detectDate desc limit 1) " +
            "group by i.emotionCode " +
            "order by count(*) desc " +
            "limit 1 ")
    public Optional<EmotionCode> findMostFrequentEmotionCodeInDay(@Param("no")Long seniorNo);


}
