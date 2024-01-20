package com.brieuc.dailymon.entity;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.cglib.core.Local;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "Entry")
public class Entry {
    
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    UUID id;

    @Column(name = "description")
    String description;

    @Column(name = "quantity")
    Double quantity;

    @Column(name = "date")
    LocalDate date;

    @OneToOne
    @JoinColumn(name = "modelid", referencedColumnName = "id")
    Model model;
}
