package Chess.Game;
import Chess.Pieces.*;

public class Board {
	private int delRowStart, delRowEnd, delColStart, delColEnd;
	private Piece delPiece;
	public Piece[][] board;
	public static enum Color {
		WHITE, BLACK;
	}
	
	public Board() {
		board = new Piece[8][8];
		fillBoard(board);
	}
	
	private Board(Piece[][] board, Piece delPiece, int delRowStart, int delRowEnd, int delColStart, int delColEnd) {
		this.board = board;
		this.delPiece = delPiece;
		this.delRowStart = delRowStart;
		this.delRowEnd = delRowEnd;
		this.delColStart = delColStart;
		this.delColEnd = delColEnd;
	}
	
	private void fillBoard(Piece[][] board) {
		for(int i = 0; i < 8; i++) {
			board[1][i] = new Pawn(Color.WHITE);
			board[6][i] = new Pawn(Color.BLACK);
		}
	}
	
	public void undoMove() {
		Piece temp = board[delRowEnd][delColEnd];
		if(delPiece != null) {
			board[delRowEnd][delColEnd] = delPiece;
		}
		board[delRowStart][delColStart] = temp;
	}
	
	public boolean isLegal(Move move) {
		//Check if someone is in check first
		//check if its their piece or move
		Piece pieceToMove = board[move.rowStart][move.colStart];
		move.piece = pieceToMove;
		if(pieceToMove == null) {
			return false;
		}
		if(!pieceToMove.canMakeMove(board,move)) {
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
		delPiece = board[move.rowEnd][move.colEnd];
		delRowStart = move.rowStart;
		delRowEnd = move.rowEnd;
		delColStart = move.colStart;
		delColEnd = move.colEnd;
		
		board[move.rowStart][move.colStart] = null;
		board[move.rowEnd][move.colEnd] = move.piece;
		return true;
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
		return new Board(copiedPieces, newDelPiece, delRowStart, delRowEnd, delColStart, delColEnd);
	}
	
	@Override
	public String toString() {
		// Change to FULLY implement FEN
		String ret = "";
		for(int i = 7; i >= 0; i--) {
			int count = 0;
			for(int j = 7; j >= 0; j--) {
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
