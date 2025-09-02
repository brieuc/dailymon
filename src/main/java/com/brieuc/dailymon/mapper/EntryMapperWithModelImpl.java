package com.brieuc.dailymon.mapper;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.brieuc.dailymon.entity.entry.EntryType;
import com.brieuc.dailymon.entity.model.Model;
import com.brieuc.dailymon.entity.model.ModelFood;
import com.brieuc.dailymon.entity.model.ModelFree;
import com.brieuc.dailymon.entity.model.ModelSport;
import com.brieuc.dailymon.service.ModelService;

@Component  
public class EntryMapperWithModelImpl implements EntryMapper {

    private final ModelMapper modelMapper;
    private final ModelService modelService;

    @Autowired
    public EntryMapperWithModelImpl(ModelMapper modelMapper, ModelService modelService) {
        this.modelMapper = modelMapper;
        this.modelService = modelService;
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
                // Champs communs
                .id(entry.getId())
                .version(entry.getVersion())
                .title(entry.getTitle())
                .description(entry.getDescription() == null ? 
                    entry.getModel().getTitle() : entry.getDescription())
                .date(entry.getDate())
                .modelId(entry.getModel().getId())
                .model((ModelFoodDto) modelMapper.toDto(entry.getModel()))
                .type(EntryType.FOOD)
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
                .version(entry.getVersion())
                .title(entry.getTitle())
                .description(entry.getDescription())
                .date(entry.getDate())
                .modelId(entry.getModel().getId())
                .type(EntryType.SPORT)
                .build();
    }

    private EntryFreeDto toDto(EntryFree entry) {
        return EntryFreeDto.builder()
                .id(entry.getId())
                .version(entry.getVersion())
                .title(entry.getTitle())
                .description(entry.getDescription())
                .date(entry.getDate())
                .modelId(entry.getModel().getId())
                // Pas de champs spécifiques pour EntryFree
                .model((ModelFreeDto) modelMapper.toDto(entry.getModel())) // ✅ Model inclus !
                // Champs communs
                .type(EntryType.FREE)
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
                .version(entry.getVersion())
                .title(entry.getTitle())
                .description(entry.getDescription())
                .date(entry.getDate())
                .modelId(entry.getModel().getId())
                .type(EntryType.FREE_FOOD)
                .build();
    }

    @Override
    public Entry toEntity(EntryDto entryDto) {
        return switch (entryDto) {
            case EntryFoodDto foodDto -> createFoodEntity(foodDto);
            case EntrySportDto sportDto -> createSportEntity(sportDto);
            case EntryFreeFoodDto freeFoodDto -> createFreeFoodEntity(freeFoodDto);
            case EntryFreeDto freeDto -> createFreeEntity(freeDto);
            default -> null;
        };
    }

    private EntryFood createFoodEntity(EntryFoodDto dto) {
        Model model = modelService.getModelById(dto.getModelId())
            .orElseThrow(() -> new RuntimeException("Model with id " + dto.getModelId() + " not found"));
        
        // Vérification du type pour la sécurité
        if (!(model instanceof ModelFood)) {
            throw new IllegalArgumentException("Expected ModelFood but got " + model.getClass().getSimpleName());
        }
        
        return EntryFood.builder()
                // Champs spécifiques à EntryFood
                .quantity(dto.getQuantity())
                // Champs communs de Entry
                .id(dto.getId())
                .version(dto.getVersion())
                .date(dto.getDate())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .model((ModelFood) model) // ✅ Cast sécurisé après vérification
                .build();
    }

    private EntrySport createSportEntity(EntrySportDto dto) {
        Model model = modelService.getModelById(dto.getModelId())
            .orElseThrow(() -> new RuntimeException("Model with id " + dto.getModelId() + " not found"));
        
        // Vérification du type pour la sécurité
        if (!(model instanceof ModelSport)) {
            throw new IllegalArgumentException("Expected ModelSport but got " + model.getClass().getSimpleName());
        }
        
        return EntrySport.builder()
                // Champs spécifiques à EntrySport
                .kcal(dto.getKcal())
                .duration(dto.getDuration())
                .aerobic(dto.getAerobic())
                .anaerobic(dto.getAnaerobic())
                .benefit(dto.getBenefit())
                // Champs communs de Entry
                .id(dto.getId())
                .version(dto.getVersion())
                .date(dto.getDate())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .model((ModelSport) model) // ✅ Cast sécurisé après vérification
                .build();
    }

    private EntryFree createFreeEntity(EntryFreeDto dto) {
        Model model = modelService.getModelById(dto.getModelId())
            .orElseThrow(() -> new RuntimeException("Model with id " + dto.getModelId() + " not found"));
        
        // Vérification du type pour la sécurité
        if (!(model instanceof ModelFree)) {
            throw new IllegalArgumentException("Expected ModelFree but got " + model.getClass().getSimpleName());
        }
        
        return EntryFree.builder()
                // Pas de champs spécifiques pour EntryFree
                // Champs communs de Entry
                .id(dto.getId())
                .version(dto.getVersion())
                .date(dto.getDate())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .model((ModelFree) model) // ✅ Cast sécurisé après vérification
                .build();
    }

    private EntryFreeFood createFreeFoodEntity(EntryFreeFoodDto dto) {
        Model model = modelService.getModelById(dto.getModelId())
            .orElseThrow(() -> new RuntimeException("Model with id " + dto.getModelId() + " not found"));
        
        // Vérification du type pour la sécurité
        if (!(model instanceof ModelFree)) {
            throw new IllegalArgumentException("Expected ModelFree but got " + model.getClass().getSimpleName());
        }
        
        return EntryFreeFood.builder()
                // Champs spécifiques à EntryFreeFood
                .foodType(dto.getFoodType())
                .kcal(dto.getKcal())
                // Champs communs de Entry
                .id(dto.getId())
                .version(dto.getVersion())
                .date(dto.getDate())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .model((ModelFree) model) // ✅ Cast sécurisé après vérification
                .build();
    }
}