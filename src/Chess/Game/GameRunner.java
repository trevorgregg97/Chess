package Chess.Game;

import Chess.Bot.SimpleSearcher;

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
