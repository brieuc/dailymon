package com.brieuc.dailymon.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.brieuc.dailymon.service.ModelService;

@Configuration
public class EntryMapperConfig {
      @Bean
      @Primary
      public EntryMapper entryMapperWithModel(ModelMapper modelMapper, ModelService modelService) {
            return new EntryMapperWithModelImpl(modelMapper, modelService);
      }
      
      // Si tu veux aussi garder la version sans model
      @Bean("entryMapperSimple")
      public EntryMapper entryMapperSimple(ModelService modelService) {
            return new EntryMapperImpl(modelService);
    }
}
