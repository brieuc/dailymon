package com.brieuc.dailymon.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class EntryMapperConfig {
      @Bean
      @Primary
      public EntryMapper entryMapperWithModel(ModelMapper modelMapper) {
            return new EntryMapperWithModelImpl(modelMapper);
      }
      
      // Si tu veux aussi garder la version sans model
      @Bean("entryMapperSimple")
      public EntryMapper entryMapperSimple() {
            return new EntryMapperImpl();
    }
}
