package com.brieuc.dailymon.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brieuc.dailymon.entity.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, UUID> {
    
}
