package Chess.Game;

import Chess.Bot.SimpleSearcher;

public class GameRunner {
	public static void main(String[] args) {
		Game game = new Game();
		int count = 0;
        while(!game.isGameOver() && count < 1){
            count++;
            Move move = SimpleSearcher.findBestMove(game,1);
            game.makeMove(move);
            System.out.println(game);
        }
	}
}
