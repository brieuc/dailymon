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

    @Override
    public Model toEntity(ModelDto modelDto) {
        return switch (modelDto) {
            case ModelFoodDto foodDto -> createFoodEntity(foodDto);
            case ModelFreeDto freeDto -> createFreeEntity(freeDto);
            case ModelSportDto sportDto -> createSportEntity(sportDto);
            default -> null;
        };
    }

    private ModelFood createFoodEntity(ModelFoodDto dto) {
        return ModelFood.builder()
                // Champs spécifiques à ModelFood
                .kcal(dto.getKcal())
                .foodType(dto.getFoodType())
                .image(dto.getImage())
                // Champs communs de Model
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
    }

    private ModelFree createFreeEntity(ModelFreeDto dto) {
        return ModelFree.builder()
                // Pas de champs spécifiques pour ModelFree
                // Champs communs de Model
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
    }

    private ModelSport createSportEntity(ModelSportDto dto) {
        return ModelSport.builder()
                // Champs spécifiques à ModelSport
                .sport(dto.getSport())
                .image(dto.getImage())
                // Champs communs de Model
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
    }
}