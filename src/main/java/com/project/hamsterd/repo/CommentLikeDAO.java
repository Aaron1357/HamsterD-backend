package com.project.hamsterd.repo;

import com.project.hamsterd.domain.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeDAO extends JpaRepository<CommentLike, Integer> {

}
