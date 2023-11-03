package com.project.hamsterd.repo;

import com.project.hamsterd.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PostDAO extends JpaRepository<Post, Integer> {


     //게시물 조회수 추가하기
     @Query(value="UPDATE tb_post SET board_view = board_view + 1 WHERE post_no =:postNo", nativeQuery=true)
     Post updateBoardView(int postNo);


     //검색창에 게시물내용 값 작성시 게시판 전체조회
     @Query(value = "SELECT * FROM tb_post WHERE post_content LIKE %:postContent%", nativeQuery = true)
     Page<Post> findSearchContent(String postContent, Pageable pageable);

     //검색창에 게시물 제목 값 작성시 게시판 전체조회
     @Query(value = "SELECT * FROM tb_post WHERE post_title LIKE %:postTitle%", nativeQuery = true)
     Page<Post> findSearchTitle(String postTitle, Pageable pageable);
    }
