package Chess.Pieces;

import java.util.LinkedList;
import java.util.List;

import Chess.Game.Board.Color;
import Chess.Game.Move;

public class Pawn extends Piece {
	public Pawn(Color color) {
		super(color);
		value = color == Color.WHITE ? 1 : -1;
		hasMoved = false;
	}
	@Override
	public String toString() {
		return color == Color.BLACK ? "p" : "P";
	}
	
	public boolean canMakeMove(Piece[][] p, Move m) {
		return true;
	}
	
	@Override
	public List<Move> generateMoves(int row, int col,Piece[][] board){
		int side = color == Color.WHITE ? 1 : -1;
		List<Move> moves = new LinkedList<Move>();
		
		if(board[row + (1 * side)][col] == null && row + (1 * side) < 8) {
			Move oneSquare = new Move(row,col,row + (1 * side) ,col);
			oneSquare.piece = this;
			moves.add(oneSquare);
		}
		
		if(!hasMoved && board[row + (2 * side)][col] == null && row + (2 * side) < 8) {
			Move twoSquare = new Move(row,col,row + (2 * side), col);
			twoSquare.piece = this;
			moves.add(twoSquare);
		}
		return moves;
	}
}
