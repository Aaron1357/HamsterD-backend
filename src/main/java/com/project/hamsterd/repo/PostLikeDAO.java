package com.project.hamsterd.repo;

import com.project.hamsterd.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeDAO extends JpaRepository<PostLike, Integer> {
}
