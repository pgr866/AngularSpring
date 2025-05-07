package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.entity.Hero;
import com.example.demo.repository.HeroRepository;

@Configuration
public class DataInitializer {

    @Bean
    @Transactional
    public CommandLineRunner initDatabase(HeroRepository heroRepository) {
        return args -> {
            if (heroRepository.count() == 0) {
                heroRepository.save(new Hero("Dr. Nice"));
                heroRepository.save(new Hero("Bombasto"));
                heroRepository.save(new Hero("Celeritas"));
                heroRepository.save(new Hero("Magneta"));
                heroRepository.save(new Hero("RubberMan"));
                heroRepository.save(new Hero("Dynama"));
                heroRepository.save(new Hero("Dr. IQ"));
                heroRepository.save(new Hero("Magma"));
                heroRepository.save(new Hero("Tornado"));
            }
        };
    }
}