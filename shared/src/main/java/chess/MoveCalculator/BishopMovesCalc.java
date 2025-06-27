package chess.MoveCalculator;

import chess.*;
import java.util.*;

public class BishopMovesCalc extends MoveCalculator{
    @Override
    public Collection<ChessMove> getPossibleMoves(ChessBoard board, ChessPosition start){
        return validateDiagonals(board, start);
    }
}
