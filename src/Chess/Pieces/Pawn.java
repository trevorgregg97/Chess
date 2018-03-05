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
	
	//Tells pawn where it is currently and the position of the board, returns all possible moves (doesn't check for if in check etc)
	@Override
	public List<Move> generateMoves(int row, int col, Piece[][] board){
		int side = color == Color.WHITE ? 1 : -1;
		List<Move> moves = new LinkedList<Move>();

		//TODO MAKE SO CAN'T CAPTURE OWN COLOR
		if(row + (1 * side) < 8  && row + (1 * side) > -1) {
			if(board[row + (1 * side)][col] == null) {
				Move oneSquare = new Move(row,col,row + (1 * side) ,col,board);
				moves.add(oneSquare);
			}
			if(col + 1 < 8 && col + 1 > -1 && board[row + (1 * side)][col + 1] != null) {
				Move capture = new Move(row,col,row + (1 * side) ,col + 1,board);
				moves.add(capture);
			}
			if(col - 1 < 8 && col - 1 > -1 && board[row + (1 * side)][col - 1] != null) {
				Move capture = new Move(row,col,row + (1 * side) ,col - 1,board);
				moves.add(capture);
			}
		}
		
		if(!hasMoved && row + (2 * side) < 8 && row + (2 * side) > -1 && board[row + (2 * side)][col] == null) {
			Move twoSquare = new Move(row,col,row + (2 * side), col,board);
			moves.add(twoSquare);
		}
        for(int i = 0; i < moves.size(); i++){
            moves.get(i).piece = this;
        }
		return moves;
	}
	@Override
	public Piece copy() {
		Pawn pawn = new Pawn(color);
		pawn.value = value;
		pawn.hasMoved = hasMoved;
		return pawn;
	}
}
