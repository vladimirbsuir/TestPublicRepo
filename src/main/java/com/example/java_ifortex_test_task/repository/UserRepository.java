package com.example.java_ifortex_test_task.repository;

import com.example.java_ifortex_test_task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select u.* from users u where u.id in (select user_id from sessions s " +
            "where s.user_id = u.id and s.device_type = 1)", nativeQuery = true)
    List<User> getUsersWithAtLeastOneMobileSession();

    @Query(value = "select u.* from sessions s join users u on s.user_id = u.id " +
            "group by u.id order by count(u.id) desc limit 1", nativeQuery = true)
    User getUserWithMostSessions();
}
