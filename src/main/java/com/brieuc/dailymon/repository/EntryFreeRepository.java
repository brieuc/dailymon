package com.brieuc.dailymon.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.brieuc.dailymon.entity.entry.EntryFree;

public interface EntryFreeRepository extends JpaRepository<EntryFree, UUID>{

    public ArrayList<EntryFree> findByDate(LocalDate date);
}
