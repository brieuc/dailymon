package com.brieuc.dailymon.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.brieuc.dailymon.entity.entry.EntryFood;

public interface EntryFoodRepository extends JpaRepository<EntryFood, UUID>{
    
    public ArrayList<EntryFood> findByDate(LocalDate date);
    
    @Query(value = "SELECT MIN(DATE) FROM ENTRY_FOOD", 
          nativeQuery = true)
    LocalDate findMinEntryDate();
}
