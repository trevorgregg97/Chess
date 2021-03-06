package Chess.Bot;

import java.util.List;

import Chess.Game.Game;
import Chess.Game.Move;
import Chess.Pieces.Piece;

public class SimpleSearcher {
	public static Move findBestMove(Game game, int ply) {
		BestMove move = minimax(game,game.getBoard(),ply);
		return (Move) move.move;
	}
	public static BestMove<Move> minimax(Game game, Piece[][] board, int depth) {
		if(depth == 0) {
			return new BestMove<>(Evaluator.evaluateBoard(board,game.isWhiteTurn()));
		}
		
		List<Move> moves = game.generateMoves();
		System.out.println(moves);
		if(moves.isEmpty()) {
			return new BestMove<>(0);
			//Change to check if its a draw or mate
		}
		
		BestMove<Move> best = new BestMove<>(-Integer.MAX_VALUE);
		for(Move move: moves) {
			Game newGame = game.copy();
			newGame.makeMove(move);
			int eval = -minimax(newGame,newGame.getBoard(), depth - 1).value;
			if(eval > best.value) {
				best.move = move;
				best.value = eval;
			}
		}
		return best;
	}
}
