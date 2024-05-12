package com.example.damma.services;

import com.example.damma.entities.*;
import com.example.damma.repositories.PieceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PieceService {

    @Autowired
    private PieceRepository pieceRepository;

    @Transactional
    public List<Piece> initializePieces(Board board) {
        List<Piece> pieces = new ArrayList<>();
        // Initialize pieces for two players: Typically rows 0-2 for one player and rows 5-7 for the other.
        for (int i = 0; i < 8; i++) {
            for (int j = (i % 2 == 0 ? 1 : 0); j < 8; j += 2) { // Checkers are placed on alternating squares
                if (i < 3) {
                    pieces.add(new Piece(board, null, new Position(i, j), enums.PieceType.REGULAR)); // Set player and color appropriately
                } else if (i > 4) {
                    pieces.add(new Piece(board, null, new Position(i, j), enums.PieceType.REGULAR)); // Set player and color appropriately
                }
            }
        }
        return pieceRepository.saveAll(pieces);
    }

    public boolean isMoveValid(Board board, Move move) {
        // Placeholder for move validation logic
        return true; // Replace with actual validation logic
    }

    @Transactional
    public void movePiece(Board board, Move move) {
        // Logic to move a piece from move.fromPosition to move.toPosition
        Optional<Piece> pieceOptional = pieceRepository.findAll().stream()
                .filter(p -> p.getPosition().equals(move.getFromPosition()))
                .findFirst();

        pieceOptional.ifPresent(piece -> {
            piece.setPosition(move.getToPosition());
            pieceRepository.save(piece);
        });
    }

    @Transactional
    public void removePiece(Board board, Piece piece) {
        pieceRepository.delete(piece);
    }

    public boolean areNoMovesAvailable(Board board) {
        // Logic to check if no moves are available
        return false; // Replace with actual logic
    }

    public boolean isOnlyOneColorLeft(Board board) {
        // Logic to check if only one color of pieces is left on the board
        return false; // Replace with actual logic
    }

    public Optional<enums.Color> determineWinner(Board board) {
        // Logic to determine winner
        return Optional.empty(); // Replace with actual logic
    }
}
