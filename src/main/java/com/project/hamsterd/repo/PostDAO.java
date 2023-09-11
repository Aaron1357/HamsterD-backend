package com.project.hamsterd.repo;

import com.project.hamsterd.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDAO extends JpaRepository<Post, Integer> {
}
