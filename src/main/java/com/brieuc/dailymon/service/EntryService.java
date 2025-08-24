package com.brieuc.dailymon.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.brieuc.dailymon.entity.entry.Entry;

@Service
public interface EntryService {
      LocalDate getMinEntryDate();
      List<Entry> getEntriesByDate(LocalDate date);
      HashMap<String, Double> getSummaryInfo(LocalDate fromDate, LocalDate toDate);
      Entry createEntry(Entry entry);
      Entry updateEntry(Entry entry);
      void deleteEntry(UUID id);
}
