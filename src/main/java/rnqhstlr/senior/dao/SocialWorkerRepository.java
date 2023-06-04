package rnqhstlr.senior.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import rnqhstlr.senior.domain.SocialWorker;

import java.util.Optional;

public interface SocialWorkerRepository extends JpaRepository<SocialWorker, Long> {

    public Optional<SocialWorker> findByUserId(String userId);
}
