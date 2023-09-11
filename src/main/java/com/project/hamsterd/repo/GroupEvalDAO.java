package com.project.hamsterd.repo;

import com.project.hamsterd.domain.GroupEval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupEvalDAO extends JpaRepository<GroupEval, Integer> {
}
