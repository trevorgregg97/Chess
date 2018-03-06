package Chess.Game;
import Chess.Pieces.*;

import java.util.HashSet;
import java.util.Set;

public class Board {
	private Square delStart, delEnd;
	private Piece delPiece;
	public Piece[][] board;
	public enum Color {
		WHITE, BLACK;
	}
	
	public Board() {
		board = new Piece[8][8];
		fillBoard(board);
	}
	
	private Board(Piece[][] board, Piece delPiece, Square delStart, Square delEnd) {
		this.board = board;
		this.delPiece = delPiece;
		this.delEnd = delEnd;
		this.delStart = delStart;
	}
	
	private void fillBoard(Piece[][] board) {
		for(int i = 0; i < 8; i++) {
			board[1][i] = new Pawn(Color.WHITE);
			board[6][i] = new Pawn(Color.BLACK);
		}
		board[0][4] = new King(Color.WHITE);
		board[7][4] = new King(Color.BLACK);
	}
	
	public void undoMove() {
	    int delRowStart = delStart.row;
	    int delColStart = delStart.col;
	    int delRowEnd = delEnd.row;
	    int delColEnd = delEnd.col;
		Piece temp = board[delRowEnd][delColEnd];
		if(delPiece != null) {
			board[delRowEnd][delColEnd] = delPiece;
		}
		board[delRowStart][delColStart] = temp;
	}
	
	public boolean isLegal(Move move) {
		//Check if someone is in check first
		//check if its their piece or move
		Piece pieceToMove = board[move.start.row][move.start.col];
		move.piece = pieceToMove;
		if(pieceToMove == null) {
			return false;
		}
		//Check if would put into check someone
		return true;
	}
	
	public boolean applyMove(Move move) {
		if(!isLegal(move)) {
			return false;
		}
		//Save previous move to undo
        int rowStart = move.start.row;
		int colStart = move.start.col;
		int rowEnd = move.end.row;
		int colEnd = move.end.col;

		delPiece = board[rowEnd][colEnd];
		delStart = new Square(rowStart,colStart);
		delEnd = new Square(rowEnd,colEnd);
		
		board[rowStart][colStart] = null;
		board[rowEnd][colEnd] = move.piece;
		move.piece.hasMoved = true;
		return true;
	}
    //TODO USE THIS TO IMPLEMENT CHECK FOR CHECKS AND CHECK FOR CHECK AS NEEDED IN OTHER METHODS
	public Set<Square> generateThreats(){
	    Set<Square> threats = new HashSet<>();
	    for(int i = 0; i < 8; i++){
	        for(int j = 0; j < 8; j++){
	            if(board[i][j] != null){
                    threats.addAll(board[i][j].generateThreatenedSquares(new Square(i,j),board));
                }
            }
        }
        return threats;
    }
	public Board copy() {
		Piece[][] copiedPieces = new Piece[8][8];
		for(int i = 0 ; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				copiedPieces[i][j] = board[i][j];
			}
		}
		Piece newDelPiece = null;
		if(delPiece != null) {
			newDelPiece = delPiece.copy();
		}
		return new Board(copiedPieces, newDelPiece,delStart, delEnd);
	}
	
	@Override
	public String toString() {
		// Change to FULLY implement FEN
		String ret = "";
		for(int i = 7; i >= 0; i--) {
			int count = 0;
			for(int j = 0; j  < 8; j++) {
				if(board[i][j] == null) {
					count += 1;
				}else if(count > 0) {
					ret += count +"" + board[i][j];
					count = 0;
				}else {
					ret += board[i][j];
				}
			}
			if(count != 0) {
				ret += count;
			}
			ret += "/";
		}
		return ret;
	}
	
}
