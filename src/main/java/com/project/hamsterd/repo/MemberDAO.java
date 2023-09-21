package com.project.hamsterd.repo;

import com.project.hamsterd.domain.Member;
import com.project.hamsterd.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberDAO extends JpaRepository<Member, Integer > {

    @Query(value="SELECT * FROM TB_MEMBER WHERE member_no : memberNo", nativeQuery=true)
    Member findByMemberId(int memberNo);

}
