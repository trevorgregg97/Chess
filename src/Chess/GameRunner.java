package Chess;

public class GameRunner {
	public static void main(String[] args) {
		Game game = new Game();
		System.out.println(game);
		Move move = new Move(1,3,3,3);
		game.makeMove(move);
		System.out.println(game);
	}
}
