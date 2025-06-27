package chess.MoveCalculator;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.Collection;

public class RookMovesCalc extends MoveCalculator{
    @Override
    public Collection<ChessMove> getPossibleMoves(ChessBoard board, ChessPosition start){
        return validateStraights(board, start);
    }
}
