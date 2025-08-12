package com.brieuc.dailymon.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.brieuc.dailymon.CreateEntry;
import com.brieuc.dailymon.UpdateEntry;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EntryDto {

    UUID id;
    @NotBlank(groups = {CreateEntry.class, UpdateEntry.class}, message = "Title couldn't be null")
    String title;
    //@NotBlank(groups = {CreateEntry.class, UpdateEntry.class}, message = "Description couldn't be null")
    String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(groups = {CreateEntry.class}, message = "Date couldn't be null")
    LocalDate date;
    @NotNull(groups = {CreateEntry.class}, message = "Model couldn't be null")
    UUID modelId;
}
