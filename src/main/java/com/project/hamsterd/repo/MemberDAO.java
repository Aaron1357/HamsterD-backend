package com.project.hamsterd.repo;

import com.project.hamsterd.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
public interface MemberDAO extends JpaRepository<Member, Integer > {

}
