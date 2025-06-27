package chess.MoveCalculator;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;

public class KnightMovesCalc extends MoveCalculator{
    @Override
    public Collection<ChessMove> getPossibleMoves(ChessBoard board, ChessPosition start){
        ArrayList<ChessMove> moves = new ArrayList<>();

        int[][] directions = {
                {2, -1},
                {2, 1},
                {-2, -1},
                {-2, 1},
                {1, -2},
                {1, 2},
                {-1, -2},
                {-1, 2}
        };

        for (int[] direction : directions){
            int targetRow = start.getRow();
            int targetCol = start.getColumn();
            while (true){
                targetRow += direction[0];
                targetCol += direction[1];
                ChessPosition targetPosition = new ChessPosition(targetRow, targetCol);
                MoveValidationResults results = MoveValidator(board, start , targetPosition);
                if (results.isOnBoard() == false || (results.isEmpty() == false && results.isCapture() == false)){
                    break;
                }
                if (results.isCapture() == true || results.isEmpty()){
                    addMove(moves, start, targetPosition, results);
                    break;
                }
            }
        }


        return moves;
    }
}
