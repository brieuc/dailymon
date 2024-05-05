package com.brieuc.dailymon.entity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor

@Entity
@Table(name = "model_free")
public class ModelFree extends Model {

}
