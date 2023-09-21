package com.project.hamsterd.repo;

import com.project.hamsterd.domain.InComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InCommentDAO extends JpaRepository<InComment, Integer> {
}
