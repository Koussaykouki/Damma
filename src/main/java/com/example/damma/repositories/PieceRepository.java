package com.example.damma.repositories;

import com.example.damma.entities.Piece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PieceRepository extends JpaRepository<Piece, Long> {
    // Additional custom methods can be added here if needed
}