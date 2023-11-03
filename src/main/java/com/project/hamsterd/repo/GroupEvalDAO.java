package com.project.hamsterd.repo;

import com.project.hamsterd.domain.GroupEval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupEvalDAO extends JpaRepository<GroupEval, Integer> {


    // memberNo번 회원이 작성한 그룹리뷰 조회(그룹까지 필터링)
    @Query(value = "SELECT * FROM TB_GROUP_REV WHERE MEMBER_NO = :memberNo AND GROUP_NO = :groupNo", nativeQuery = true)
    List<GroupEval> findByMemberNoAndGroupNo(@Param("memberNo") int memberNo, @Param("groupNo") int groupNo);

    // groupNo의 그룹리뷰 조회
    @Query(value = "SELECT * FROM TB_GROUP_REV WHERE GROUP_NO = :groupNo", nativeQuery = true)
    List<GroupEval> findByGroupNo(@Param("groupNo") int groupNo);

    // groupNo의 그룹리뷰 조회
    @Query(value = "SELECT * FROM TB_GROUP_REV WHERE MEMBER_NO = :memberNo", nativeQuery = true)
    GroupEval findByMemberNo(@Param("memberNo") int memberNo);

    // groupNo의 평균점수 조회
    @Query(value = "SELECT AVG(GROUP_SCORE) FROM TB_GROUP_REV WHERE GROUP_NO = :groupNo", nativeQuery = true)
        Integer getGroupAVG(@Param("groupNo") int groupNo);




}
