package com.project.hamsterd.repo;

import com.project.hamsterd.domain.PersonalEval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalEvalDAO extends JpaRepository<PersonalEval, Integer> {


    // memberNo번 회원이 작성한 리뷰들 조회
    @Query(value = "SELECT * FROM TB_PER_REV WHERE MEMBER_NO = :memberNo", nativeQuery = true)
    List<PersonalEval> findByMemberId(@Param("memberNo") int memberNo);
}
