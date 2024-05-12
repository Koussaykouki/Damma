package com.example.damma.services;


import com.example.damma.entities.Board;
import com.example.damma.entities.Game;
import com.example.damma.entities.Move;
import com.example.damma.entities.enums;
import com.example.damma.repositories.GameRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private BoardService boardService;

    @Transactional
    public Game createNewGame() {
        Board board = boardService.initializeNewBoard();
        Game newGame = new Game(board, null); // Assuming player management is handled separately
        newGame.setGameStatus(enums.GameStatus.ONGOING);
        return gameRepository.save(newGame);
    }

    @Transactional
    public boolean makeMove(Long gameId, Move move) {
        Optional<Game> gameOptional = gameRepository.findById(gameId);
        if (gameOptional.isPresent()) {
            Game game = gameOptional.get();
            if (boardService.processMove(game.getBoard(), move)) {
                if (boardService.checkGameOver(game.getBoard())) {
                    game.setGameStatus(boardService.getWinner(game.getBoard()).map(color ->
                                    (color == enums.Color.BLACK ? enums.GameStatus.BLACK_WINS : enums.GameStatus.RED_WINS))
                            .orElse(enums.GameStatus.DRAW));
                }
                gameRepository.save(game);
                return true;
            }
        }
        return false;
    }

    public Optional<Game> getGameById(Long gameId) {
        return gameRepository.findById(gameId);
    }
}