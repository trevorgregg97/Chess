package Chess.Pieces;
import Chess.Board.Color;
import Chess.Move;

public abstract class Piece {
	protected Color color;
	public Piece(Color color) {
		this.color = color;
	}
	
	public abstract boolean canMakeMove(Piece[][] p, Move m );
}
