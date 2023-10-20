package com.project.hamsterd.repo;

import com.project.hamsterd.domain.Member;
import com.project.hamsterd.domain.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyGroupDAO extends JpaRepository<StudyGroup, Integer> {

}
