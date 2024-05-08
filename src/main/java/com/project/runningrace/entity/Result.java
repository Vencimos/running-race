package com.project.runningrace.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "runner_id", nullable = false)
    private Runner runner;

    @ManyToOne
    @JoinColumn(name = "race_id", nullable = false)
    private Race race;

    @Column(nullable = false)
    @NotNull
    @Min(0)
    private int timeInMinutes;

    public Result(Runner runner, Race race, int timeInMinutes) {
        this.runner = runner;
        this.race = race;
        this.timeInMinutes = timeInMinutes;
    }
}

