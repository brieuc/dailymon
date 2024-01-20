package com.brieuc.dailymon;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class ModelDto {

    UUID id;
    String label;
    String description;
    Integer kcal;
    String image;
}
