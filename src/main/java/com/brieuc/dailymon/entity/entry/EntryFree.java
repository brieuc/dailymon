package com.brieuc.dailymon.entity.entry;

import com.brieuc.dailymon.entity.model.ModelFree;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "entry_free")
public class EntryFree extends Entry {    

    @ManyToOne
    @JoinColumn(name = "modelid", referencedColumnName = "id")
    ModelFree model;
}
