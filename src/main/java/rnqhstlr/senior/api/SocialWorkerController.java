package rnqhstlr.senior.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rnqhstlr.senior.domain.SocialWorker;
import rnqhstlr.senior.dto.LoginParam;
import rnqhstlr.senior.service.LoginService;

@RestController
@RequiredArgsConstructor
public class SocialWorkerController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginParam loginParam, HttpServletRequest request){

        SocialWorker loginSocialWorker = loginService.login(loginParam.getUserId(), loginParam.getPassword());

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_SOCIAL_WORKER, loginSocialWorker.getUserId());
        return ResponseEntity.ok().build();
    }

}
