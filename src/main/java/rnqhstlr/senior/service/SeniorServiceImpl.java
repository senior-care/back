package rnqhstlr.senior.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rnqhstlr.senior.dao.ImageRepository;
import rnqhstlr.senior.dao.SeniorRepository;
import rnqhstlr.senior.domain.EmotionCode;
import rnqhstlr.senior.domain.Senior;
import rnqhstlr.senior.dto.SeniorList;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeniorServiceImpl implements SeniorService{

    private final SeniorRepository seniorRepository;
    private final ImageRepository imageRepository;

    @Override
    public List<SeniorList> findSeniors(Long socialWorkerNo) {

        List<Senior> findSeniors = seniorRepository.findBySocialWorker_SocialWorkerNoOrderByRegistrationDate(socialWorkerNo);

        return findSeniors.stream()
                .map(s -> {
                    EmotionCode emotionCode = imageRepository.findMostFrequentEmotionCodeInDay(s.getSeniorNo())
                            .orElseGet(() ->  EmotionCode.NONE);

                    return SeniorList.createSeniorList(
                            s.getSeniorNo(), s.getName(), s.getGender(), s.getBirth(), s.getMainPhone(), s.getSubPhone(), emotionCode);
                })
                .collect(Collectors.toList());
    }

}
