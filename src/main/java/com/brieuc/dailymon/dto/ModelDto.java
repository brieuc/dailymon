package com.brieuc.dailymon.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter

public class ModelDto {

    UUID id;
    String title;
    String description;
    String image;
}
