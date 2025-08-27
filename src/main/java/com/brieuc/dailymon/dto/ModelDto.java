package com.brieuc.dailymon.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Getter
@Setter

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ModelFoodDto.class, name = "FOOD"),
    @JsonSubTypes.Type(value = ModelFreeDto.class, name = "FREE"),
    @JsonSubTypes.Type(value = ModelSportDto.class, name = "SPORT")
})

@NoArgsConstructor
public class ModelDto {

    UUID id;
    String title;
    String description;
    String image;
}
