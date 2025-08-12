package com.brieuc.dailymon.mapper;

import org.springframework.stereotype.Component;

import com.brieuc.dailymon.dto.EntryDto;
import com.brieuc.dailymon.dto.EntryFoodDto;
import com.brieuc.dailymon.dto.EntryFreeDto;
import com.brieuc.dailymon.dto.EntryFreeFoodDto;
import com.brieuc.dailymon.dto.EntrySportDto;
import com.brieuc.dailymon.entity.entry.Entry;
import com.brieuc.dailymon.entity.entry.EntryFood;
import com.brieuc.dailymon.entity.entry.EntryFree;
import com.brieuc.dailymon.entity.entry.EntryFreeFood;
import com.brieuc.dailymon.entity.entry.EntrySport;

@Component
public class EntryMapperImpl implements EntryMapper {

    @Override
    public EntryDto toDto(Entry entry) {
        return switch (entry) {
            case EntryFood food -> toDto(food);
            case EntrySport sport -> toDto(sport);
            case EntryFree free -> toDto(free);
            case EntryFreeFood freeFood -> toDto(freeFood);
            default -> null;
        };
    }

    private EntryFoodDto toDto(EntryFood entry) {
        return EntryFoodDto.builder()
                // Champs spécifiques à EntryFood
                .quantity(entry.getQuantity())
                .foodType(entry.getModel().getFoodType()) // Depuis le model
                // Champs communs
                .id(entry.getId())
                .title(entry.getTitle())
                .description(entry.getDescription() == null ? 
                    entry.getModel().getTitle() : entry.getDescription())
                .date(entry.getDate())
                .modelId(entry.getModel().getId())
                .build();
    }

    private EntrySportDto toDto(EntrySport entry) {
        return EntrySportDto.builder()
                // Champs spécifiques à EntrySport
                .kcal(entry.getKcal())
                .aerobic(entry.getAerobic())
                .anaerobic(entry.getAnaerobic())
                .duration(entry.getDuration())
                .benefit(entry.getBenefit())
                // Champs communs
                .id(entry.getId())
                .title(entry.getTitle())
                .description(entry.getDescription())
                .date(entry.getDate())
                .modelId(entry.getModel().getId())
                .build();
    }

    private EntryFreeDto toDto(EntryFree entry) {
        return EntryFreeDto.builder()
                // Pas de champs spécifiques pour EntryFree
                // Champs communs
                .id(entry.getId())
                .title(entry.getTitle())
                .description(entry.getDescription())
                .date(entry.getDate())
                .modelId(entry.getModel().getId())
                .build();
    }

    private EntryFreeFoodDto toDto(EntryFreeFood entry) {
        return EntryFreeFoodDto.builder()
                // Champs spécifiques à EntryFreeFood
                .foodType(entry.getFoodType())
                .kcal(entry.getKcal())
                // Champs communs (hérite de EntryFreeDto)
                .id(entry.getId())
                .title(entry.getTitle())
                .description(entry.getDescription())
                .date(entry.getDate())
                .modelId(entry.getModel().getId())
                .build();
    }
}