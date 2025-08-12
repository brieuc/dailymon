package com.brieuc.dailymon.mapper;

import org.springframework.stereotype.Component;

import com.brieuc.dailymon.dto.ModelDto;
import com.brieuc.dailymon.dto.ModelFoodDto;
import com.brieuc.dailymon.dto.ModelFreeDto;
import com.brieuc.dailymon.dto.ModelSportDto;
import com.brieuc.dailymon.entity.model.Model;
import com.brieuc.dailymon.entity.model.ModelFood;
import com.brieuc.dailymon.entity.model.ModelFree;
import com.brieuc.dailymon.entity.model.ModelSport;

@Component
public class ModelMapperImpl implements ModelMapper {

    @Override
    public ModelDto toDto(Model model) {
        return switch (model) {
            case ModelFood food -> createFoodDto(food);
            case ModelFree free -> createFreeDto(free);
            case ModelSport sport -> createSportDto(sport);
            default -> null;
        };
    }

    private ModelFoodDto createFoodDto(ModelFood model) {
        return ModelFoodDto.builder()
                // Champs spécifiques à ModelFood
                .kcal(model.getKcal())
                .foodType(model.getFoodType())
                // Champs communs
                .id(model.getId())
                .title(model.getTitle())
                .description(model.getDescription())
                .image(model.getImage())
                .build();
    }

    private ModelFreeDto createFreeDto(ModelFree model) {
        return ModelFreeDto.builder()
                // Pas de champs spécifiques pour ModelFree
                // Champs communs
                .id(model.getId())
                .title(model.getTitle())
                .description(model.getDescription())
                .build();
    }

    private ModelSportDto createSportDto(ModelSport model) {
        return ModelSportDto.builder()
                // Champs spécifiques à ModelSport
                .sport(model.getSport())
                // Champs communs
                .id(model.getId())
                .title(model.getTitle())
                .description(model.getDescription())
                .image(model.getImage())
                .build();
    }
}