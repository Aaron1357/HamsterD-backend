package com.project.hamsterd.repo;

import com.project.hamsterd.domain.GroupEval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupEvalDAO extends JpaRepository<GroupEval, Integer> {

    @Query(value = "SELECT * FROM TB_GROUP_REV WHERE MEMBER_NO = :memberNo AND GROUP_NO = :groupNo", nativeQuery = true)
    List<GroupEval> findByMemberNoAndGroupNo(@Param("memberNo") int memberNo, @Param("groupNo") int groupNo);

}
