package Chess.Game;

import Chess.Pieces.Piece;

public class Move {
	public int rowStart, colStart, rowEnd, colEnd;
	public Piece piece;
	public Piece[][] board;
	public Move(int rowStart, int colStart, int rowEnd, int colEnd,Piece[][] board) {
		this.colEnd = colEnd;
		this.rowEnd = rowEnd;
		this.colStart = colStart;
		this.rowStart = rowStart;
		this.board = board;
	}
	
	@Override
	public String toString() {
		if(piece == null) {
			throw new NullPointerException();
		}
		
		if(board[rowEnd][colEnd] == null) {
			if(piece.toString().toLowerCase().equals("p")) {
				return "" + (char) (colEnd + 97) + (rowEnd + 1);
			}
			return piece.toString().toUpperCase() + (char) (colEnd + 97) + (rowEnd + 1); 
		}else {
			if(piece.toString().toLowerCase().equals("p")) {
				return (char) (colStart + 97) + "x" + (char) (colEnd + 97) + (rowEnd + 1);
			}
			return piece.toString().toUpperCase() + "x" + (char) (colEnd + 97) + (rowEnd + 1); 
		}
		
	}
}
