package com.example.damma.services;

import com.example.damma.entities.Game;
import com.example.damma.entities.Move;
import com.example.damma.entities.Piece;
import com.example.damma.entities.Position;
import com.example.damma.repositories.MoveRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MoveService {

    @Autowired
    private MoveRepository moveRepository;

    @Transactional
    public Move saveMove(Game game, Position fromPosition, Position toPosition, List<Piece> capturedPieces) {
        Move move = new Move(game, fromPosition, toPosition, capturedPieces);
        return moveRepository.save(move);
    }

    public Optional<Move> getMoveById(Long moveId) {
        return moveRepository.findById(moveId);
    }

    public List<Move> getAllMovesByGame(Game game) {
        // This would typically require a custom query to filter moves by game
        // Assuming such a method is implemented in MoveRepository like:
        // List<Move> findByGame(Game game);
        return moveRepository.findAll().stream()
                .filter(move -> move.getGame().equals(game))
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean undoLastMove(Game game) {
        List<Move> moves = getAllMovesByGame(game);
        if (!moves.isEmpty()) {
            Move lastMove = moves.get(moves.size() - 1);
            // Logic to undo the last move: reverse the positions, restore captured pieces, etc.
            // This part would interact with PieceService and BoardService to revert the board state
            moveRepository.delete(lastMove);
            return true;
        }
        return false;
    }
}
