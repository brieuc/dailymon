package com.brieuc.dailymon.mapper;

import org.springframework.stereotype.Component;

import com.brieuc.dailymon.dto.ModelDto;
import com.brieuc.dailymon.entity.model.Model;

@Component
public interface ModelMapper {
      ModelDto toDto(Model model);
}
