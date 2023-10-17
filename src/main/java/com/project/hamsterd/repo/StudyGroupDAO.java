package com.project.hamsterd.repo;

import com.project.hamsterd.domain.Member;
import com.project.hamsterd.domain.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyGroupDAO extends JpaRepository<StudyGroup, Integer> {

//    @Query(value="SELECT * FROM TB_MEMBER WHERE ID = :id", nativeQuery=true)
//    Member findByMemberId(@Param("id") String id);
}
