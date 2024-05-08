package com.project.runningrace.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Runner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotNull
    private String name;

    @Column(nullable = false)
    @NotNull
    private int age;

    @Column(nullable = false)
    @NotNull
    private String gender;

    public Runner(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}

