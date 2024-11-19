package com.example.tdd.repository.user;

import com.example.tdd.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String name); // 이름으로 사용자검색
}
