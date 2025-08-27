package com.brieuc.dailymon.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.brieuc.dailymon.entity.model.Model;
import com.brieuc.dailymon.entity.model.ModelFood;
import com.brieuc.dailymon.entity.model.ModelFree;
import com.brieuc.dailymon.entity.model.ModelSport;

@Repository
public interface ModelRepository extends JpaRepository<Model, UUID> {
      @Query("SELECT m FROM ModelFood m")
      List<ModelFood> findAllModelFood();

      @Query("SELECT m FROM ModelFree m")
      List<ModelFree> findAllModelFree();

      @Query("SELECT m FROM ModelSport m")
      List<ModelSport> findAllModelSport();
}

