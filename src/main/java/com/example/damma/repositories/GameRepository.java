package com.example.damma.repositories;

import com.example.damma.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    // Additional custom methods can be added here if needed
}