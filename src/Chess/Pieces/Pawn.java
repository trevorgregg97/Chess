package Chess.Pieces;

import Chess.Board.Color;
import Chess.Move;

public class Pawn extends Piece {
	public Pawn(Color color) {
		super(color);
	}
	@Override
	public String toString() {
		return color == Color.BLACK ? "p" : "P";
	}
	
	public boolean canMakeMove(Piece[][] p, Move m) {
		return true;
	}
}
