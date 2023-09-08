package com.project.hamsterd.repo;

import com.project.hamsterd.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleDAO extends JpaRepository<Schedule, Integer> {
}
