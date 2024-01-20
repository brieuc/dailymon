package com.brieuc.dailymon.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brieuc.dailymon.entity.Entry;

public interface EntryRepository extends JpaRepository<Entry, UUID>{

    public ArrayList<Entry> findByDate(LocalDate date);
}
