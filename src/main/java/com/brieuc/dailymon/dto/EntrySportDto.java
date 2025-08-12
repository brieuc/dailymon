package com.brieuc.dailymon.dto;

import com.brieuc.dailymon.entity.Benefit;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class EntrySportDto extends EntryDto {
    
    @NotNull(message = "Calories couldn't be null")
    @Digits(integer = 5, fraction = 0, message = "Incorrect calories input")
    Integer kcal;
    @NotNull(message = "Aerobic couldn't be null")
    @Digits(integer = 1, fraction = 1, message = "Incorrect aerobic input")
    Double aerobic;
    @NotNull(message = "Anaerobic couldn't be null")
    @Digits(integer = 1, fraction = 1, message = "Incorrect anaerobic input")
    Double anaerobic;
    @NotNull(message = "Duration couldn't be null")
    @Digits(integer = 3, fraction = 0, message = "Incorrect duration input")
    Integer duration;
    @NotNull(message = "Benefit couldn't be null")
    Benefit benefit;
    ModelSportDto model;
    final String type="SPORT";
}
