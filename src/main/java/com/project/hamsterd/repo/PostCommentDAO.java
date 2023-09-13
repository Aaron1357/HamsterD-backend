package com.project.hamsterd.repo;

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
    @Query(value = "SELECT * FROM TB_COMMENT WHERE COMMENT_NO = :commentNO", nativeQuery = true)
    List<PostComment> findByCommentNo(@Param("commentNo") int commentNo);

}
