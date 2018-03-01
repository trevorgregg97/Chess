import java.util.HashMap;
import java.util.Map;

public class Board {
	private enum Pieces {
		PAWN;
	}
	private static void fillBoard(Pieces[][] board) {
		for(int i = 0; i < 8; i++) {
			board[1][i] = Pieces.PAWN;
			board[6][i] = Pieces.PAWN;
		}
	}
	private static Map<Pieces,String> pieceStringMapping; 
	
	private Pieces[][] board;
	public Board() {
		board = new Pieces[8][8];
		fillBoard(board);
		pieceStringMapping = new HashMap<Pieces,String>();
		pieceStringMapping.put(Pieces.PAWN,"p");
	}
	
	public boolean isLegal(Move move) {
		//Check if allowed
		return true;
	}
	public boolean applyMove(Move move) {
		if(!isLegal(move)) {
			return false;
		}
		//Make move
		return true;
	}
	@Override
	public String toString() {
		// Change to implement FEN
		String ret = "";
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				ret += board[i][j];
			}
		}
		return ret;
	}
	
}
