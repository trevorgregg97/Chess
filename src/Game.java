
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
		//Verify move is legal then apply move
		return(board.applyMove(move));
	}
	@Override
	public String toString() {
		return isWhiteTurn + " " + board;
	}
}
