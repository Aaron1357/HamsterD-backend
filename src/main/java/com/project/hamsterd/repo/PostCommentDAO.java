package com.project.hamsterd.repo;

import com.project.hamsterd.domain.Post;
import com.project.hamsterd.domain.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCommentDAO extends JpaRepository<PostComment, Integer> {

    // SELECT * FROM TB_COMMENT WHERE COMMENT_NO = ?
    // 게시물에 달려있는 댓글 전체 조회
    @Query(value = "SELECT * FROM TB_COMMENT WHERE post_no = :postNo", nativeQuery = true)
    List<PostComment> findByPostNo(@Param("postNo") int postNo);



}
