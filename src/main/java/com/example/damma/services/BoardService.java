package com.example.damma.services;

import com.example.damma.entities.Board;
import com.example.damma.entities.Move;
import com.example.damma.entities.enums;
import com.example.damma.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private PieceService pieceService;

    @Transactional
    public Board initializeNewBoard() {
        Board board = new Board();
        board.setPieces(pieceService.initializePieces(board));
        return boardRepository.save(board);
    }

    @Transactional
    public boolean processMove(Board board, Move move) {
        // Validate the move
        if (!pieceService.isMoveValid(board, move)) {
            return false;
        }

        // Execute the move
        pieceService.movePiece(board, move);

        // Check for captures
        move.getCapturedPieces().forEach(piece -> pieceService.removePiece(board, piece));

        boardRepository.save(board);
        return true;
    }

    public boolean checkGameOver(Board board) {
        // Logic to determine if the game is over based on the pieces left on the board
        return pieceService.areNoMovesAvailable(board) || pieceService.isOnlyOneColorLeft(board);
    }

    public Optional<enums.Color> getWinner(Board board) {
        // Logic to determine the winner based on the pieces left on the board
        return pieceService.determineWinner(board);
    }
}

