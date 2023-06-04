package rnqhstlr.senior.api;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rnqhstlr.senior.domain.SocialWorker;
import rnqhstlr.senior.dto.SeniorList;
import rnqhstlr.senior.dto.SeniorListResponse;
import rnqhstlr.senior.service.SeniorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SeniorController {

    private final SeniorService seniorService;

    @GetMapping("/senior")
    public ResponseEntity<SeniorListResponse> seniorList(HttpSession session){

        SocialWorker loginWorker = (SocialWorker) session.getAttribute(SessionConst.LOGIN_SOCIAL_WORKER);

        List<SeniorList> seniorList = seniorService.findSeniors(loginWorker.getSocialWorkerNo());
        return ResponseEntity.ok(new SeniorListResponse(seniorList));
    }
}
