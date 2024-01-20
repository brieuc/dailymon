package com.brieuc.dailymon.entity;

import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Entry")
public class Entry {
    
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    UUID id;

    @Column(name = "label")
    String label;

    @Column(name = "description")
    String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "modelid", referencedColumnName = "id")
    Model model;
}
