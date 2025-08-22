package com.brieuc.dailymon.mapper;

import org.springframework.stereotype.Component;

import com.brieuc.dailymon.dto.EntryDto;
import com.brieuc.dailymon.dto.EntryFoodDto;
import com.brieuc.dailymon.dto.EntryFreeDto;
import com.brieuc.dailymon.dto.EntryFreeFoodDto;
import com.brieuc.dailymon.dto.EntrySportDto;
import com.brieuc.dailymon.dto.ModelFoodDto;
import com.brieuc.dailymon.dto.ModelFreeDto;
import com.brieuc.dailymon.dto.ModelSportDto;
import com.brieuc.dailymon.entity.entry.Entry;
import com.brieuc.dailymon.entity.entry.EntryFood;
import com.brieuc.dailymon.entity.entry.EntryFree;
import com.brieuc.dailymon.entity.entry.EntryFreeFood;
import com.brieuc.dailymon.entity.entry.EntrySport;

@Component  
public class EntryMapperWithModelImpl implements EntryMapper {

    private final ModelMapper modelMapper;

    public EntryMapperWithModelImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

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
                .model((ModelFoodDto) modelMapper.toDto(entry.getModel())) // ✅ Model inclus !
                // Champs communs
                .id(entry.getId())
                .title(entry.getTitle())
                .description(entry.getDescription() == null ? 
                    entry.getModel().getTitle() : entry.getDescription())
                .date(entry.getDate())
                .model((ModelFoodDto) modelMapper.toDto(entry.getModel()))
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
                .model((ModelSportDto) modelMapper.toDto(entry.getModel())) // ✅ Model inclus !
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
                .id(entry.getId())
                .title(entry.getTitle())
                .description(entry.getDescription())
                .date(entry.getDate())
                .modelId(entry.getModel().getId())
                // Pas de champs spécifiques pour EntryFree
                .model((ModelFreeDto) modelMapper.toDto(entry.getModel())) // ✅ Model inclus !
                // Champs communs

                .build();
    }

    private EntryFreeFoodDto toDto(EntryFreeFood entry) {
        return EntryFreeFoodDto.builder()
                // Champs spécifiques à EntryFreeFood
                .foodType(entry.getFoodType())
                .kcal(entry.getKcal())
                .model((ModelFreeDto) modelMapper.toDto(entry.getModel())) // ✅ Model inclus !
                // Champs communs
                .id(entry.getId())
                .title(entry.getTitle())
                .description(entry.getDescription())
                .date(entry.getDate())
                .modelId(entry.getModel().getId())
                .build();
    }
}