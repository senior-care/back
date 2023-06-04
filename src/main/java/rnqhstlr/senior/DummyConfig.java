package rnqhstlr.senior;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rnqhstlr.senior.dao.ImageRepository;
import rnqhstlr.senior.dao.SeniorRepository;
import rnqhstlr.senior.dao.SocialWorkerRepository;

@Configuration
@RequiredArgsConstructor
public class DummyConfig {

    private final SocialWorkerRepository socialWorkerRepository;
    private final SeniorRepository seniorRepository;
    private final ImageRepository imageRepository;

    @Bean
    public DummyDataInit dummyDataInit(){
        return new DummyDataInit(socialWorkerRepository, seniorRepository, imageRepository);
    }
}
