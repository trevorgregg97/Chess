package Chess.Bot;

import Chess.Pieces.Piece;

public class Evaluator {
	public static int evaluateBoard(Piece[][] board, boolean isWhite) {
		assert(board.length == 8);
		assert(board[0].length == 8);
		int eval = 0;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(board[i][j] != null) {
					eval += board[i][j].value;
				}
			}
		}
		return eval;
	}
}
