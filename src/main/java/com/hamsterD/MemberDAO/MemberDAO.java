package com.hamsterD.MemberDAO;

import com.hamsterD.MemberVo.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
public interface MemberDAO extends JpaRepository<Member, Integer > {

}
