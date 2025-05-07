package com.example.demo.controller;

import com.example.demo.dto.HeroDTO;
import com.example.demo.entity.Hero;
import com.example.demo.mapper.HeroMapper;
import com.example.demo.repository.HeroRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final HeroRepository heroRepository;
    private final HeroMapper heroMapper;

    public HeroController(HeroRepository heroRepository, HeroMapper heroMapper) {
        this.heroRepository = heroRepository;
        this.heroMapper = heroMapper;
    }

    @GetMapping
    public List<HeroDTO> getAllHeroes() {
        return heroRepository.findAll().stream()
                .map(heroMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public Hero getHeroById(@PathVariable Long id) {
        return heroRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public HeroDTO updateHero(@PathVariable Long id, @RequestBody HeroDTO heroDTO) {
        Hero existingHero = heroRepository.findById(id).orElseThrow(() -> new RuntimeException("Hero not found"));
        existingHero.setName(heroDTO.getName());
        existingHero.setSuperpowers(heroDTO.getSuperpowers());
        return heroMapper.toDto(heroRepository.save(existingHero));
    }
}
