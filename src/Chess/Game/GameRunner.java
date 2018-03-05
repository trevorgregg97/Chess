package Chess.Game;

import Chess.Bot.SimpleSearcher;

public class GameRunner {
	public static void main(String[] args) {
		Game game = new Game();
		System.out.println(game);
//		for(int i = 0 ; i < 20; i++){
//            Move move = SimpleSearcher.findBestMove(game,4);
//            game.makeMove(move);
//        }
	}
}
