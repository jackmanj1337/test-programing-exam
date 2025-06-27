package chess.MoveCalculator;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;

public class QueenMovesCalc extends MoveCalculator{
    @Override
    public Collection<ChessMove> getPossibleMoves(ChessBoard board, ChessPosition start){
        ArrayList<ChessMove> moves = new ArrayList<>();
        moves.addAll(validateStraights(board, start));
        moves.addAll(validateDiagonals(board, start));
        
        
        return moves;
    }
}
