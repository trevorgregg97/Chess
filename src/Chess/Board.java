package Chess;
import Chess.Pieces.*;

public class Board {
	public enum Color {
		WHITE, BLACK;
	}
	private void fillBoard(Piece[][] board) {
		for(int i = 0; i < 8; i++) {
			board[1][i] = new Pawn(Color.WHITE);
			board[6][i] = new Pawn(Color.BLACK);
		}
	}
	private Piece[][] board;
	
	public Board() {
		board = new Piece[8][8];
		fillBoard(board);
	}
	
	public boolean isLegal(Move move) {
		//Check if someone is in check first
		//check if its their piece or move
		Piece pieceToMove = board[move.rowStart][move.colStart];
		move.piece = pieceToMove;
		System.out.println(pieceToMove);
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
		//Make move
		board[move.rowStart][move.colStart] = null;
		board[move.rowEnd][move.colEnd] = move.piece;
		return true;
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
