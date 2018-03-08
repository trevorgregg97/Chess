package Chess.Pieces;

import java.util.LinkedList;
import java.util.List;

import Chess.Game.Board;
import Chess.Game.Board.Color;
import Chess.Game.Move;
import Chess.Game.Square;

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
	public List<Move> generateMoves(Square pos, Board gameBoard){
	    int row = pos.row;
	    int col = pos.col;
		int side = color == Color.WHITE ? 1 : -1;
		Piece[][] board = gameBoard.board;
		List<Move> moves = new LinkedList<>();

        //Check if we can move forward, then check if we can capture or just move forward one.
		if(row + (1 * side) < 8  && row + (1 * side) > -1) {
			if(board[row + (1 * side)][col] == null) {
				Move oneSquare = new Move(pos,new Square(row + (1 * side) ,col),board);
				moves.add(oneSquare);
			}
			if(col + 1 < 8 && col + 1 > -1 && board[row + (1 * side)][col + 1] != null && board[row + (1 * side)][col + 1].color != color) {
				Move capture = new Move(pos,new Square(row + (1 * side) ,col + 1),board);
				moves.add(capture);
			}
			if(col - 1 < 8 && col - 1 > -1 && board[row + (1 * side)][col - 1] != null && board[row + (1 * side)][col - 1].color != color) {
				Move capture = new Move(pos,new Square(row + (1 * side) ,col - 1),board);
				moves.add(capture);
			}
		}
		//If it hasn't moved yet we can go 2 spots forward
		if(!hasMoved && row + (2 * side) < 8 && row + (2 * side) > -1 && board[row + (2 * side)][col] == null) {
			Move twoSquare = new Move(pos,new Square(row + (2 * side), col),board);
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

    @Override
    public List<Move> generateThreatenedSquares(Square pos, Piece[][] board) {
        List<Move> threats = new LinkedList<>();
        threats.add(new Move(pos,new Square(pos.row + 1,pos.col + 1),board,this));
        threats.add(new Move(pos,new Square(pos.row + 1, pos.col -1),board,this));
        return threats;
    }
}
