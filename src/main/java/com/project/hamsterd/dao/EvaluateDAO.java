package com.project.hamsterd.dao;

import com.project.hamsterd.vo.Evaluate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluateDAO  extends JpaRepository<Evaluate, Integer> {
}
