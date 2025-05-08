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
                heroRepository.save(new Hero("Hulk", "http://i.annihil.us/u/prod/marvel/i/mg/5/a0/538615ca33ab0.jpg"));
                heroRepository.save(new Hero("Thor", "http://i.annihil.us/u/prod/marvel/i/mg/d/d0/5269657a74350.jpg"));
                heroRepository.save(new Hero("Spider-Man (Peter Parker)", "http://i.annihil.us/u/prod/marvel/i/mg/3/50/526548a343e4b.jpg"));
                heroRepository.save(new Hero("Iron Man", "http://i.annihil.us/u/prod/marvel/i/mg/9/c0/527bb7b37ff55.jpg"));
                heroRepository.save(new Hero("Black Widow", "http://i.annihil.us/u/prod/marvel/i/mg/f/30/50fecad1f395b.jpg"));
                heroRepository.save(new Hero("Wolverine", "http://i.annihil.us/u/prod/marvel/i/mg/2/60/537bcaef0f6cf.jpg"));
                heroRepository.save(new Hero("Captain America", "http://i.annihil.us/u/prod/marvel/i/mg/3/50/537ba56d31087.jpg"));
                heroRepository.save(new Hero("Deadpool", "http://i.annihil.us/u/prod/marvel/i/mg/9/90/5261a86cacb99.jpg"));
                heroRepository.save(new Hero("Doctor Strange", "http://i.annihil.us/u/prod/marvel/i/mg/5/f0/5261a85a501fe.jpg"));
            }
        };
    }
}