package com.project.hamsterd.repo;

import com.project.hamsterd.domain.Member;
import com.project.hamsterd.domain.Post;
import com.project.hamsterd.domain.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberDAO extends JpaRepository<Member, Integer > {

    @Query(value="SELECT * FROM TB_MEMBER WHERE ID = :id", nativeQuery=true)
    Member findByMemberId(@Param("id") String id);

    @Query(value="SELECT * FROM TB_MEMBER WHERE AUTHORITY = 'GROUP_MANAGER'", nativeQuery=true)
    List<Member> getManagerList();

    @Query(value="SELECT * FROM TB_MEMBER WHERE GROUP_NO = :groupNo", nativeQuery=true)
    List<Member> findByGroupNo(@Param("groupNo") int groupNo);

    @Query(value="SELECT * FROM TB_MEMBER WHERE GROUP_NO = :groupNo AND AUTHORITY = 'GROUP_MANAGER'", nativeQuery=true)
    Member findManager(@Param("groupNo") int groupNo);

    @Query(value="SELECT * FROM TB_MEMBER WHERE MEMBER_NO = :memberNo", nativeQuery=true)
    Member showMemberbyMemberNO(@Param("memberNo") int memberNo);




    @Query(value="SELECT * FROM TB_MEMBER WHERE NICKNAME = :nick", nativeQuery=true)
    Member findByNick(@Param("nick") String nick);

}