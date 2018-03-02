package Chess.Pieces;
import java.util.List;

import Chess.Game.Board.Color;
import Chess.Game.Move;

public abstract class Piece {
	public Color color;
	protected boolean hasMoved;
	public int value;
	public Piece(Color color) {
		this.color = color;
	}
	
	public abstract boolean canMakeMove(Piece[][] p, Move m );
	public abstract List<Move> generateMoves(int row, int col, Piece[][] board);
}
