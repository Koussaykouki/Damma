package com.example.damma.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "piece")
@Data
@NoArgsConstructor
public class Piece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Embedded
    private Position position;

    @Enumerated(EnumType.STRING)
    private enums.PieceType type;

    public Piece(Board board, Player player, Position position, enums.PieceType type) {
        this.board = board;
        this.player = player;
        this.position = position;
        this.type = type;
    }
}

