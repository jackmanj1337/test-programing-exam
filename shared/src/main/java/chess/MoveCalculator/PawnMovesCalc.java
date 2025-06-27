package chess.MoveCalculator;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.Collection;
import java.util.ArrayList;

public class PawnMovesCalc extends MoveCalculator{
    @Override
    MoveValidationResults MoveValidator(ChessBoard board, ChessPosition start, ChessPosition end){
        MoveValidationResults results = new MoveValidationResults();
        if (end.getRow() > 8 || end.getRow() < 1 || end.getColumn() > 8 || end.getColumn() < 1){
            results.setOnBoard(false);
            return results;
        } else {
            results.setOnBoard(true);
        }

        if (board.getPiece(end) == null){
            results.setEmpty(true);
        } else {
            results.setEmpty(false);
            if (board.getPiece(end).getTeamColor() == board.getPiece(start).getTeamColor()){
                results.setCapture(false);
            } else {
                results.setCapture(true);
            }
        }

if ((end.getRow() == 1 || end.getRow() == 8)){
            results.setPromotion(true);
        } else {
            results.setPromotion(false);
        }



        return results;
    }

    @Override
    public Collection<ChessMove> getPossibleMoves(ChessBoard board, ChessPosition start){
        ArrayList<ChessMove> moves = new ArrayList<>();
        int directionModifier = board.getPiece(start).getTeamColor() == ChessGame.TeamColor.WHITE ? 1 : -1;

        ChessPosition target = new ChessPosition(start.getRow()+(directionModifier * 1), start.getColumn());
        MoveValidationResults results = MoveValidator(board, start, target);
        if (results.isEmpty() == true && results.isCapture() == false) {
            addMove(moves, start, target, results);
            if (start.getRow() == 2 || start.getRow() == 7) {
                target = new ChessPosition(start.getRow() + (directionModifier * 2), start.getColumn());
                results = MoveValidator(board, start, target);
                if (results.isEmpty() == true && results.isCapture() == false) {
                    addMove(moves, start, target, results);
                }
            }

        }

        for (int direction = -1; direction < 2; direction += 2){
            target = new ChessPosition(start.getRow()+(directionModifier * 1), start.getColumn() + direction);
            results = MoveValidator(board, start, target);
            if (results.isOnBoard() == true && results.isCapture() == true){
                addMove(moves, start, target, results);
            }

        }
        return moves;
    }
}
