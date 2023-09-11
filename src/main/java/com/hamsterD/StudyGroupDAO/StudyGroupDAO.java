package com.hamsterD.StudyGroupDAO;

import com.hamsterD.StudyGroupVO.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyGroupDAO extends JpaRepository<StudyGroup, Integer> {

}
