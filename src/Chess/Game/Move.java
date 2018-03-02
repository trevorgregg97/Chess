package Chess.Game;

import Chess.Pieces.Piece;

public class Move {
	public int rowStart, colStart, rowEnd, colEnd;
	public Piece piece;
	public Move(int rowStart, int colStart, int rowEnd, int colEnd) {
		this.colEnd = colEnd;
		this.rowEnd = rowEnd;
		this.colStart = colStart;
		this.rowStart = rowStart;
	}
	
	@Override
	public String toString() {
		if(piece == null) {
			throw new NullPointerException();
		}
		if(piece.toString().toLowerCase().equals("p")) {
			return "" + (char) (colEnd + 97) + (rowEnd + 1);
		}
		return piece.toString().toUpperCase() + (char) (colEnd + 97) + (rowEnd + 1); 
	}
}
