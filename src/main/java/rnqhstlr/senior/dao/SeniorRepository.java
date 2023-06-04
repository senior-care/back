package rnqhstlr.senior.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rnqhstlr.senior.domain.Senior;

import java.util.List;

public interface SeniorRepository extends JpaRepository<Senior, Long> {

    List<Senior> findBySocialWorker_SocialWorkerNoOrderByRegistrationDate(Long socialWorkerNo);
}
