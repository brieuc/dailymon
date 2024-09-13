package com.brieuc.dailymon.entity.entry;

import com.brieuc.dailymon.entity.FoodType;
import com.brieuc.dailymon.entity.model.ModelFree;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "entry_free_food")
// No way to inherit directly for EntryFree, the deep inheritance doesn't
// seem to work when saving the EntryFreeFood.
public class EntryFreeFood extends Entry {
      
      @Column(name = "food_type")
      FoodType foodType;

      @Column(name = "kcal")
      int kcal;

      @ManyToOne
      @JoinColumn(name = "modelid", referencedColumnName = "id")
      ModelFree model;
}
