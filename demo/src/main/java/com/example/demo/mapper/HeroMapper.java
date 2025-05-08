package com.example.demo.mapper;

import com.example.demo.dto.HeroDTO;
import com.example.demo.entity.Hero;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class HeroMapper {

    public HeroDTO toDto(Hero hero) {
        return new HeroDTO(hero.getId(), hero.getName(), hero.getSuperpowers(), hero.getImage());
    }

    public Hero toEntity(HeroDTO heroDTO) {
        Hero hero = new Hero(heroDTO.getName());
        hero.setId(heroDTO.getId());
        hero.setImage(heroDTO.getImage());
        hero.setSuperpowers(new ArrayList<>());
        if (heroDTO.getSuperpowers() != null) {
            heroDTO.getSuperpowers().forEach(sp -> {
                sp.setHero(hero);
                hero.getSuperpowers().add(sp);
            });
        }
        return hero;
    }
}
