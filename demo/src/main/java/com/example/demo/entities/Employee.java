package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int age;

    private String role;

    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    private int experienceYears;

}
