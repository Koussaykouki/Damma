package com.example.damma.repositories;

import com.example.damma.entities.Move;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoveRepository extends JpaRepository<Move, Long> {
    // You can add custom methods here if needed
}
