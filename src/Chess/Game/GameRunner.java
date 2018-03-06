package Chess.Game;

import Chess.Bot.Evaluator;
import Chess.Bot.SimpleSearcher;
import Chess.Game.Board.Color;

public class GameRunner {
	public static void main(String[] args) {
		Game game = new Game();
		for(int i = 0 ; i < 20; i++){
            Move move = SimpleSearcher.findBestMove(game,1);
            game.makeMove(move);
            System.out.println(game);
        }
	}
}
