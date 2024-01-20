package com.brieuc.dailymon.dto;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.cglib.core.Local;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntryDto {

    String description;
    Double quantity;
    LocalDate date;
    UUID modelId;
}
