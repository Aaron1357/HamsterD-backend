package com.project.hamsterd.repo;

import com.project.hamsterd.domain.GroupComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupCommentDAO extends JpaRepository<GroupComment, Integer> {
    @Query(value= "SELECT * FROM TB_GROUP_COMMENT WHERE GROUP_NO = :groupNo", nativeQuery=true)
    List<GroupComment> findByGroupNo(@Param("groupNo") int groupNo);
}
