package com.example.java_ifortex_test_task.repository;

import com.example.java_ifortex_test_task.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    @Query(value = "select s.id, s.device_type - 1 as device_type, s.started_at_utc, s.ended_at_utc, s.user_id " +
            "from sessions s where s.device_type = 2 order by s.started_at_utc limit 1", nativeQuery = true)
    Session getFirstDesktopSession();

    @Query(value = "select s.id, s.device_type - 1 as device_type, s.started_at_utc, s.ended_at_utc, s.user_id " +
            "from sessions s join users u on s.user_id = u.id where u.deleted = false " +
            "and (date_part('year', s.ended_at_utc) < 2025 or s.ended_at_utc is null) order by s.started_at_utc desc", nativeQuery = true)
    List<Session> getSessionsFromActiveUsersEndedBefore2025();
}