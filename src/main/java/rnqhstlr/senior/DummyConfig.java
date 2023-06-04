package rnqhstlr.senior;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rnqhstlr.senior.dao.SocialWorkerRepository;

@Configuration
@RequiredArgsConstructor
public class DummyConfig {

    private final SocialWorkerRepository socialWorkerRepository;

    @Bean
    public DummyDataInit dummyDataInit(){
        return new DummyDataInit(socialWorkerRepository);
    }
}
