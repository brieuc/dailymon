package com.brieuc.dailymon.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Benefit {

    @JsonProperty("sprint")
    SPRINT("sprint"),
    @JsonProperty("tempo")
    TEMPO("tempo"),
    @JsonProperty("base")
    BASE("base"),
    @JsonProperty("anaerobie")
    ANAEROBIE("anaerobie"),
    @JsonProperty("vo2max")
    VO2MAX("vo2max");

    private final String benefit;

    Benefit(String benefit) {
        this.benefit = benefit;
    }

    public String toString() {
        return this.benefit;
    }    
}
