package rnqhstlr.senior.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rnqhstlr.senior.dao.IllnessRepository;
import rnqhstlr.senior.dao.ImageRepository;
import rnqhstlr.senior.dao.SeniorRepository;
import rnqhstlr.senior.domain.EmotionCode;
import rnqhstlr.senior.domain.Illness;
import rnqhstlr.senior.domain.Image;
import rnqhstlr.senior.domain.Senior;
import rnqhstlr.senior.dto.SeniorDetails;
import rnqhstlr.senior.dto.SeniorList;
import rnqhstlr.senior.exception.NotExistSeniorException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SeniorServiceImpl implements SeniorService{

    private final SeniorRepository seniorRepository;
    private final ImageRepository imageRepository;
    private final IllnessRepository illnessRepository;

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

    @Override
    public SeniorDetails findSenior(Long seniorNo, LocalDate startDay, LocalDate endDay) {

        //노인 정보 조회
        Senior findSenior = seniorRepository.findById(seniorNo)
                .orElseThrow(() -> new NotExistSeniorException("존재하지 않은 노인입니다."));

        //노인 질병 조회
        List<Illness> findIllnessList = illnessRepository.findBySenior_SeniorNoOrderByCreatedDateDesc(findSenior.getSeniorNo());

        //일별 최대 감정 코드 찾기
        List<Image> findImages = new ArrayList<>();
        for(LocalDate start = startDay; start.isBefore(endDay)||start.isEqual(endDay); start=start.plusDays(1)){
            imageRepository.findEmotionCodeByDate(seniorNo, start)
                    .ifPresent(i -> findImages.add(i));
        }

        return SeniorDetails.createSeniorDetails(findSenior, findIllnessList, findImages);
    }

}
