package com.brieuc.dailymon.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brieuc.dailymon.entity.model.ModelSport;

public interface ModelSportRepository extends JpaRepository<ModelSport, UUID> {
    
}
