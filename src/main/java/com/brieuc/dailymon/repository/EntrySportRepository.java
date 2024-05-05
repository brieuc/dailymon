package com.brieuc.dailymon.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brieuc.dailymon.entity.entry.EntrySport;

public interface EntrySportRepository extends JpaRepository<EntrySport, UUID> {
    
    public ArrayList<EntrySport> findByDate(LocalDate date);
}
