package com.brieuc.dailymon.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brieuc.dailymon.entity.model.Model;

public interface ModelRepository extends JpaRepository<Model, UUID> {
      
}
