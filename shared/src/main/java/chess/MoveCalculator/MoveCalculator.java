package chess.MoveCalculator;

import chess.*;
import java.util.*;

public abstract class MoveCalculator {

    public abstract Collection<ChessMove> getPossibleMoves(ChessBoard board, ChessPosition start);

    protected static class MoveValidationResults{

        private boolean onBoard;
        private boolean empty;
        private boolean capture;
        private boolean promotion;

        public boolean isOnBoard() {
            return onBoard;
        }

        public void setOnBoard(boolean onBoard) {
            this.onBoard = onBoard;
        }

        public boolean isCapture() {
            return capture;
        }

        public void setCapture(boolean capture) {
            this.capture = capture;
        }

        public boolean isEmpty() {
            return empty;
        }

        public void setEmpty(boolean empty) {
            this.empty = empty;
        }

        public boolean isPromotion() {
            return promotion;
        }

        public void setPromotion(boolean promotion) {
            this.promotion = promotion;
        }

        public MoveValidationResults(boolean onBoard, boolean empty, boolean capture, boolean promotion){
            this.onBoard = onBoard;
            this.empty = empty;
            this.capture = capture;
            this.promotion = promotion;
        }
        public MoveValidationResults(){};

    }

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
/*
if ((end.getRow() == 1 || end.getRow() == 8) && board.getPiece(start).getPieceType() == ChessPiece.PieceType.PAWN){
            results.setPromotion(true);
        } else {
            results.setPromotion(false);
        }
*/


        return results;
    }

    protected void addMove(ArrayList<ChessMove> list, ChessPosition start, ChessPosition end, MoveValidationResults results){
        ChessPiece.PieceType[] availablePromotions =  {
            ChessPiece.PieceType.QUEEN,
            ChessPiece.PieceType.KNIGHT,
            ChessPiece.PieceType.BISHOP,
            ChessPiece.PieceType.ROOK
        };


        if (results.isPromotion() == false){
            list.add(new ChessMove(start, end, null));
            return;
        } else {
            for (ChessPiece.PieceType promotion : availablePromotions){
                list.add(new ChessMove(start, end, promotion));
            }
        }
    }

    protected Collection<ChessMove> validateStraights(ChessBoard board, ChessPosition start){
        ArrayList<ChessMove> possibleStraights = new ArrayList<>();

        int[][] directions = {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
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
                if (results.isCapture() == true){
                    addMove(possibleStraights, start, targetPosition, results);
                    break;
                }
                if (results.isEmpty() == true){
                    addMove(possibleStraights, start, targetPosition, results);
                }
            }
        }
        return possibleStraights;
    }

    protected Collection<ChessMove> validateDiagonals(ChessBoard board, ChessPosition start){
        ArrayList<ChessMove> possibleStraights = new ArrayList<>();

        int[][] directions = {
                {1, 1},  // upper right
                {1, -1},  // upper right
                {-1, -1}, // lower left
                {-1, 1}  // lower left
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
                if (results.isCapture() == true){
                    addMove(possibleStraights, start, targetPosition, results);
                    break;
                }
                if (results.isEmpty() == true){
                    addMove(possibleStraights, start, targetPosition, results);
                }
            }
        }
        return possibleStraights;
    }






}
