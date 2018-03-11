package Chess.Game;

import java.util.ArrayList;
import java.util.List;

import Chess.Game.Board.Color;
import Chess.Pieces.Piece;

public class Game {
	private Board board;
	
	public Game() {
		board = new Board();
	}
	
	private Game( Board board) {
		this.board = board;
	}
	
	public boolean isGameOver() {
		if(generateMoves().size() == 0){
		    return true;
        }
        Piece[][] board = getBoard();
		int count = 0;
        for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(board[i][j] != null){
					count++;
				}
			}
		}
		if(count == 2){
        	return true;
		}
        return false;
	}
	
	public boolean makeMove(Move move) {
		boolean madeMove = board.applyMove(move,true);
		return madeMove;
	}

	public boolean isWhiteTurn(){
	    return board.isWhiteTurn;
    }
	@Override
	public String toString() {
		String turn = board.isWhiteTurn ? "w" : "b";
		String castle = "KQkq";
		String enpessante = "-";
		String moves = "0 0";
		return board + " " + turn + " " + castle + " " + enpessante + " " + moves;
	}
	
	public Piece[][] getBoard(){
		return board.board;
	}
	
	public void undoMove() {
		board.undoMove();
	}
	
	public Game copy() {
		return new Game(board.copy());
	}

	public List<Move> generateMoves(){
		Piece[][] pieces = getBoard();
		List<Move> moves = new ArrayList<Move>();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(pieces[i][j] != null && pieces[i][j].color == (board.isWhiteTurn ? Color.WHITE : Color.BLACK)) {
					Square pos = new Square(i,j);
					List<Move> pieceMoves = pieces[i][j].generateMoves(pos,board.board);
					for(int k = 0; k < pieceMoves.size(); k++) {
                        Move move = pieceMoves.get(k);
                        if(board.isLegal(move,true)){
                            moves.add(move);
                        }
                    }
				}
			}
		}
		return moves;
	}
}
