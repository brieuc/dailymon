package com.brieuc.dailymon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brieuc.dailymon.entity.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {
      Optional<User> findByUsername(String username);
}
