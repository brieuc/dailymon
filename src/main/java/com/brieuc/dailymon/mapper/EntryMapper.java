package com.brieuc.dailymon.mapper;

import com.brieuc.dailymon.dto.EntryDto;
import com.brieuc.dailymon.entity.entry.Entry;

public interface EntryMapper {
    EntryDto toDto(Entry entry);
    Entry toEntity(EntryDto entryDto);
}