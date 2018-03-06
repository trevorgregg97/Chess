package Chess.Game;

import Chess.Pieces.Piece;

public class Move {
	public Square start,end;
	public Piece piece;
	public Piece[][] board;
	public Move(Square start, Square end,Piece[][] board) {
		this.start = start;
		this.end = end;
		this.board = board;
	}
	
	@Override
	public String toString() {
		if(piece == null) {
			throw new NullPointerException();
		}
		int rowEnd = end.row;
		int colEnd = end.col;
		int rowStart = start.row;
		int colStart = start.col;
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
