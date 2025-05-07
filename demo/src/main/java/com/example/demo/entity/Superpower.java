package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Superpower {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Hero hero;

    public Superpower() {}

    public Superpower(String name, Hero hero) {
        this.name = name;
        this.hero = hero;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Hero getHero() {
        return hero;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }
}
