package Chess.Pieces;
import java.util.List;
import java.util.Set;

import Chess.Game.Board.Color;
import Chess.Game.Move;
import Chess.Game.Square;
import Chess.Game.Board;

public abstract class Piece {
	public Color color;
	public boolean hasMoved;
	public int value;

	public Piece(Color color) {
		this.color = color;
	}
	public abstract List<Move> generateMoves(Square pos, Piece[][] board);
	public abstract Piece copy();
	public abstract List<Move> generateThreatenedSquares(Square pos, Piece[][] board);
}
