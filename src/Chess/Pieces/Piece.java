package Chess.Pieces;
import java.util.List;
import java.util.Set;

import Chess.Game.Board.Color;
import Chess.Game.Move;

public abstract class Piece {
	public Color color;
	public boolean hasMoved;
	public int value;

	public Piece(Color color) {
		this.color = color;
	}

	public abstract List<Move> generateMoves(int row, int col, Piece[][] board);
	public abstract Piece copy();
	public abstract Set<Move> generateTheatenedSquares();
}
