package com.example.damma.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "game")
@Data
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "board_id", referencedColumnName = "id")
    private Board board;

    @Enumerated(EnumType.STRING)
    private enums.GameStatus gameStatus;

    @ManyToOne
    @JoinColumn(name = "current_player_id")
    private Player currentPlayer;

    public Game(Board board, Player currentPlayer) {
        this.board = board;
        this.currentPlayer = currentPlayer;
        this.gameStatus = enums.GameStatus.ONGOING;
    }


}
