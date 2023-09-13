package com.project.hamsterd.repo;

import com.project.hamsterd.domain.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyGroupDAO extends JpaRepository<StudyGroup, Integer> {

}
