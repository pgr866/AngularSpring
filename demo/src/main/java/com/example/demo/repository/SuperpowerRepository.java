package com.example.demo.repository;

import com.example.demo.entity.Superpower;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SuperpowerRepository extends JpaRepository<Superpower, Long> {
    Optional<Superpower> findByName(String name);
}
