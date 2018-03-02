package Chess;

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
		return(board.applyMove(move));
	}
	@Override
	public String toString() {
		String turn = isWhiteTurn ? "w" : "b";
		return board + " " + turn ;
	}
}
