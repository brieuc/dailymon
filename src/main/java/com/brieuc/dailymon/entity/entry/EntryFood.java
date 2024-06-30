package com.brieuc.dailymon.entity.entry;

import com.brieuc.dailymon.entity.FoodType;
import com.brieuc.dailymon.entity.model.ModelFood;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "entry_food")
public class EntryFood extends Entry {

    @Column(name = "quantity")
    Double quantity;

    @Column(name = "food_type")
    FoodType foodType;

    @ManyToOne
    @JoinColumn(name = "modelid", referencedColumnName = "id")
    ModelFood model;
}
