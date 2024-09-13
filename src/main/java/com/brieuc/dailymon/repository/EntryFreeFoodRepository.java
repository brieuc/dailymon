package com.brieuc.dailymon.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brieuc.dailymon.entity.entry.EntryFreeFood;

@Repository
public interface EntryFreeFoodRepository extends JpaRepository<EntryFreeFood, UUID> {
        public List<EntryFreeFood> findByDate(LocalDate date);  
}
