package Chess.Game;

import java.util.ArrayList;
import java.util.List;

import Chess.Game.Board.Color;
import Chess.Pieces.Piece;

public class Game {
	private boolean isWhiteTurn;
	private Board board;
	
	public boolean isGameOver() {
		return false;
	}
	
	public Game() {
		isWhiteTurn = true;
		board = new Board();
	}
	
	public boolean makeMove(Move move) {
		boolean madeMove = board.applyMove(move);
		if(madeMove) {
			isWhiteTurn = !isWhiteTurn;
		}
		return madeMove;
	}
	@Override
	public String toString() {
		String turn = isWhiteTurn ? "w" : "b";
		return board + " " + turn ;
	}
	public Piece[][] getBoard(){
		return board.board;
	}
	
	public List<Move> generateMoves(){
		//Could keep a list of all pieces? 
		Piece[][] pieces = getBoard();
		List<Move> moves = new ArrayList<Move>();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(pieces[i][j] != null && pieces[i][j].color == (isWhiteTurn ? Color.WHITE : Color.BLACK)) {
					moves.addAll(pieces[i][j].generateMoves(i, j,pieces));
				}
			}
		}
		return moves;
	}
}
