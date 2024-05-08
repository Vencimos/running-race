package com.project.runningrace.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotNull
    private String name;

    @Column(nullable = false)
    @NotNull
    private double distance;

    public Race(String name, double distance) {
        this.name = name;
        this.distance = distance;
    }
}
