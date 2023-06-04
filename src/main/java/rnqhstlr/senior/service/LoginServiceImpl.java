package rnqhstlr.senior.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rnqhstlr.senior.dao.SocialWorkerRepository;
import rnqhstlr.senior.domain.SocialWorker;
import rnqhstlr.senior.exception.InvalidPasswordException;
import rnqhstlr.senior.exception.NotExistSocialWorker;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginServiceImpl implements LoginService{

    private final SocialWorkerRepository socialWorkerRepository;

    @Override
    public SocialWorker login(String userId, String password) {

        SocialWorker socialWorker = socialWorkerRepository.findByUserId(userId)
                .orElseThrow(() -> new NotExistSocialWorker("존재하지 않는 복시자 입니다."));

        if(!socialWorker.getPassword().equals(password)) {
            throw new InvalidPasswordException("패스워드가 일치하지 않습니다.");
        }

        return socialWorker ;
    }

}
