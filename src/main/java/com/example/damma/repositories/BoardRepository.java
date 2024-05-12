package com.example.damma.repositories;

import com.example.damma.entities.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // Additional custom methods can be added here if needed
}
