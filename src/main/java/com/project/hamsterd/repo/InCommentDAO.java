package com.project.hamsterd.repo;

import com.project.hamsterd.domain.InComment;
import com.project.hamsterd.domain.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InCommentDAO extends JpaRepository<InComment, Integer> {

    @Query(value = "SELECT * FROM TB_IN_COMMENT WHERE POST_NO =:postNo AND COMMENT_NO = :commentNo", nativeQuery = true)
    List<InComment> findByCommentNo(@Param("postNo") int postNo,@Param("commentNo") int commentNo);

}
