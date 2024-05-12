package com.example.damma.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "move")
@Data
@NoArgsConstructor
public class Move {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Embedded
    private Position fromPosition;

    @Embedded
    private Position toPosition;

    @OneToMany(mappedBy = "move", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Piece> capturedPieces;

    public Move(Game game, Position fromPosition, Position toPosition, List<Piece> capturedPieces) {
        this.game = game;
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
        this.capturedPieces = capturedPieces;
    }
}

