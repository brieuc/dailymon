package com.brieuc.dailymon.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class ModelDto {

    UUID id;
    String title;
    String description;
    String image;
}
