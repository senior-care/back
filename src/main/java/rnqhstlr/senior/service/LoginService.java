package rnqhstlr.senior.service;

import rnqhstlr.senior.domain.SocialWorker;

public interface LoginService {

    public SocialWorker login(String id, String password);
}
