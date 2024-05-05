package com.brieuc.dailymon.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brieuc.dailymon.entity.model.ModelFood;

public interface ModelFoodRepository extends JpaRepository<ModelFood, UUID>{
    
}
