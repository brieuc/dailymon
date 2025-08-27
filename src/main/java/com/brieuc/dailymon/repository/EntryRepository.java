package com.brieuc.dailymon.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.brieuc.dailymon.entity.entry.Entry;
import com.brieuc.dailymon.entity.model.Model;

@Repository
public interface EntryRepository extends JpaRepository<Entry, UUID> {
      List<Entry> findByModel(Model model);
      List<Entry> findByDate(LocalDate date);
      @Query("SELECT MIN(e.date) FROM Entry e")
      LocalDate findMinEntryDate();
}
