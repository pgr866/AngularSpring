package com.example.demo.dto;

import com.example.demo.entity.Superpower;
import java.util.List;

public class HeroDTO {
    private Long id;
    private String name;
    private String image;
    private List<Superpower> superpowers;

    public HeroDTO(Long id, String name, List<Superpower> superpowers, String image) {
        this.id = id;
        this.name = name;
        this.superpowers = superpowers;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }    

    public List<Superpower> getSuperpowers() {
        return superpowers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setSuperpowers(List<Superpower> superpowers) {
        this.superpowers = superpowers;
    }
}
