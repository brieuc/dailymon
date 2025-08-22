package com.brieuc.dailymon.entity.entry;

import com.brieuc.dailymon.entity.FoodType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "entry_free_food")
// No way to inherit directly for EntryFree, the deep inheritance doesn't
// seem to work when saving the EntryFreeFood.
public class EntryFreeFood extends Entry {
      
      @Column(name = "food_type")
      FoodType foodType;

      @Column(name = "kcal")
      int kcal;
}
