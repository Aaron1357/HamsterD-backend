package com.project.hamsterd.repo;

import com.project.hamsterd.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PostDAO extends JpaRepository<Post, Integer> {

    //특정 멤버의 모든 게시판 조회
    //SELECT * FROM post WHERE memberNo=?

     @Query(value="SELECT * FROM post WHERE member_No : memberNo", nativeQuery=true)
     List<Post> findByMemberId(String id);



     }
