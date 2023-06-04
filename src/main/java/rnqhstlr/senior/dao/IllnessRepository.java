package rnqhstlr.senior.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rnqhstlr.senior.domain.Illness;

import java.util.List;

public interface IllnessRepository extends JpaRepository<Illness, Long> {

    public List<Illness> findBySenior_SeniorNoOrderByCreatedDateDesc(Long seniorNo);
}
